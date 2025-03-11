package miu.edu.demo.aspects;

import miu.edu.demo.entities.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(ExecutionTime)")
    @Transactional
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        Logger logger = new Logger();
        logger.setOperation(joinPoint.getSignature().getName());
        logger.setTime(executionTime);
        logger.setPrinciple("User");
        return proceed;
    }
}
