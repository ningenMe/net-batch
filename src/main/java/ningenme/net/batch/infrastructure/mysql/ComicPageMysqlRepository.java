package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.infrastructure.mysql.dto.ComicPageMysqlDto;
import ningenme.net.batch.infrastructure.mysql.mapper.ComicPageMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ComicPageMysqlRepository {

    private final ComicPageMysqlMapper comicPageMysqlMapper;

    public void post(@NonNull final List<ComicPage> comicPageList) {
        if (CollectionUtils.isEmpty(comicPageList)) {
            return;
        }
        comicPageMysqlMapper.insert(comicPageList.stream()
                                                 .map(ComicPageMysqlDto::new)
                                                 .collect(Collectors.toList())
                                   );
    }

    public void put(@NonNull final ComicPage comicPage) {
        comicPageMysqlMapper.update(new ComicPageMysqlDto(comicPage));
    }

    public List<ComicPage> get() {
        return comicPageMysqlMapper.selectOldest()
                                   .stream()
                                   .map(ComicPageMysqlDto::getComicPage)
                                   .collect(Collectors.toList());
    }
}
