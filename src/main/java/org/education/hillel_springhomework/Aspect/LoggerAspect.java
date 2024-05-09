package org.education.hillel_springhomework.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.education.hillel_springhomework.dto.StatisticsCollection;
import org.education.hillel_springhomework.model.Implementation;
import org.education.hillel_springhomework.service.StatisticsService;
import org.education.hillel_springhomework.writer.MyWriter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggerAspect {

    private final StatisticsService statisticsService;
    private final MyWriter myWriter;

    public LoggerAspect(StatisticsService statisticsService, MyWriter myWriter) {
        this.statisticsService = statisticsService;
        this.myWriter = myWriter;
    }

    @Pointcut("execution(public * org.education.hillel_springhomework.repository.jpa.DataService*.*(..))")
    public void jpaMethods() {
    }

    @Pointcut("execution(public * org.education.hillel_springhomework.repository.jdbc..*(..))")
    public void jdbcMethods() {
    }

    @AfterReturning(pointcut = "jpaMethods()", returning = "result")
    public void logJPA(JoinPoint joinPoint, Object result) {
        if (result != null && !result.equals(false)) {
            Object[] args = joinPoint.getArgs();
            Date date = new Date();
            String methodName = joinPoint.getSignature().getName();

            myWriter.write(date, methodName, args);

            StatisticsCollection statisticsCollection = new StatisticsCollection(methodName, Implementation.JPA, date);
            statisticsService.addStatistics(statisticsCollection);
        }
    }

    @After(value = "jdbcMethods()")
    public void logJDBC(JoinPoint joinPoint) {
        if (!joinPoint.getSignature().getName().equals("getUserDAO") &&
                !joinPoint.getSignature().getName().equals("getUsers")) {
            Object[] args = joinPoint.getArgs();
            Date date = new Date();
            String methodName = joinPoint.getSignature().getName();

            myWriter.write(date, methodName, args);

            StatisticsCollection statisticsCollection = new StatisticsCollection(methodName, Implementation.JDBC, date);
            statisticsService.addStatistics(statisticsCollection);
        }
    }

    @AfterThrowing(value = "jdbcMethods()", throwing = "exception")
    public void logJDBCException(JoinPoint joinPoint, Exception exception) {
        if (!joinPoint.getSignature().getName().equals("getUserDAO") &&
                !joinPoint.getSignature().getName().equals("getUsers")) {

            myWriter.writeException(joinPoint.getSignature().getName(), exception);
        }
    }
}

