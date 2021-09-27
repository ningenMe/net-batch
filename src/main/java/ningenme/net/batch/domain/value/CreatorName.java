package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor(staticName = "of")
public class CreatorName {
    private final String value;

    public static List<CreatorName> getComicCreator(@NonNull final List<String> comicCreatorList) {
        return comicCreatorList.stream().map(CreatorName::of).collect(Collectors.toList());
    }
}
