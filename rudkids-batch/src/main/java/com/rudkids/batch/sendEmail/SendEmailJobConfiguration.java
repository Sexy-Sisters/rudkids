package com.rudkids.batch.sendEmail;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.mail.SimpleMailMessageItemWriter;
import org.springframework.batch.item.mail.builder.SimpleMailMessageItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SendEmailJobConfiguration {
    public static final String JOB_NAME = "sendEmailBatch5";
    public static final String BEAN_PREFIX = JOB_NAME + "_";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory emf;
    private final JavaMailSender mailSender;

    @Value("${chunkSize:1000}")
    private int chunkSize;

    @Bean(JOB_NAME)
    public Job sendEmailJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
            .preventRestart()
            .start(sendEmailStep())
            .build();
    }

    @Bean(BEAN_PREFIX + "step")
    public Step sendEmailStep() {
        return new StepBuilder(BEAN_PREFIX + "step", jobRepository)
            .<String, SimpleMailMessage>chunk(chunkSize, transactionManager)
            .reader(sendEmailReader())
            .processor(sendEmailProcessor())
            .writer(sendEmailWriter())
            .build();
    }

    @Bean
    public JpaPagingItemReader<String> sendEmailReader() {
        return new JpaPagingItemReaderBuilder<String>()
            .name(BEAN_PREFIX + "reader")
            .entityManagerFactory(emf)
            .pageSize(chunkSize)
            .queryString("SELECT u.email FROM User u")
            .build();
    }

    @Bean
    public ItemProcessor<String, SimpleMailMessage> sendEmailProcessor() {
        return email -> {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("batchtest0729@gmail.com");
            mail.setTo(email);
            mail.setSubject("Welcome!");
            mail.setText("환영환영");

            return mail;
        };
    }

    @Bean
    public ItemWriter<SimpleMailMessage> sendEmailWriter() {
        return mail -> {
            SimpleMailMessageItemWriter writer = new SimpleMailMessageItemWriterBuilder()
                .mailSender(mailSender)
                .build();

            writer.write(mail);
        };
    }
}
