package com.rudkids.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class S3ImageDeleterJobConfiguration {
    public static final String JOB_NAME = "testBatch";
    public static final String BEAN_PREFIX = JOB_NAME + "_";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final S3ImageDeleterTasklet imageDeleterTasklet;

    @Bean(JOB_NAME)
    public Job testJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
            .preventRestart()
            .start(processorConvertStep())
            .build();
    }

    @Bean(BEAN_PREFIX + "step")
    public Step processorConvertStep() {
        return new StepBuilder(BEAN_PREFIX + "step", jobRepository)
            .tasklet(imageDeleterTasklet, transactionManager)
            .build();
    }
}
