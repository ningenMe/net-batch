package ningenme.net.batch.infrastructure.qiita;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.infrastructure.qiita.dto.BlogQiitaDto;
import ningenme.net.batch.infrastructure.qiita.mapper.BlogQiitaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BlogQiitaRepository {

    private final BlogQiitaMapper blogQiitaMapper;

    public List<Blog> getBlog() {
        try {
            final List<BlogQiitaDto> blogQiitaDtoList = blogQiitaMapper.get();
            log.info(blogQiitaDtoList.toString());
            return BlogQiitaDto.getBlogList(blogQiitaDtoList);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
