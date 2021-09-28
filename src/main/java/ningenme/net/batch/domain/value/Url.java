package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Url {
    private final String value;

    private final static Pattern PATTERN = Pattern.compile("^https?://.*");

    public static Url of(@NonNull final String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(value + " is invalid url");
        }
        return new Url(value);
    }
}
