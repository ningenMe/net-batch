package ningenme.net.batch.infrastructure.comicNatalie.mapper;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.infrastructure.comicNatalie.dto.ComicComicNatalieDto;
import ningenme.net.batch.infrastructure.comicNatalie.dto.PageComicNatalieDto;
import org.javatuples.Triplet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class ComicNatalieMapper {

    private static final Pattern PATTERN_YEN = Pattern.compile("\\d.*円");
    private static final Pattern PATTERN_HOKA = Pattern.compile(".*ほか$");

    public List<PageComicNatalieDto> getPage(@NonNull final String url) {
        final List<PageComicNatalieDto> pageComicNatalieDtoList = new ArrayList<>();
        try {
            final Document document = Jsoup.connect(url).get();
            final Elements elements = document.select("[class=NA_card NA_card-l]");
            for (final Element element : elements) {
                final PageComicNatalieDto pageComicNatalieDto = new PageComicNatalieDto();
                for (Element e1 : element.select("a")) {
                    final String tmp = e1.attr("href");
                    if (tmp.startsWith("https")) {
                        pageComicNatalieDto.setUrl(tmp);
                    }
                }
                pageComicNatalieDto.setName(element.select("[class=NA_card_title]").text());

                pageComicNatalieDtoList.add(pageComicNatalieDto);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return pageComicNatalieDtoList;
    }

    public List<ComicComicNatalieDto> getComic(@NonNull final String url) {
        final List<ComicComicNatalieDto> comicComicNatalieDtoList = new ArrayList<>();

        final List<Triplet<Elements, Elements, Elements>> comicElementList = getComicElementList(url);
        for (final Triplet<Elements, Elements, Elements> triplet : comicElementList) {
            final LocalDate date = getDate(triplet.getValue0().text());
            final String publisher = triplet.getValue1().text();
            final List<String> comicStringList = Arrays.asList(triplet.getValue2().toString().split("<br>|<br/>"));

            for (final String comicString : comicStringList) {
                final Document document = Jsoup.parse(getHtml(comicString));
                if (!document.hasText()) {
                    continue;
                }
                final String comicUrl = document.select("a").first().attr("href");
                final String comicName = document.select("a").first().text();

                List<String> creatorList = getCreatorList(comicString);
                for (Element element : document.select("a")) {
                    if (Objects.isNull(element) || !element.hasText() || element.select("[class=GAE_inlineArtist]").isEmpty()) {
                        continue;
                    }
                    creatorList = Stream.concat(creatorList.stream(), Stream.of(element.select("[class=GAE_inlineArtist]").text()))
                                        .collect(Collectors.toList());
                }
                final List<String> filteredCreatorList = getFilteredCreatorList(creatorList);

                ComicComicNatalieDto comicComicNatalieDto = new ComicComicNatalieDto();
                {
                    comicComicNatalieDto.setUrl(comicUrl);
                    comicComicNatalieDto.setPublisher(publisher);
                    comicComicNatalieDto.setName(comicName);
                    comicComicNatalieDto.setCreatorList(filteredCreatorList);
                    comicComicNatalieDto.setDate(date);
                    if (ObjectUtils.isEmpty(publisher) || ObjectUtils.isEmpty(comicName)) {
                        log.warn("comicUrl={}, publisher={}, comicName={}, creatorList={}", comicUrl, publisher, comicName, filteredCreatorList.toString());
                    }
                }
                comicComicNatalieDtoList.add(comicComicNatalieDto);
            }
        }
        return comicComicNatalieDtoList;
    }

    private LocalDate getDate(@NonNull final String dateString) {
        final List<String> tmpList = Arrays.asList(dateString.split("年|月|日"));
        final String date = String.format("%04d-%02d-%02d", Integer.valueOf(tmpList.get(0)), Integer.valueOf(tmpList.get(1)), Integer.valueOf(tmpList.get(2)));
        return LocalDate.parse(date);
    }

    private String getHtml(@NonNull final String comicString) {
        Integer idx = 0;
        for (Integer i = 0; i < comicString.length(); ++i) {
            if (Objects.equals(comicString.charAt(i), '>')) {
                idx = i;
            }
        }
        return comicString.substring(0, idx + 1);
    }

    private List<String> getCreatorList(@NonNull final String comicString) {
        final String tmpComicString = comicString.replaceAll("<p>", "").replaceAll("</p>", "");
        Integer idx = 0;
        for (Integer i = 0; i < tmpComicString.length(); ++i) {
            if (Objects.equals(tmpComicString.charAt(i), '>')) {
                idx = i;
            }
        }
        return Arrays.stream(tmpComicString.substring(idx + 1).split(" |　"))
                     .reduce(
                         List.of(),
                         (list, str) -> Stream.concat(list.stream(), Arrays.stream(str.split("/"))).collect(Collectors.toList()),
                         (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList())
                            );
    }

    private List<String> getFilteredCreatorList(@NonNull final List<String> creatorList) {
        return creatorList.stream()
                          .filter(creator -> !ObjectUtils.isEmpty(creator))
                          .filter(creator -> !PATTERN_YEN.matcher(creator).matches())
                          .map(creator -> {
                              if (PATTERN_HOKA.matcher(creator).matches()) {
                                  return creator.substring(0, creator.length() - 2);
                              }
                              return creator;
                          })
                          .collect(Collectors.toList());
    }

    private List<Triplet<Elements, Elements, Elements>> getComicElementList(@NonNull final String url) {
        final List<Triplet<Elements, Elements, Elements>> entryList = new ArrayList<>();
        try {
            final Document document = Jsoup.connect(url).get();
            final Elements dateElements = document.select("[class=NA_article_date]");
            final Elements elements = document.select("[class=NA_article_body]").first().children();

            Elements h2Elements = new Elements();
            Elements pElements = new Elements();
            for (final Element element : elements) {
                final Elements h2Tmp = element.select("h2");
                final Elements pTmp = element.select("p");
                final Elements paTmp = element.select("p").select("a");
                if (!h2Tmp.isEmpty()) {
                    h2Elements = h2Tmp;
                }
                if (!paTmp.isEmpty() && paTmp.toString().length() >= 30 && paTmp.text().length() >= 4) {
                    pElements = pTmp;
                }
                if (!h2Elements.isEmpty() && !pElements.isEmpty()) {
                    entryList.add(new Triplet<>(dateElements, h2Elements, pElements));
                    h2Elements = new Elements();
                    pElements = new Elements();
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return entryList;
    }
}
