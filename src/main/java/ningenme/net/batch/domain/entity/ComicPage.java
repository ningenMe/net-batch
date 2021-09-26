package ningenme.net.batch.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.value.Url;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ComicPage {
    @NonNull
    private final Url url;
    private final String name;
    private final LocalDateTime processedTime;
}
