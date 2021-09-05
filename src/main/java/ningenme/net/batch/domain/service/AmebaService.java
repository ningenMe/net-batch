package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Blog;
import ningenme.net.batch.infrastructure.ameba.BlogAmebaRepository;
import ningenme.net.batch.infrastructure.mysql.BlogMysqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmebaService {

    private final BlogAmebaRepository blogAmebaRepository;
    private final BlogMysqlRepository blogMysqlRepository;

    public void batchAmeba() {
        final List<Blog> blogList = blogAmebaRepository.getBlog();
        blogMysqlRepository.post(blogList);
    }

}
