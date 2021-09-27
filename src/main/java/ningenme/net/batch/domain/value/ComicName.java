package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ComicName {
    @NonNull
    private final String value;

    private static final Pattern PATTERN = Pattern.compile("^「.*」$");

    public static ComicName of(@NonNull final String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(value + "is invalid as comic name");
        }
        return new ComicName(value);
    }
}
