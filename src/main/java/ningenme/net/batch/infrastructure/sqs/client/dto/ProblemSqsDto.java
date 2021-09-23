package ningenme.net.batch.infrastructure.sqs.client.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import ningenme.net.batch.domain.entity.Problem;
import ningenme.net.batch.domain.value.Estimation;
import ningenme.net.batch.domain.value.Url;

@Data
public class ProblemSqsDto {
    @SerializedName("url")
    private String url;
    @SerializedName("estimation")
    private Integer estimation;

    private String receiptHandle;

    public Problem getProblem() {
        return new Problem(Estimation.of(estimation), Url.of(url), receiptHandle);
    }
}
