package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.infrastructure.github.BlogGithubRepository;
import ningenme.net.batch.infrastructure.mysql.BlogMysqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final BlogGithubRepository blogGithubRepository;
    private final BlogMysqlRepository blogMysqlRepository;

    public void batchDiary() {
        final List<Blog> blogList = blogGithubRepository.getBlog();
        blogMysqlRepository.post(blogList);
    }

}
