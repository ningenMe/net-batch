package ningenme.net.batch.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.value.BlogTitle;
import ningenme.net.batch.domain.value.BlogType;
import ningenme.net.batch.domain.value.Liked;
import ningenme.net.batch.domain.value.PostedDate;
import ningenme.net.batch.domain.value.Url;

@Data
@Builder(toBuilder = true)
@Slf4j
public class Blog {
    @NonNull
    private final BlogType blogType;
    @NonNull
    private final BlogTitle blogTitle;
    @NonNull
    private final PostedDate postedDate;
    @NonNull
    private final Url url;
    @NonNull
    @Builder.Default
    private final Liked liked = Liked.of();

}
