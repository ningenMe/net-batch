package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Comic;

import java.time.LocalDate;

@Data
public class ComicMysqlDto {
    private String url;
    private String comicName;
    private String workName;
    private LocalDate publishedDate;

    public ComicMysqlDto(@NonNull final Comic comic) {
        url = comic.getUrl().getValue();
        comicName = comic.getComicName().getValue();
        workName = comic.getWork().getWorkName().getValue();
        publishedDate = comic.getPublishedDate().getValue();
    }
}
