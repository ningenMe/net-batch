package ningenme.net.batch.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StepName {

    HATENA_STEP("hatenaStep"),
    ;

    private final String value;

}
