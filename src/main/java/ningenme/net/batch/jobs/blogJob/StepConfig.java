package ningenme.net.batch.jobs.blogJob;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.model.StepName;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final HatenaTasklet hatenaTasklet;

    @Bean
    public Step HatenaStep() {
        return stepBuilderFactory.get(StepName.HATENA_STEP.getValue()) //Step名を指定
                                 .tasklet(hatenaTasklet) //実行するTaskletを指定
                                 .build();
    }

}
