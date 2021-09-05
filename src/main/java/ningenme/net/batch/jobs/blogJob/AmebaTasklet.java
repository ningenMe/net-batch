package ningenme.net.batch.jobs.blogJob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.service.AmebaService;
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
public class AmebaTasklet implements Tasklet {

    private final AmebaService amebaService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        amebaService.batchAmeba();
        return RepeatStatus.FINISHED;
    }
}
