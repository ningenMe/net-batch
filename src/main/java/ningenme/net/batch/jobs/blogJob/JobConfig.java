package ningenme.net.batch.jobs.blogJob;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.JobName;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job helloWorldJob(Step hatenaStep) {
        return jobBuilderFactory.get(JobName.BLOG_JOB.getValue())
                                .incrementer(new RunIdIncrementer())
                                .start(hatenaStep)
                                .build();
    }

}