package ningenme.net.batch.domain.entity;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.value.Estimation;
import ningenme.net.batch.domain.value.Url;

@Data
public class Problem {

    @NonNull
    private final Estimation estimation;

    @NonNull
    private final Url url;

    @NonNull
    private final String receiptHandle;

}
