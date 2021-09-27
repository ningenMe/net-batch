package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Work;
import ningenme.net.batch.infrastructure.mysql.dto.RelationCreatorWorkMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.RelationCreatorWorkMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Repository
@Slf4j
public class RelationCreatorWorkMysqlRepository {

    private final RelationCreatorWorkMysqlMapper relationCreatorWorkMysqlMapper;

    public void post(@NonNull final List<Work> workList) {
        if (CollectionUtils.isEmpty(workList)) {
            return;
        }
        relationCreatorWorkMysqlMapper.insert(workList.stream()
                                                      .reduce(List.of(),
                                                              (list, work) -> Stream.concat(list.stream(), RelationCreatorWorkMysqlDto.getRelationCreatorComicMysqlDto(work).stream())
                                                                                    .collect(Collectors.toList()),
                                                              (list1, list2) -> Stream.concat(list1.stream(), list2.stream())
                                                                                      .collect(Collectors.toList()))
                                             );
    }
}
