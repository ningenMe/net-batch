package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.infrastructure.mysql.dto.ComicMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.ComicMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ComicMysqlRepository {

    private final ComicMysqlMapper comicMysqlMapper;

    public void post(@NonNull final List<Comic> comicList) {
        if (CollectionUtils.isEmpty(comicList)) {
            return;
        }
        comicMysqlMapper.insert(comicList.stream()
                                         .map(ComicMysqlDto::new)
                                         .collect(Collectors.toList())
                               );
    }

}
