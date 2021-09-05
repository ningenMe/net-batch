package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.NonNull;

@Data
public class Liked {
  private final Integer value;
  private static final Integer MAX_VALUE=10000;

  public static Liked of(@NonNull final Integer value) {
    return new Liked(value);
  }

  public static Liked of() {
    return new Liked(0);
  }

  public Liked plusOne() {
    return Liked.of(Math.min(value+1,MAX_VALUE));
  }
}
