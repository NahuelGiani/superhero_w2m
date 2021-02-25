package com.w2m.superhero.annotations.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodInfoAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodInfoAspect.class);

    @Around("@annotation(com.w2m.superhero.annotations.interfaces.MethodInfo)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info("Method: {}() executed in {} ms. Response: {}", joinPoint.getSignature().getName(), 
        		executionTime, proceed);
        return proceed;
    }
	
}
