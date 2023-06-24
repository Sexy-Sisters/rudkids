package com.rudkids.batch.image.job;

import com.rudkids.batch.image.tasklet.CommunityTasklet;
import com.rudkids.batch.image.tasklet.ItemTasklet;
import com.rudkids.batch.image.tasklet.ProductTasklet;
import com.rudkids.batch.image.tasklet.UserTasklet;
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
public class DeleteS3ImageJobConfiguration {
    public static final String JOB_NAME = "deleteImageBatch";
    public static final String BEAN_PREFIX = JOB_NAME + "_";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CommunityTasklet communityTasklet;
    private final ItemTasklet itemTasklet;
    private final ProductTasklet productTasklet;
    private final UserTasklet userTasklet;

    @Bean(JOB_NAME)
    public Job deleteImageJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
            .start(deleteCommunityImageStep())
            .next(deleteItemImageStep())
            .next(deleteProductImageStep())
            .next(deleteUserImageStep())
            .build();
    }

    @Bean(BEAN_PREFIX + "communityStep")
    public Step deleteCommunityImageStep() {
        return new StepBuilder(BEAN_PREFIX + "communityStep", jobRepository)
            .tasklet(communityTasklet, transactionManager)
            .build();
    }

    @Bean(BEAN_PREFIX + "itemStep")
    public Step deleteItemImageStep() {
        return new StepBuilder(BEAN_PREFIX + "itemStep", jobRepository)
            .tasklet(itemTasklet, transactionManager)
            .build();
    }

    @Bean(BEAN_PREFIX + "productStep")
    public Step deleteProductImageStep() {
        return new StepBuilder(BEAN_PREFIX + "productStep", jobRepository)
            .tasklet(productTasklet, transactionManager)
            .build();
    }

    @Bean(BEAN_PREFIX + "userStep")
    public Step deleteUserImageStep() {
        return new StepBuilder(BEAN_PREFIX + "userStep", jobRepository)
            .tasklet(userTasklet, transactionManager)
            .build();
    }
}
