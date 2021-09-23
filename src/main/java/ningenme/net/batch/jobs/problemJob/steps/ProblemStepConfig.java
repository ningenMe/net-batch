package ningenme.net.batch.jobs.problemJob.steps;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.StepName;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ProblemStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final ProblemSubscribeTasklet problemSubscribeTasklet;

    @Bean
    public Step ProblemSubscribeStep() {
        return stepBuilderFactory.get(StepName.PROBLEM_SUBSCRIBE_STEP.getValue())
                                 .tasklet(problemSubscribeTasklet)
                                 .build();
    }

}
