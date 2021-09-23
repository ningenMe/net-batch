package ningenme.net.batch.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NetBatchSqsConfig {
    @Value("${net.batch.sqs.user-access-key-id}")
    private String userAccessKeyId;
    @Value("${net.batch.sqs.user-secret-access-key}")
    private String userSecretAccessKey;
    @Value("${net.batch.sqs.url-problem-sqs}")
    private String urlProblemSqs;
}
