package ningenme.net.batch.infrastructure.comicNatalie.mapper;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.infrastructure.comicNatalie.dto.PageComicNatalieDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ComicNatalieMapper {

    public List<PageComicNatalieDto> getPageUrl(@NonNull final String url) {
        final List<PageComicNatalieDto> pageComicNatalieDtoList = new ArrayList<>();
        try {
            final Document document = Jsoup.connect(url).get();
            final Elements elements = document.select("[class=NA_card NA_card-l]");
            for (final Element element : elements) {
                final PageComicNatalieDto pageComicNatalieDto = new PageComicNatalieDto();
                for (Element e1 : element.select("a")) {
                    final String tmp = e1.attr("href");
                    if (tmp.startsWith("https")) {
                        pageComicNatalieDto.setUrl(tmp);
                    }
                }
                pageComicNatalieDto.setName(element.select("[class=NA_card_title]").text());

                pageComicNatalieDtoList.add(pageComicNatalieDto);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return pageComicNatalieDtoList;
    }
}
