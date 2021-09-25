package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum JobName {

    BLOG_JOB("blogJob"),
    PROBLEM_JOB("problemJob"),
    ATCODER_USER_JOB("atcoderUserJob"),
    COMIC_PAGE_JOB("comicPageJob"),
    ;

    private final String value;

}
