package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Comic;

@Data
public class ComicMysqlDto {
    private String url;
    private String comicName;
    private String publisherName;

    public ComicMysqlDto(@NonNull final Comic comic) {
        url = comic.getUrl().getValue();
        comicName = comic.getComicName().getValue();
        publisherName = comic.getComicPublisher().getValue();
    }
}
