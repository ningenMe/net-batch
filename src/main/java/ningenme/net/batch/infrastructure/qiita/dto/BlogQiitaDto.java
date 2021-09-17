package ningenme.net.batch.infrastructure.qiita.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.domain.value.BlogTitle;
import ningenme.net.batch.domain.value.BlogType;
import ningenme.net.batch.domain.value.PostedDate;
import ningenme.net.batch.domain.value.Url;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BlogQiitaDto {
    @SerializedName("title")
    String title;
    @SerializedName("url")
    String url;
    @SerializedName("created_at")
    String postedTime;

    public static Blog getBlog(@NonNull final BlogQiitaDto blogQiitaDto) {
        return Blog.builder()
                   .blogType(BlogType.QIITA)
                   .blogTitle(BlogTitle.of(blogQiitaDto.getTitle()))
                   .postedDate(PostedDate.of(blogQiitaDto.getPostedTime()))
                   .url(Url.of(blogQiitaDto.getUrl()))
                   .build();
    }

    public static List<Blog> getBlogList(@NonNull final List<BlogQiitaDto> blogQiitaDtoList) {
        return blogQiitaDtoList.stream()
                               .map(BlogQiitaDto::getBlog)
                               .collect(Collectors.toList());
    }
}
