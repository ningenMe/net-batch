package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.domain.value.Url;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ComicPageMysqlDto {
    private String url;
    private String name;
    private LocalDateTime processedTime;

    public ComicPageMysqlDto(@NonNull final ComicPage comicPage) {
        url = comicPage.getUrl().getValue();
        name = comicPage.getName();
        processedTime = comicPage.getProcessedTime();
    }

    public ComicPage getComicPage() {
        return ComicPage.builder()
                        .url(Url.of(url))
                        .name(name)
                        .processedTime(processedTime)
                        .build();
    }
}
