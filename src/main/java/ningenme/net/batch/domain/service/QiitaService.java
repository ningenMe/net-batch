package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.infrastructure.mysql.BlogMysqlRepository;
import ningenme.net.batch.infrastructure.qiita.BlogQiitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QiitaService {

    private final BlogQiitaRepository blogQiitaRepository;
    private final BlogMysqlRepository blogMysqlRepository;

    public void batchQiita() {
        final List<Blog> blogList = blogQiitaRepository.getBlog();
        blogMysqlRepository.post(blogList);
    }

}
