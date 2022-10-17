package com.machines.main.aop;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.machines.main.ClearCacheService;

@Aspect
@Component
@Slf4j
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "false", matchIfMissing = true)
public class NoKafkaAspect {

	@PostConstruct
	public void init() {
		log.debug("NoKafkaAspect constructed");
	}

	@Autowired
	private ClearCacheService clearCacheService;

	@Pointcut("@annotation(com.machines.main.aop.KafkaAnnotation)")
	public void refreshCacheOfAnyMethodAnnotated() {
	}

	@After("refreshCacheOfAnyMethodAnnotated()")
	public void afterChanges() {
		clearCacheService.refreshAllCache();
	}

}
