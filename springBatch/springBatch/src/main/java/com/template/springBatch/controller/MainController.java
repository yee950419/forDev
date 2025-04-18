package com.template.springBatch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @GetMapping("/first")
    public String firstApi(@RequestParam String date) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", date)
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("firstJob"), jobParameters);

        return "ok";
    }

    @GetMapping("/second")
    public String secondApi(@RequestParam String date) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", date)
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("secondJob"), jobParameters);

        return "ok";
    }
}