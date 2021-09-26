package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor(staticName = "of")
public class PublishedDate {
    @NonNull
    private final LocalDate value;
}
