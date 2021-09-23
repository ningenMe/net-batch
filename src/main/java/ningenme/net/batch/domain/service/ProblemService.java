package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Problem;
import ningenme.net.batch.infrastructure.mysql.ProblemMysqlRepository;
import ningenme.net.batch.infrastructure.sqs.ProblemSqsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemService {

    private final ProblemSqsRepository problemSqsRepository;
    private final ProblemMysqlRepository problemMysqlRepository;

    public void batchProblemSubscribe() {
        //mqからproblem取得
        final List<Problem> problemList = problemSqsRepository.getProblemList();
        problemList.forEach(problem -> log.info(problem.toString()));
        //mysqlにproblemをinsert
        problemMysqlRepository.post(problemList);
        //mqからqueueを削除
        problemSqsRepository.deleteProblem(problemList);
    }
}

