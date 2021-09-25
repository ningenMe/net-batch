package ningenme.net.batch.infrastructure.comicNatalie.dto;

import lombok.Data;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.domain.value.Url;

import java.time.LocalDateTime;

@Data
public class PageComicNatalieDto {
    private String url;
    private String name;

    public ComicPage getComicPage() {
        return new ComicPage(Url.of(url), name, LocalDateTime.now().minusYears(1L));
    }
}
