package ningenme.net.batch.domain.value;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class Isbn13 {

    private final static Pattern PATTERN = Pattern.compile("^[0-9]{13}$");

    @NonNull
    private final String value;

    public static Isbn13 of(@NonNull final String value) {
        if (!PATTERN.matcher(value).matches()) {
            return Isbn13.of(Isbn10.of(value.substring(0, 10)));
        }
        return new Isbn13(value);
    }

    public static Isbn13 of(@NonNull final Isbn10 isbn10) {
        final List<Integer> integerList = Stream.concat(
                                                    Stream.of(9, 7, 8),
                                                    Arrays.stream(isbn10.getValue().substring(0, 9).split(""))
                                                          .map(Integer::parseInt))
                                                .collect(Collectors.toList());
        Integer sum = 0;
        for (Integer i = 0; i < integerList.size(); i += 1) {
            if (i % 2 == 0) {
                sum += integerList.get(i);
            } else {
                sum += integerList.get(i) * 3;
            }
        }
        final Integer digit = (10 - sum % 10) % 10;
        final List<String> stringList = Stream.concat(integerList.stream(), Stream.of(digit))
                                              .map(String::valueOf)
                                              .collect(Collectors.toList());
        final String value = String.join("", stringList);
        if (!PATTERN.matcher(value).matches()) {
            throw new RuntimeException(value + " is invalid isbn13");
        }
        return new Isbn13(value);
    }
}
