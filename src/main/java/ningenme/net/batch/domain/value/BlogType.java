package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BlogType {
  HATENA("HATENA"),
  QIITA("QIITA"),
  AMEBA("AMEBA"),
  DIARY("DIARY"),
  ;
  private final String value;
  public static BlogType of(@NonNull final String value) {
    for (BlogType blogType: BlogType.values()) {
      if(Objects.equals(blogType.getValue(),value)) {
        return blogType;
      }
    }
    throw new RuntimeException(value + "is an unknown blog type");
  }
  public static List<String> of(@NonNull final List<BlogType> blogTypeList) {
    return blogTypeList.stream()
                       .map(BlogType::getValue)
                       .collect(Collectors.toList());
  }
}
