package ningenme.net.batch.jobs.comicJob;

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
public class ComicJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job ComicJob(@Qualifier("ComicStep") Step comicStep) {
        return jobBuilderFactory.get(JobName.COMIC_JOB.getValue())
                                .incrementer(new RunIdIncrementer())
                                .start(comicStep)
                                .build();
    }
}
