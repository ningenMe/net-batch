package ningenme.net.batch.infrastructure.sqs;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Problem;
import ningenme.net.batch.infrastructure.sqs.client.ProblemSqsClient;
import ningenme.net.batch.infrastructure.sqs.client.dto.ProblemSqsDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProblemSqsRepository {

    private final ProblemSqsClient problemSqsClient;

    public List<Problem> getProblemList() {
        final List<ProblemSqsDto> problemSqsDtoList = problemSqsClient.subscribe();
        return problemSqsDtoList.stream()
                                .map(ProblemSqsDto::getProblem)
                                .collect(Collectors.toList());
    }

    public void deleteProblem(@NonNull final List<Problem> problemList) {
        problemSqsClient.delete(
            problemList.stream()
                       .map(Problem::getReceiptHandle)
                       .collect(Collectors.toList()));
    }

}
