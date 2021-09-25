package ningenme.net.batch.infrastructure.comicNatalie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.infrastructure.comicNatalie.dto.PageComicNatalieDto;
import ningenme.net.batch.infrastructure.comicNatalie.mapper.ComicNatalieMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
                final List<PageComicNatalieDto> pageComicNatalieDtoList = comicNatalieMapper.getPageUrl(String.format(URL, pageNumber));
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


}
