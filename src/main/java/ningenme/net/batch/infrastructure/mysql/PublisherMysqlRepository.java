package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.infrastructure.mysql.dto.PublisherMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.PublisherMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class PublisherMysqlRepository {

    private final PublisherMysqlMapper publisherMysqlMapper;

    public void post(@NonNull final List<Comic> comicList) {
        if (CollectionUtils.isEmpty(comicList)) {
            return;
        }
        publisherMysqlMapper.insert(comicList.stream()
                                             .map(PublisherMysqlDto::new)
                                             .collect(Collectors.toList()));
    }
}
