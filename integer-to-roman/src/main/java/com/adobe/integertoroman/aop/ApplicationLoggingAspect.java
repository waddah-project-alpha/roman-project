package com.adobe.integertoroman.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
public class ApplicationLoggingAspect {

    private final static List<String> SENSITIVE_PARAM_NAMES = Arrays.asList("password", "userId", "userName", "age");

    @Around("within(com.adobe.integertoroman.converter.service..*) || within(com.adobe.integertoroman.converter.controller..*)")
    private Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        Logger logger = LoggerFactory.getLogger(codeSignature.getDeclaringType());
        String methodName = codeSignature.getName();
        final Object[] args = joinPoint.getArgs();
        final String[] argNames = codeSignature.getParameterNames();
        String argNamesAndValues = IntStream.range(0, args.length)
                                         .mapToObj(i -> obfuscateSensitiveData(argNames[i], args[i]))
                                         .collect(Collectors.joining(", "));
        try {
            Object result = joinPoint.proceed();
            logger.info(MessageFormat.format("Executed method {0}({1}). Result: {2}", methodName, argNamesAndValues, result));
            return result;
        } catch(Exception e) {
            logger.error(MessageFormat.format("Executed method {0}({1}). Threw exception: {2}", methodName, argNamesAndValues, e.getMessage()));
            throw e;
        }
    }

    private String obfuscateSensitiveData(String key, Object value)
    {
        if (SENSITIVE_PARAM_NAMES.contains(key.toLowerCase()))
        {
            return MessageFormat.format("{0}:###", key);
        }
        return MessageFormat.format("{0}:{1}", key, value);
    }
}
