package com.rnd.spring.scheduler.dynamicscheduling;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {


    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("******** RUNNING ");

    }
}