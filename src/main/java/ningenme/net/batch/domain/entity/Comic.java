package ningenme.net.batch.domain.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.ComicName;
import ningenme.net.batch.domain.value.ComicUrl;
import ningenme.net.batch.domain.value.CreatorName;
import ningenme.net.batch.domain.value.Isbn13;
import ningenme.net.batch.domain.value.PublishedDate;
import ningenme.net.batch.domain.value.PublisherName;
import ningenme.net.batch.domain.value.Url;
import ningenme.net.batch.domain.value.WorkName;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Comic {
    @NonNull
    private final Url url;
    @NonNull
    private final ComicUrl comicUrl;
    @NonNull
    private final ComicName comicName;
    @NonNull
    private final PublisherName publisherName;
    @NonNull
    private final List<CreatorName> creatorNameList;
    @NonNull
    private final PublishedDate publishedDate;

    public Work getWork() {
        return Work.of(WorkName.of(comicName), publisherName, creatorNameList);
    }

    public Isbn13 getIsbn13() {
        return comicUrl.getIsbn13();
    }
}
