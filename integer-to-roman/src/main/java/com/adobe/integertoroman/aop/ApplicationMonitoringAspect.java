package com.adobe.integertoroman.aop;

import com.adobe.integertoroman.metrics.IntegerToRomanTelemetryService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationMonitoringAspect {

    @Autowired
    private IntegerToRomanTelemetryService integerToRomanTelemetryService;

    @AfterReturning("within(com.adobe.integertoroman.*.controller..*)")
    public void afterControllerMethodReturns(JoinPoint joinPoint) {
        integerToRomanTelemetryService.increaseControllerSuccess(joinPoint.getSignature().getDeclaringType().getSimpleName());
    }

    @AfterThrowing("within(com.adobe.integertoroman.*.controller..*)")
    public void afterControllerMethodThrows(JoinPoint joinPoint) {
        integerToRomanTelemetryService.increaseControllerFailure(joinPoint.getSignature().getDeclaringType().getSimpleName());
    }

    @Around("within(com.adobe.integertoroman.*.controller..*)")
    public void withinControllerMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Long now = System.currentTimeMillis();
        joinPoint.proceed();
        integerToRomanTelemetryService.registerControllerExecution(System.currentTimeMillis() - now);
    }


}
