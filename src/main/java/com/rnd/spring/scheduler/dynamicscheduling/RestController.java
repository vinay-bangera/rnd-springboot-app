package com.rnd.spring.scheduler.dynamicscheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/scheduler")
public class RestController {

    private Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    @Autowired
    private TaskScheduler taskScheduler;

    @GetMapping("/start")
    public void start() {
        scheduleATask("JOBID-1", new PrintTask("JOBID-11"), "10 * * * * ?");
    }

    @GetMapping("/stop")
    public void stop() {
        System.out.println("Stop");
        for (ScheduledFuture future : jobsMap.values()) {
            future.cancel(false);
        }
    }


    public void scheduleATask(String jobId, Runnable tasklet, String cronExpression) {
        System.out.println("Scheduling task with job id: " + jobId + " and cron expression: " + cronExpression);
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
}

class PrintTask implements Runnable {

    private String jobID;

    public PrintTask(String jobID) {
        this.jobID = jobID;
    }

    @Override
    public void run() {
        System.out.println("Running " + jobID);
    }
}