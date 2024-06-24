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

/**
 * Aspect class that handles telemetry for our application. Note that this class merely keeps track of success/failure
 * of calls for controller methods, along with execution times. In a real world application we'd definitely add more useful
 * metrics, but this is merely a proof of concept
 */
@Aspect
@Component
public class ApplicationMonitoringAspect {

    @Autowired
    private IntegerToRomanTelemetryService integerToRomanTelemetryService;

    /**
     * Increases success counter after successful return of a controller method
     * @param joinPoint
     */
    @AfterReturning("within(com.adobe.integertoroman.*.controller..*)")
    public void afterControllerMethodReturns(JoinPoint joinPoint) {
        integerToRomanTelemetryService.increaseControllerSuccess(joinPoint.getSignature().getDeclaringType().getSimpleName());
    }

    /**
     * Increases failure counter if controller method throws an exception
     * @param joinPoint
     */
    @AfterThrowing("within(com.adobe.integertoroman.*.controller..*)")
    public void afterControllerMethodThrows(JoinPoint joinPoint) {
        integerToRomanTelemetryService.increaseControllerFailure(joinPoint.getSignature().getDeclaringType().getSimpleName());
    }

    /**
     * Measures execution time for a controller method. Note that if the method throws, the measurement is not registered
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("within(com.adobe.integertoroman.*.controller..*)")
    public Object withinControllerMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Long now = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        integerToRomanTelemetryService.registerControllerExecution(System.currentTimeMillis() - now);
        return result;
    }


}
