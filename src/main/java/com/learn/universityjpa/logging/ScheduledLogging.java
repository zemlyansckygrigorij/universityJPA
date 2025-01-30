package com.learn.universityjpa.logging;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.apache.commons.compress.utils.Lists;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class SubjectController
 * для работы с web сайтом /subjects
 */
@Component
@Timed("people")
public class ScheduledLogging {
    private final AtomicInteger testGauge; //has
    private final Counter testCounter; //has

    private Timer timer; //has
    Timer timer1; //has
    int compositeRegistryGauge; //has

    Counter scheduledCounter; //has

    Counter counter; //has
    Counter counter1; //has

    Counter counter12; // has
    List<Tag> list = new ArrayList<>(); //has
    int i = 8;
    DistributionSummary distributionSummary; //has
    Timer timer2;

    public ScheduledLogging(MeterRegistry meterRegistry) {



//has
        testGauge = meterRegistry.gauge("schedule_gauge1", new AtomicInteger(0));
        //has
        testCounter = meterRegistry.counter("schedule_counter2");

       /* list.add(Tag.of("tag0", "value0"));
        list.add(Tag.of("tag1", "value1"));
        list.add(Tag.of("tag2", "value2"));
        list.add(Tag.of("tag3", "value3"));
        list.add(Tag.of("tag4", "value4"));
        list.add(Tag.of("tag5", "value5"));
        list.add(Tag.of("tag6", "value6"));
        list.add(Tag.of("tag7", "value7"));
        list.add(Tag.of("tag8", "value8"));*/


        //has
        timer = meterRegistry.timer("schedule_timer3", list);

        //has
        compositeRegistryGauge = meterRegistry
                .gauge("compositeRegistry_gauge4", 1);
//has
        scheduledCounter = Counter
                .builder("scheduled_counte5r")
                .description("scheduled counter appuniversityJPA6")
                .tags("tag1", "tag2")
                .register(meterRegistry);



//not has
      //  counter12 =  Metrics.counter("objects.instance");

        counter12 = Counter
                .builder("objects_instance")
                .description("scheduled counter appuniversityJPA6")
                .tags("tag_objects.instance", "tag_objects.instance")
                .register(meterRegistry);
//not has
        counter = meterRegistry.counter("page.visitors7", "age", "20s");
        //has
         counter1 = Counter
                .builder("instance8")
                .description("indicates instance count of the object")
                .tags("dev", "performance")
                .register(meterRegistry);

        counter.increment(1000);




        //has
        timer1 = meterRegistry.timer("app_event9");
        timer.record(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (InterruptedException ignored) {
            }
        });

        timer1.record(30, TimeUnit.MILLISECONDS);

//has
        LongTaskTimer longTaskTimer = LongTaskTimer
                .builder("3rdPartyService10")
                .register(meterRegistry);

        LongTaskTimer.Sample currentTaskId = longTaskTimer.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException ignored) { }
        long timeElapsed = currentTaskId.stop();



//noit has
        Gauge gauge = Gauge
                .builder("cache.size11", list, List::size)
                .register(meterRegistry);


//has
        distributionSummary = DistributionSummary
                .builder("request_size12")
                .baseUnit("bytes")
                .register(meterRegistry);
        //not has
        timer2 = Timer
                .builder("test_timer13")
                .publishPercentiles(0.3, 0.5, 0.95)
                .publishPercentileHistogram()
                .register(meterRegistry);

        //not has
        DistributionSummary hist = DistributionSummary
                .builder("summary14")
                .serviceLevelObjectives(1, 10, 5)
                .register(meterRegistry);

    }

    @Scheduled(fixedRate = 10)
    public void schedulingTask() {
        i++;
        testGauge.set(getRandomNumberInRange(0 , 100));
        testCounter.increment();
        compositeRegistryGauge++;
        scheduledCounter.increment();
        counter.increment(-1);

      //  list.add(Tag.of("tag" + i,"value" + i));
        distributionSummary.record(i);
        timer.record(i, TimeUnit.SECONDS);

        getCars();

    }
    private int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    //@TaskBeginFinishLogging
    public List<String> getCars() {
        return Lists.newArrayList();
    }

    @Timed
    public List<String> getJobs() {
        return Lists.newArrayList();
    }
}


















