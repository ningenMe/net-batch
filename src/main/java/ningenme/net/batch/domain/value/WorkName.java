package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkName {

    @NonNull
    private final String value;

    private static final Pattern PATTERN = Pattern.compile(".*(\\(|（)[0-9]+(\\)|）)");

    public static WorkName of(@NonNull final ComicName comicName) {
        final Integer len = comicName.getValue().length();
        String value = comicName.getValue().substring(1, len - 1);
        if (PATTERN.matcher(value).matches()) {
            final Integer lastIndex = Math.max(value.lastIndexOf("("), value.lastIndexOf("（"));
            value = value.substring(0, lastIndex);
        }
        return new WorkName(value);
    }
}
