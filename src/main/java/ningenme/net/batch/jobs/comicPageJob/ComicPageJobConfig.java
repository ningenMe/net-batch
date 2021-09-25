package ningenme.net.batch.jobs.comicPageJob;

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
public class ComicPageJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job ComicPageJob(@Qualifier("ComicPageStep") Step comicPageStep) {
        return jobBuilderFactory.get(JobName.COMIC_PAGE_JOB.getValue())
                                .incrementer(new RunIdIncrementer())
                                .start(comicPageStep)
                                .build();
    }

}
