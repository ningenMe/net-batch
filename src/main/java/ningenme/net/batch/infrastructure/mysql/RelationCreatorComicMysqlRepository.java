package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.infrastructure.mysql.dto.RelationCreatorComicMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.RelationCreatorComicMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Repository
@Slf4j
public class RelationCreatorComicMysqlRepository {

    private final RelationCreatorComicMysqlMapper relationCreatorComicMysqlMapper;

    public void post(@NonNull final List<Comic> comicList) {
        if (CollectionUtils.isEmpty(comicList)) {
            return;
        }
        relationCreatorComicMysqlMapper.insert(comicList.stream()
                                                        .reduce(List.of(),
                                                                (list, comic) -> Stream.concat(list.stream(), RelationCreatorComicMysqlDto.getRelationCreatorComicMysqlDto(comic).stream())
                                                                                       .collect(Collectors.toList()),
                                                                (list1, list2) -> Stream.concat(list1.stream(), list2.stream())
                                                                                        .collect(Collectors.toList()))
                                              );
    }
}
