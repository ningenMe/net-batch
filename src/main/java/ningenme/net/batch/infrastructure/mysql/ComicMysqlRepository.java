package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.infrastructure.mysql.dto.ComicMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.ComicMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComicMysqlRepository {

    private final ComicMysqlMapper comicMysqlMapper;

    public void post(@NonNull final List<Comic> comicList) {
        if (CollectionUtils.isEmpty(comicList)) {
            return;
        }
        comicMysqlMapper.insert(comicList
                .stream()
                .map(comic -> {
                    try {
                        return new ComicMysqlDto(comic);
                    }
                    catch (Exception ex) {
                        log.warn(ex.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
        );
    }

}
