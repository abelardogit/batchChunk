package com.abelardolg.config;

import com.abelardolg.persistence.IPersonDAO;
import com.abelardolg.service.IPersonService;
import com.abelardolg.service.PersonServiceImpl;
import com.abelardolg.steps.PersonItemReader;
import com.abelardolg.steps.PersonItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public PersonItemReader personItemReader() {
        return new PersonItemReader();
    }

    @Bean
    public PersonItemWriter itemReaderStep() {
        return new PersonItemWriter();
    }
}

