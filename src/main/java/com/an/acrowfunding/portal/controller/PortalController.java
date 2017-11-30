package com.an.acrowfunding.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.acrowfunding.common.bean.Member;
import com.an.acrowfunding.common.controller.BaseController;
import com.an.acrowfunding.portal.service.MemberService;

@Controller
public class PortalController extends BaseController{
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loginUser")
	@ResponseBody
	public Object getMemberByLoginAccout(Member member,HttpSession session) {
		/*Member member = memberService.getMemberByLoginAccout(loginacct);
		if(member != null) {
			
		}*/
		start();
		System.out.println("Member:"+member);
		try {
			Member dbMember = memberService.getMemberByLoginAccout(member.getLoginacct());
			System.out.println("DbMember:"+dbMember);
			if(dbMember==null) {
				fail();
			}else {
				if(dbMember.getUserpswd().equals(member.getUserpswd())) {
					session.setAttribute("loginMember", dbMember);
					success();
				}else {
					fail();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@RequestMapping("/member")
	public String member() {
		return "member";
	}

}
