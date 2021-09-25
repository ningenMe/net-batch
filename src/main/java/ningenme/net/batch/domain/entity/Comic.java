package ningenme.net.batch.domain.entity;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.value.Url;

@RequiredArgsConstructor
public class Comic {
    @NonNull
    private final Url url;
}
