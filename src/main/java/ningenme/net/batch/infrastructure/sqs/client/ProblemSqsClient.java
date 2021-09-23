package ningenme.net.batch.infrastructure.sqs.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.common.config.NetBatchSqsConfig;
import ningenme.net.batch.infrastructure.sqs.client.dto.ProblemSqsDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProblemSqsClient {

    private final NetBatchSqsConfig netBatchSqsConfig;
    private static final Integer WAIT_TIME = 10;
    private static final Integer MESSAGE_NUMBER = 10;

    public List<ProblemSqsDto> subscribe() {
        final AWSCredentials awsCredentials = new BasicAWSCredentials(netBatchSqsConfig.getUserAccessKeyId(), netBatchSqsConfig.getUserSecretAccessKey());

        final AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                                                          .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                                                          .build();

        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
            .withQueueUrl(netBatchSqsConfig.getUrlProblemSqs())
            .withWaitTimeSeconds(WAIT_TIME)
            .withMaxNumberOfMessages(MESSAGE_NUMBER);

        final ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(receiveMessageRequest);

        return receiveMessageResult.getMessages()
                                   .stream()
                                   .map(message -> {
                                       String json = message.getBody();
                                       Gson gson = new Gson();
                                       Type type = new TypeToken<ProblemSqsDto>() {
                                       }.getType();
                                       ProblemSqsDto problemSqsDto = gson.<ProblemSqsDto>fromJson(json, type);
                                       problemSqsDto.setReceiptHandle(message.getReceiptHandle());
                                       return problemSqsDto;
                                   })
                                   .collect(Collectors.toList());
    }

    public void delete(@NonNull final List<String> receiptHandleList) {
        final AWSCredentials awsCredentials = new BasicAWSCredentials(netBatchSqsConfig.getUserAccessKeyId(), netBatchSqsConfig.getUserSecretAccessKey());

        final AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                                                          .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                                                          .build();

        receiptHandleList
            .forEach(receiptHandle -> amazonSQS.deleteMessage(netBatchSqsConfig.getUrlProblemSqs(), receiptHandle));
    }

}
