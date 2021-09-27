package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Work;
import ningenme.net.batch.infrastructure.mysql.dto.WorkMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.WorkMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class WorkMysqlRepository {

    private final WorkMysqlMapper workMysqlMapper;

    public void post(@NonNull final List<Work> workList) {
        if (CollectionUtils.isEmpty(workList)) {
            return;
        }
        workMysqlMapper.insert(WorkMysqlDto.getWorkMysqlDtoList(workList));
    }
}
