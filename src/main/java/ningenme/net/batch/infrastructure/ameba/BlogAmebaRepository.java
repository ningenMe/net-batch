package ningenme.net.batch.infrastructure.ameba;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.domain.value.BlogTitle;
import ningenme.net.batch.domain.value.BlogType;
import ningenme.net.batch.domain.value.PostedDate;
import ningenme.net.batch.domain.value.Url;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Slf4j
public class BlogAmebaRepository {

  private final static String URL = "https://ameblo.jp/ningenme/page-%d.html";
  private final static String DOMAIN = "https://ameblo.jp";
  private final static Pattern PATTERN = Pattern.compile(",");
  private final static Integer PAGE_COUNT = 200;

  public List<Blog> getBlog() {
    List<Blog> blogList = List.of();
    try {
      for (Integer i = 1; i < PAGE_COUNT; i += 1) {
        blogList = Stream.concat(blogList.stream(),
                                 getBlog(i).stream())
                         .collect(Collectors.toList());
        Thread.sleep(500);
      }
    } catch (Exception ex) {
    }
    return blogList;
  }

  private List<Blog> getBlog(@NonNull final Integer page) {
    try {
      Document document = Jsoup.connect(String.format(URL,page)).get();
      final String title = document.select("article").select("h2").text();
      final String url   = DOMAIN + document.select("article").select("h2").select("[class=skinArticleTitle]").attr("href");
      final List<String> list = Arrays.asList(PATTERN.split(document.select("[type=application/ld+json]").toString()));
      final String postedTime = list.stream()
                                    .filter(element -> element.startsWith("\"dateModified"))
                                    .collect(Collectors.joining())
                                    .substring(16);
      log.info("get " + title);
      return Collections.singletonList(
              Blog.builder()
                  .blogType(BlogType.AMEBA)
                  .blogTitle(BlogTitle.of(title))
                  .postedDate(PostedDate.of(postedTime))
                  .url(Url.of(url))
                  .build()
                                      );
    }
    catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
