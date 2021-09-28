package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ComicUrl {

    private final static Pattern PATTERN = Pattern.compile("^https?://www.amazon.co.jp/exec/obidos/ASIN/[0-9]*X?/.*$");
    private final static String PREFIX_HTTP = "http://www.amazon.co.jp/exec/obidos/ASIN/";
    private final static String PREFIX_HTTPS = "https://www.amazon.co.jp/exec/obidos/ASIN/";

    @NonNull
    private final String value;

    public static ComicUrl of(@NonNull final Url url) {
        final String value = url.getValue();
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(value + " is invalid comic url");
        }
        return new ComicUrl(value);
    }

    public Isbn13 getIsbn13() {
        if (value.startsWith(PREFIX_HTTP)) {
            return Isbn13.of(value.substring(PREFIX_HTTP.length(), PREFIX_HTTP.length() + 13));
        }
        if (value.startsWith(PREFIX_HTTPS)) {
            return Isbn13.of(value.substring(PREFIX_HTTPS.length(), PREFIX_HTTPS.length() + 13));
        }
        throw new RuntimeException();
    }

}
