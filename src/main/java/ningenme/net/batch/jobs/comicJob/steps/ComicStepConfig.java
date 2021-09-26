package ningenme.net.batch.jobs.comicJob.steps;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.StepName;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ComicStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final ComicTasklet comicTasklet;

    @Bean
    public Step ComicStep() {
        return stepBuilderFactory.get(StepName.COMIC_STEP.getValue())
                                 .tasklet(comicTasklet)
                                 .build();
    }

}
