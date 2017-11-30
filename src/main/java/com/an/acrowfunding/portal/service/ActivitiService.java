package com.an.acrowfunding.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.an.acrowfunding.common.bean.Member;

@FeignClient("atcrowdfunding-activiti-service")
public interface ActivitiService {
	
	@RequestMapping("/act/startProcess")
	public String startProcess(@RequestBody Member loginMember);
	
}
