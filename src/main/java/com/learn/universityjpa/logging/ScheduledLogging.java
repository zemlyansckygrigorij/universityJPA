package com.learn.universityjpa.logging;

import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduledLogging {
    private final AtomicInteger testGauge;
    private final Counter testCounter;
    private Timer timer;
    int compositeRegistryGauge;

    Counter scheduledCounter;

    Counter counter;
    Counter counter1;
    List<Tag> list = new ArrayList<>();
    int i=8;
    DistributionSummary distributionSummary;
    Timer timer2;
    public ScheduledLogging(MeterRegistry meterRegistry){

        CompositeMeterRegistry compositeRegistry = new CompositeMeterRegistry();
        SimpleMeterRegistry oneSimpleMeter = new SimpleMeterRegistry();


        testGauge = meterRegistry.gauge("schedule_gauge1", new AtomicInteger(0));
        testCounter = meterRegistry.counter("schedule_counter2");

        list.add(Tag.of("tag0","value0"));
        list.add(Tag.of("tag1","value1"));
        list.add(Tag.of("tag2","value2"));
        list.add(Tag.of("tag3","value3"));
        list.add(Tag.of("tag4","value4"));
        list.add(Tag.of("tag5","value5"));
        list.add( Tag.of("tag6","value6"));
        list.add(Tag.of("tag7","value7"));
        list.add(Tag.of("tag8","value8"));
        timer = meterRegistry.timer("schedule_timer3",list);

        compositeRegistryGauge = compositeRegistry
                .gauge("compositeRegistry_gauge4", 1);

        scheduledCounter = Counter
                .builder("scheduled_counte5r")
                .description("scheduled counter appuniversityJPA6")
                .tags("tag1","tag2")
                .register(meterRegistry);

        Metrics.addRegistry(new SimpleMeterRegistry());

        Metrics.counter("objects.instance").increment();

        counter = oneSimpleMeter.counter("page.visitors7", "age", "20s");
         counter1 = Counter
                .builder("instance8")
                .description("indicates instance count of the object")
                .tags("dev", "performance")
                .register(meterRegistry);

        counter.increment(1000);



        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        Timer timer = registry.timer("app.event9");
        timer.record(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (InterruptedException ignored) {
            }
        });

        timer.record(30, TimeUnit.MILLISECONDS);


        LongTaskTimer longTaskTimer = LongTaskTimer
                .builder("3rdPartyService10")
                .register(registry);

        LongTaskTimer.Sample currentTaskId = longTaskTimer.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException ignored) { }
        long timeElapsed = currentTaskId.stop();




        Gauge gauge = Gauge
                .builder("cache.size11", list, List::size)
                .register(registry);



        distributionSummary = DistributionSummary
                .builder("request.size12")
                .baseUnit("bytes")
                .register(registry);
        timer2 = Timer
                .builder("test.timer13")
                .publishPercentiles(0.3, 0.5, 0.95)
                .publishPercentileHistogram()
                .register(registry);
        DistributionSummary hist = DistributionSummary
                .builder("summary14")
                .serviceLevelObjectives(1, 10, 5)
                .register(registry);

    }

    @Scheduled(fixedRate = 1000)
    public void schedulingTask() {
        i++;
        testGauge.set(getRandomNumberInRange(0 , 100));
        testCounter.increment();
        compositeRegistryGauge++;
        scheduledCounter.increment();
        counter.increment(-1);

        list.add(Tag.of("tag"+i,"value"+i));
        distributionSummary.record(i);
        timer.record(i, TimeUnit.SECONDS);



    }
    private int getRandomNumberInRange(int min , int max){
        Random random = new Random();
        return random.nextInt((max-min)+1)+min;
    }
}


















