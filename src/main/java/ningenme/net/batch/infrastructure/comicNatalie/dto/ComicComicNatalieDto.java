package ningenme.net.batch.infrastructure.comicNatalie.dto;

import lombok.Data;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.domain.value.ComicName;
import ningenme.net.batch.domain.value.CreatorName;
import ningenme.net.batch.domain.value.PublishedDate;
import ningenme.net.batch.domain.value.PublisherName;
import ningenme.net.batch.domain.value.Url;

import java.time.LocalDate;
import java.util.List;

@Data
public class ComicComicNatalieDto {
    private String url;
    private String publisher;
    private String name;
    private List<String> creatorList;
    private LocalDate date;

    public Comic getComic() {
        return new Comic(Url.of(url),
                         ComicName.of(name),
                         PublisherName.of(publisher),
                         CreatorName.getComicCreator(creatorList),
                         PublishedDate.of(date));
    }
}
