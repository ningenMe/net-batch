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

  private final static Pattern pattern = Pattern.compile("^https?://.*");

  public static Url of(@NonNull final String value) {
    if(!pattern.matcher(value).matches()) {
      throw new IllegalArgumentException("invalid url");
    }
    return new Url(value);
  }
}
