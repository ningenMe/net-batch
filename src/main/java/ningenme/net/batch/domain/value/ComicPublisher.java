package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ComicPublisher {
    private final String value;
}
