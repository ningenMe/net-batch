package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StepName {

    AMEBA_STEP("amebaStep"),
    HATENA_STEP("hatenaStep"),
    QIITA_STEP("qiitaStep"),
    DIARY_STEP("diaryStep"),

    PROBLEM_SUBSCRIBE_STEP("problemSubscribeStep"),

    COMIC_PAGE_STEP("comicPageStep"),
    COMIC_STEP("comicStep"),

    ATCODER_USER_PUBLISH_STEP("atcoderUserPublishStep"),
    ;

    private final String value;

}
