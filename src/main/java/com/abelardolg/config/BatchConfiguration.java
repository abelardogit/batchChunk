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
    @JobScope
    public PersonItemWriter itemReaderStep() {
        IPersonDAO personDAO;
        IPersonService service = new PersonServiceImpl(personDAO);
        return new PersonItemWriter();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep() {
        return new ItemProcessorStep();
    }

    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep() {
        return new ItemWriterStep();
    }

    @Bean
    public Step descompressFileStep(JobRepository jobRepository,
                                    PlatformTransactionManager transactionManager) {
        return new StepBuilder("itemDescompressStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                itemDescompressStep())
                .build()
        ;
    }

    @Bean
    public Step readFileStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager) {
        return new StepBuilder("itemReaderStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .tasklet(itemReaderStep())
                .build()
        ;
    }

    @Bean
    public Step processFileStep(JobRepository jobRepository,
                                PlatformTransactionManager transactionManager) {
        return new StepBuilder("itemProcessStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .tasklet(itemProcessorStep())
                .build()
        ;
    }

    @Bean
    public Step writeDataStep(JobRepository jobRepository,
                              PlatformTransactionManager transactionManager) {
        return new StepBuilder("itemWriterStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .tasklet(itemWriterStep())
                .build()
        ;
    }

    @Bean
    public Job readCSVJob(JobRepository jobRepository) {
        System.err.println("readCSVJob");
        return new JobBuilder("readCSVJob", jobRepository)
                .start(descompressFileStep(jobRepository))
                .next(readFileStep(jobRepository))
                .next(processFileStep(jobRepository))
                .next(writeDataStep(jobRepository))
                .build()
        ;
    }
}

