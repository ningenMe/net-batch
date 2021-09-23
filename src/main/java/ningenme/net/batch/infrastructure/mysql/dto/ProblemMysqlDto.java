package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Problem;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProblemMysqlDto {
    private String url;
    private Integer estimation;

    private ProblemMysqlDto(@NonNull final Problem problem) {
        url = problem.getUrl().getValue();
        estimation = problem.getEstimation().getValue();
    }

    public static List<ProblemMysqlDto> getProblemMysqlDtoList(@NonNull final List<Problem> problemList) {
        return problemList.stream()
                          .map(ProblemMysqlDto::new)
                          .collect(Collectors.toList());
    }

}
