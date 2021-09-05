package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StepName {

    AMEBA_STEP("amebaStep"),
    HATENA_STEP("hatenaStep"),
    ;

    private final String value;

}
