package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class PublisherName {
    @NonNull
    private final String value;
}
