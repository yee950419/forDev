package com.template.springBatch.batch;

import com.template.springBatch.entity.WinEntity;
import com.template.springBatch.repository.WinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class SecondBatch {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final WinRepository winRepository;

    @Bean
    public Job secondJob() {

        return new JobBuilder("secondJob", jobRepository)
                .start(secondStep())
                .build();
    }

    @Bean
    public Step secondStep() {

        return new StepBuilder("secondStep", jobRepository)
                .<WinEntity, WinEntity> chunk(10, platformTransactionManager)
                .reader(winReader())
                .processor(giveRewardProcessor())
                .writer(winWriter())
                .build();
    }

    @Bean
    public RepositoryItemReader<WinEntity> winReader() {

        return new RepositoryItemReaderBuilder<WinEntity>()
                .name("winReader")
                .pageSize(10)
                .methodName("findByWinGreaterThanEqual")
                .arguments(Collections.singletonList(10L))
                .repository(winRepository)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<WinEntity, WinEntity> giveRewardProcessor() {

        return item -> {
            item.giveReward();

            return item;
        };
    }

    @Bean
    public RepositoryItemWriter<WinEntity> winWriter() {

        return new RepositoryItemWriterBuilder<WinEntity>()
                .repository(winRepository)
                .methodName("save")
                .build();
    }

}
