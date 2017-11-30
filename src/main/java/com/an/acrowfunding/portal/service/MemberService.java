package com.an.acrowfunding.portal.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.an.acrowfunding.common.bean.Cert;
import com.an.acrowfunding.common.bean.Member;
import com.an.acrowfunding.common.bean.Ticket;

@FeignClient("atcrowdfunding-member-service")
public interface MemberService {
	
	@RequestMapping("/member/login/{loginacct}")
	public Member getMemberByLoginAccout(@PathVariable("loginacct")String loginacct);

	@RequestMapping("/member/queryTicketByMemberId/{id}")
	public Ticket queryTicketByMemberId(@PathVariable("id")Integer id);

	@RequestMapping("/member/insertTicket")
	public void insertTicket(@RequestBody Ticket ticket);

	@RequestMapping("/member/updateAcctType")
	public int updateAcctType(@RequestBody Member loginMember);

	@RequestMapping("/member/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member loginMember);
	
	@RequestMapping("/member/queryCertsByAccttype")
	public List<Cert> queryCertsByAccttype(@RequestBody Member loginMember);

}
