package ningenme.net.batch.domain.value;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ComicCreator {
    private final String value;

    public static List<ComicCreator> getComicCreator(@NonNull final List<String> comicCreatorList) {
        return comicCreatorList.stream().map(ComicCreator::of).collect(Collectors.toList());
    }
}
