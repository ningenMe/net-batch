package ningenme.net.batch.infrastructure.comicNatalie.dto;

import lombok.Data;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.domain.value.ComicCreator;
import ningenme.net.batch.domain.value.ComicName;
import ningenme.net.batch.domain.value.ComicPublisher;
import ningenme.net.batch.domain.value.Url;

import java.util.List;

@Data
public class ComicComicNatalieDto {
    private String url;
    private String publisher;
    private String name;
    private List<String> creatorList;

    public Comic getComic() {
        return new Comic(Url.of(url),
                         ComicName.of(name),
                         ComicPublisher.of(publisher),
                         ComicCreator.getComicCreator(creatorList));
    }
}
