package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Blog;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BlogDto {
    private String url;
    private LocalDate postedDate;
    private String blogType;
    private String blogTitle;
    private Integer liked;

    private BlogDto(@NonNull final Blog blog) {
        url = blog.getUrl().getValue();
        postedDate = blog.getPostedDate().getValue();
        blogType = blog.getBlogType().getValue();
        blogTitle = blog.getBlogTitle().getValue();
        liked = blog.getLiked().getValue();
    }

    public static List<BlogDto> getBlogDtoList(@NonNull final List<Blog> blogList) {
        return blogList.stream()
                       .map(BlogDto::new)
                       .collect(Collectors.toList());
    }

}
