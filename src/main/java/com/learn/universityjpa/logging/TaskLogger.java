package com.learn.universityjpa.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Компонент для реализации логирования.
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TaskLogger {
    /**
     * Логирует время до выполнения метода отмеченного аннотаций {@link TaskBeginFinishLogging}
     *
     * @param joinPoint точка применения аспекта.
     */
    @Before("@annotation(com.learn.universityjpa.logging.TaskBeginFinishLogging))")
    public void logScheduledTasksBeforeExecution(final JoinPoint joinPoint) {
        log.info("Task {} started at [{}]...", getJoinPointName(joinPoint),new Date());
    }

    /**
     * Логирует время после выполнения метода отмеченного аннотаций {@link TaskBeginFinishLogging}
     *
     * @param joinPoint точка применения аспекта.
     */
    @After("@annotation(com.learn.universityjpa.logging.TaskBeginFinishLogging))")
    public void logScheduledTasksAfterExecution(final JoinPoint joinPoint) {
        log.info("...Task {} ended at [{}]", getJoinPointName(joinPoint), new Date());
    }

    private String getJoinPointName(final JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName();
    }
}
