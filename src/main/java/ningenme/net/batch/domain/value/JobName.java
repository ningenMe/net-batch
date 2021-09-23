package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum JobName {

    BLOG_JOB("blogJob"),
    ATCODER_USER_JOB("atcoderUserJob"),
    ;

    private final String value;

}
