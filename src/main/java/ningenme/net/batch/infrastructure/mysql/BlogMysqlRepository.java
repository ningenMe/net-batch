package ningenme.net.batch.infrastructure.mysql;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.infrastructure.mysql.mapper.BlogMysqlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BlogMysqlRepository {
  private final BlogMysqlMapper blogMysqlMapper;

  public void post(@NonNull final List<Blog> blogList) {
    if(CollectionUtils.isEmpty(blogList)) {
      return;
    }
    blogMysqlMapper.insert(Blog.getBlogDtoList(blogList));
  }

}
