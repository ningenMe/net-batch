package ningenme.net.batch.infrastructure.comicNatalie;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.domain.value.Url;
import ningenme.net.batch.infrastructure.comicNatalie.dto.PageComicNatalieDto;
import ningenme.net.batch.infrastructure.comicNatalie.mapper.ComicNatalieMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComicNatalieRepository {

    private final static String URL = "https://natalie.mu/comic/tag/416/page/%d";
    private final static Integer PAGE_COUNT = 120;

    private final ComicNatalieMapper comicNatalieMapper;

    public List<ComicPage> getComicPageList() {
        final List<ComicPage> comicPageList = new ArrayList<>();
        for (Integer pageNumber = 1; pageNumber < PAGE_COUNT; pageNumber += 1) {
            try {
                Thread.sleep(5000);
                final List<PageComicNatalieDto> pageComicNatalieDtoList = comicNatalieMapper.getPage(String.format(URL, pageNumber));
                pageComicNatalieDtoList.forEach(dto -> {
                    try {
                        comicPageList.add(dto.getComicPage());
                        log.info(dto.toString());
                    } catch (Exception exception) {
                    }
                });
            } catch (Exception exception) {
            }
        }
        return comicPageList;
    }

    public List<Comic> getComicList(@NonNull final Url url) {
        try {
            Thread.sleep(10000L);
            return comicNatalieMapper.getComic(url.getValue())
                                     .stream()
                                     .map(comicComicNatalieDto -> {
                                         try {
                                             return comicComicNatalieDto.getComic();
                                         } catch (Exception ex) {
                                             log.error(ex.getMessage());
                                             return null;
                                         }
                                     })
                                     .filter(Objects::nonNull)
                                     .collect(Collectors.toList());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return List.of();
    }


}
