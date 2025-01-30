package com.learn.universityjpa.logging;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Компонент для реализации логирования.
 */
@Slf4j
@Aspect
@Component
public class TaskLogger {

    private final Counter customMetricCounter;
    private final Counter countRequest;
    private final Timer timer;
    long start;

    @Autowired
    public TaskLogger(MeterRegistry meterRegistry) {
        this.customMetricCounter = Counter.builder("custom_metric_name")
                .description("Description of custom metric")
                .tags("environment", "development")
                .register(meterRegistry);
        this.countRequest =  Counter.builder("custom_count_requests")
                .description("Count of web-requests")
                .tags("environment", "request")
                .register(meterRegistry);
        this.timer =  meterRegistry.timer("app.timer1", "type", "ping");
    }

    /**
     * Логирует время до выполнения метода отмеченного аннотаций {@link TaskBeginFinishLogging}.
     * @param joinPoint точка применения аспекта.
     */
    @Before("@annotation(com.learn.universityjpa.logging.TaskBeginFinishLogging))")
    public void logScheduledTasksBeforeExecution(final JoinPoint joinPoint) {
        customMetricCounter.increment();
        start = System.currentTimeMillis();
        log.info("Task {} started at [{}]...", getJoinPointName(joinPoint), new Date());
    }

    /**
     * Логирует время после выполнения метода отмеченного аннотаций {@link TaskBeginFinishLogging}.
     * @param joinPoint точка применения аспекта.
     */
    @After("@annotation(com.learn.universityjpa.logging.TaskBeginFinishLogging))")
    public void logScheduledTasksAfterExecution(final JoinPoint joinPoint) {
        timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
        log.info("...Task {} ended at [{}]  timer- {}", getJoinPointName(joinPoint), new Date(), timer.count());
    }

    /**
     * Логирует запрос.
     * @param joinPoint точка применения аспекта.
     */
    @Before("@annotation(com.learn.universityjpa.logging.CounterRequests)")
    public void countRequest(final JoinPoint joinPoint) {
        countRequest.increment();
        log.info("was calling method {}  at [{}]  timer- {}", joinPoint.toShortString(), new Date(), timer.count());
    }

    private String getJoinPointName(final JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName();
    }
}
