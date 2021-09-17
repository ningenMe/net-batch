package ningenme.net.batch.infrastructure.hatena;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.domain.value.BlogTitle;
import ningenme.net.batch.domain.value.BlogType;
import ningenme.net.batch.domain.value.PostedDate;
import ningenme.net.batch.domain.value.Url;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Slf4j
public class BlogHatenaRepository {

    private final static String URL = "https://ningenme.hatenablog.com/archive?page=";
    private final static Pattern PATTERN = Pattern.compile("/");
    private final static Integer PAGE_COUNT = 10;

    public List<Blog> getBlog() {
        List<Blog> blogList = List.of();
        try {
            for (Integer i = 1; i < PAGE_COUNT; i += 1) {
                blogList = Stream.concat(blogList.stream(),
                                         getBlog(i).stream())
                                 .collect(Collectors.toList());
            }
        } catch (Exception ignored) {
        }
        return blogList;
    }

    private List<Blog> getBlog(@NonNull final Integer page) {
        try {
            Document document = Jsoup.connect(URL + page).get();
            Elements elements = document.select("[class=archive-entry-header]").select("[class=entry-title-link]");

            List<Blog> blogList = new ArrayList<>();
            Integer size = elements.size();

            for (Integer index = 0; index < size; index += 1) {
                final String title = elements.get(index).text();
                final String url = elements.get(index).attr("href");
                final List<String> list = Arrays.asList(PATTERN.split(url));
                log.info("get " + title);
                try {
                    blogList.add(Blog.builder()
                                     .blogType(BlogType.HATENA)
                                     .blogTitle(BlogTitle.of(title))
                                     .postedDate(PostedDate.of(LocalDate.parse(list.get(4) + "-" + list.get(5) + "-" + list.get(6))))
                                     .url(Url.of(url))
                                     .build());
                } catch (Exception ignored) {
                }
            }
            return blogList;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
