package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.NonNull;

@Data
public class Estimation {
    private final Integer value;

    public static Estimation of(@NonNull final Integer value) {
        return new Estimation(value);
    }
}
