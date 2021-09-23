package ningenme.net.batch.jobs.blogJob;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.JobName;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class BlogJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job BlogJob(@Qualifier("AmebaStep") Step amebaStep,
                       @Qualifier("HatenaStep") Step hatenaStep,
                       @Qualifier("QiitaStep") Step qiitaStep,
                       @Qualifier("DiaryStep") Step diaryStep) {
        return jobBuilderFactory.get(JobName.BLOG_JOB.getValue())
                                .incrementer(new RunIdIncrementer())
                                .start(amebaStep)
                                .next(hatenaStep)
                                .next(qiitaStep)
                                .next(diaryStep)
                                .build();
    }

}
