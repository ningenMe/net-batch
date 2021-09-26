package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ComicName {
    private final String value;
}
