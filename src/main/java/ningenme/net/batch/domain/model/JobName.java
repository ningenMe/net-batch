package ningenme.net.batch.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum JobName {

    BLOG_JOB("blogJob"),
    ;

    private final String value;

}
