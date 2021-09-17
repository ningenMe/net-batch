package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.infrastructure.hatena.BlogHatenaRepository;
import ningenme.net.batch.infrastructure.mysql.BlogMysqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HatenaService {

    private final BlogHatenaRepository blogHatenaRepository;
    private final BlogMysqlRepository blogMysqlRepository;

    public void batchHatena() {
        final List<Blog> blogList = blogHatenaRepository.getBlog();
        blogMysqlRepository.post(blogList);
    }
}
