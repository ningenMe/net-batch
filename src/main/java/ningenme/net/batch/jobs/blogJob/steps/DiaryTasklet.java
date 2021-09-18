package ningenme.net.batch.jobs.blogJob.steps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.service.DiaryService;
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
public class DiaryTasklet implements Tasklet {

    private final DiaryService diaryService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        diaryService.batchDiary();
        return RepeatStatus.FINISHED;
    }
}
