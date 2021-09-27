package ningenme.net.batch.domain.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.CreatorName;
import ningenme.net.batch.domain.value.PublisherName;
import ningenme.net.batch.domain.value.WorkName;

import java.util.List;

@RequiredArgsConstructor(staticName = "of")
@Data
public class Work {

    @NonNull
    private final WorkName workName;
    @NonNull
    private final PublisherName publisherName;
    @NonNull
    private final List<CreatorName> creatorNameList;

}
