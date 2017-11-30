package com.an.acrowfunding.portal.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.acrowfunding.common.bean.Cert;
import com.an.acrowfunding.common.bean.Member;
import com.an.acrowfunding.common.bean.ResultAjax;
import com.an.acrowfunding.common.bean.Ticket;
import com.an.acrowfunding.portal.service.ActivitiService;
import com.an.acrowfunding.portal.service.MemberService;

@RequestMapping("member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ActivitiService activitiService;
	
	@ResponseBody
	@RequestMapping("basicinfo")
	public ResultAjax updateBasicinfo(HttpSession session,Member member) {
		ResultAjax result = new ResultAjax();
		
		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());
			loginMember.setTel(member.getTel());
			
			session.setAttribute("loginMember", loginMember);
			
			
			int count = memberService.updateBasicinfo(loginMember);
			if(count == 1) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("updateAcct")
	public ResultAjax updateAcct(HttpSession session,String accttype) {
		ResultAjax result = new ResultAjax();
		
		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			loginMember.setAccttype(accttype);
			session.setAttribute("loginMember", loginMember);
			
			int count = memberService.updateAcctType(loginMember);
			if(count == 1) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	//记忆功能，当点击未实名认证的时候，会自动跳转到上次信息保存的地方
	@RequestMapping("apply")
	public String apply(HttpSession session,Map<String,Object>map) {
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		Ticket ticket = memberService.queryTicketByMemberId(loginMember.getId());
		
		if(ticket == null) {
			String piid = activitiService.startProcess(loginMember);
			ticket = new Ticket();
			ticket.setMemberid(loginMember.getId());
			ticket.setPiid(piid);
			ticket.setStatus("0");//审核中
			//accttype-账户类型，basicinfo-基本信息，uploadfile-资质文件上传，checkemail-
			ticket.setPstep("accttype");
			memberService.insertTicket(ticket);
			return "member/accttype";
			
		}else {
			String pstep = ticket.getPstep();
			if("accttype".equals(pstep)) {
				return "member/accttype";
			}else if("basicinfo".equals(pstep)) {
				return "member/basicinfo";
			}else if("uploadfile".equals(pstep)) {
				List<Cert> certs =  memberService.queryCertsByAccttype(loginMember);
				map.put("certs", certs);
				return "member/uploadfile";
			}else if("checkemail".equals(pstep)) {
				return "member/checkemail";
			}
		}
		return "accttype";
	}
	
}
