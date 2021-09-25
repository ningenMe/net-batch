package ningenme.net.batch.jobs.comicPageJob.steps;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.StepName;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ComicPageStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final ComicPageTasklet comicPageTasklet;

    @Bean
    public Step ComicPageStep() {
        return stepBuilderFactory.get(StepName.COMIC_PAGE_STEP.getValue())
                                 .tasklet(comicPageTasklet)
                                 .build();
    }

}
