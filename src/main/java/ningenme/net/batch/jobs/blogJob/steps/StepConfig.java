package ningenme.net.batch.jobs.blogJob.steps;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.StepName;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final AmebaTasklet amebaTasklet;
    private final HatenaTasklet hatenaTasklet;
    private final QiitaTasklet qiitaTasklet;
    private final DiaryTasklet diaryTasklet;

    @Bean
    public Step HatenaStep() {
        return stepBuilderFactory.get(StepName.HATENA_STEP.getValue())
                                 .tasklet(hatenaTasklet)
                                 .build();
    }

    @Bean
    public Step AmebaStep() {
        return stepBuilderFactory.get(StepName.AMEBA_STEP.getValue())
                                 .tasklet(amebaTasklet)
                                 .build();
    }

    @Bean
    public Step QiitaStep() {
        return stepBuilderFactory.get(StepName.QIITA_STEP.getValue())
                                 .tasklet(qiitaTasklet)
                                 .build();
    }

    @Bean
    public Step DiaryStep() {
        return stepBuilderFactory.get(StepName.DIARY_STEP.getValue())
                                 .tasklet(diaryTasklet)
                                 .build();
    }

}
