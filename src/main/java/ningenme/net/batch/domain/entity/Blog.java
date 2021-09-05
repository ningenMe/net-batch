package ningenme.net.batch.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.value.BlogTitle;
import ningenme.net.batch.domain.value.BlogType;
import ningenme.net.batch.domain.value.Liked;
import ningenme.net.batch.domain.value.PostedDate;
import ningenme.net.batch.domain.value.Url;
import ningenme.net.batch.infrastructure.mysql.dto.BlogDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@Slf4j
public class Blog {
  @NonNull
  private final BlogType blogType;
  @NonNull
  private final BlogTitle blogTitle;
  @NonNull
  private final PostedDate postedDate;
  @NonNull
  private final Url url;
  @NonNull
  @Builder.Default
  private final Liked liked = Liked.of();

  private BlogDto getBlogDto() {
    BlogDto blogDto = new BlogDto();
    {
      blogDto.setBlogType(blogType.getValue());
      blogDto.setBlogTitle(blogTitle.getValue());
      blogDto.setPostedDate(postedDate.getValue());
      blogDto.setUrl(url.getValue());
      blogDto.setLiked(liked.getValue());
    }
    return blogDto;
  }

  public static Blog of(@NonNull final BlogDto blogDto) {
    return Blog.builder()
               .blogType(BlogType.of(blogDto.getBlogType()))
               .blogTitle(BlogTitle.of(blogDto.getBlogTitle()))
               .postedDate(PostedDate.of(blogDto.getPostedDate()))
               .url(Url.of(blogDto.getUrl()))
               .liked(Liked.of(blogDto.getLiked()))
               .build();
  }

  public static List<BlogDto> getBlogDtoList(@NonNull final List<Blog> blogList) {
    return blogList.stream()
                   .map(Blog::getBlogDto)
                   .collect(Collectors.toList());
  }

  public static List<Blog> fromBlogDtoList(@NonNull final List<BlogDto> blogDtoList) {
    return blogDtoList.stream()
                      .map(Blog::of)
                      .collect(Collectors.toList());
  }
}
