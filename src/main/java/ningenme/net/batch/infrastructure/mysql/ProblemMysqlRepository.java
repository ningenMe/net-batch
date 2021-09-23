package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Problem;
import ningenme.net.batch.infrastructure.mysql.dto.ProblemMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.ProblemMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProblemMysqlRepository {
    private final ProblemMysqlMapper problemMysqlMapper;

    public void post(@NonNull final List<Problem> problemList) {
        if (CollectionUtils.isEmpty(problemList)) {
            return;
        }
        problemMysqlMapper.insert(ProblemMysqlDto.getProblemMysqlDtoList(problemList));
    }

}
