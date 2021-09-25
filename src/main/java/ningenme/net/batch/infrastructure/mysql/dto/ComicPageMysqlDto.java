package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.ComicPage;

import java.time.LocalDateTime;

@Data
public class ComicPageMysqlDto {
    private String url;
    private String name;
    private LocalDateTime processedTime;

    public ComicPageMysqlDto(@NonNull final ComicPage comicPage) {
        url = comicPage.getUrl().getValue();
        name = comicPage.getName();
        processedTime = comicPage.getProcessedTime();
    }
}
