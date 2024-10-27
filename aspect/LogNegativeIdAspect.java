package org.technicaltest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.technicaltest.exception.SpaceShipNotFoundException;

@Aspect
@Component
public class LogNegativeIdAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogNegativeIdAspect.class);

    @Around("@annotation(org.technicaltest.Annotations.LogNegativeId)")
    public Object logNegativeId(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Integer) {
                int id = (Integer) arg;
                if (id < 0) {
                    logger.warn("Negative ID detected: {}", id);
                    throw new SpaceShipNotFoundException("SpaceShip not found for negative ID: " + id);
                }
            }
        }
        return joinPoint.proceed();
    }
}
