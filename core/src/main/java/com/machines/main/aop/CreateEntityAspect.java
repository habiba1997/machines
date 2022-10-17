package com.machines.main.aop;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.machines.main.ClearCacheService;

@Aspect
@Component
@Slf4j
public class CreateEntityAspect {

	@Autowired
	private ClearCacheService clearCacheService;

	@PostConstruct
	public void init() {
		log.debug("CreateEntityAspect constructed");
	}

	@Pointcut("execution(* com.machines.main.impl.CreateEntityService.*(..))")
	public void refreshCacheOfAllMethodsInCreateEntityService() {
	}

	@After("refreshCacheOfAllMethodsInCreateEntityService()")
	public void afterChanges() {
		clearCacheService.refreshAllCache();
	}

}
