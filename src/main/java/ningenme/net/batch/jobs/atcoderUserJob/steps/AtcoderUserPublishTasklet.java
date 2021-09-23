package ningenme.net.batch.jobs.atcoderUserJob.steps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.service.AtcoderUserService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
@Slf4j
public class AtcoderUserPublishTasklet implements Tasklet {

    private final AtcoderUserService atcoderUserService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        atcoderUserService.batch();
        return RepeatStatus.FINISHED;
    }

}
