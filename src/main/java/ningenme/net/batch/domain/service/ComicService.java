package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.infrastructure.comicNatalie.ComicNatalieRepository;
import ningenme.net.batch.infrastructure.mysql.ComicPageMysqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComicService {

    private final ComicNatalieRepository comicNatalieRepository;
    private final ComicPageMysqlRepository comicPageMysqlRepository;

    public void batchComicPage() {
        final List<ComicPage> comicPageList = comicNatalieRepository.getComicPageList();
        comicPageMysqlRepository.post(comicPageList);
    }

}
