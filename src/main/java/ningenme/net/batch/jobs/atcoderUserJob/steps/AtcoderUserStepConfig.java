package ningenme.net.batch.jobs.atcoderUserJob.steps;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.StepName;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AtcoderUserStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final AtcoderUserPublishTasklet atcoderUserPublishTasklet;

    @Bean
    public Step AtcoderUserPublishStep() {
        return stepBuilderFactory.get(StepName.ATCODER_USER_PUBLISH_STEP.getValue())
                                 .tasklet(atcoderUserPublishTasklet)
                                 .build();
    }

}
