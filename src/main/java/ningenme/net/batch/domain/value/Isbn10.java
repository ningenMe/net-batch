package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class Isbn10 {

    private final static Pattern PATTERN = Pattern.compile("^[0-9]{9}[0-9X]$");

    @NonNull
    private final String value;

    public static Isbn10 of(@NonNull final String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new RuntimeException(value + " is invalid isbn10");
        }
        return new Isbn10(value);
    }

}
