package ningenme.net.batch.domain.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.ComicCreator;
import ningenme.net.batch.domain.value.ComicName;
import ningenme.net.batch.domain.value.ComicPublisher;
import ningenme.net.batch.domain.value.Url;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Comic {
    private final Url url;
    private final ComicName comicName;
    private final ComicPublisher comicPublisher;
    private final List<ComicCreator> comicCreatorList;
}
