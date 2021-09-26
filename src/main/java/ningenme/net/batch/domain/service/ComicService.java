package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.infrastructure.comicNatalie.ComicNatalieRepository;
import ningenme.net.batch.infrastructure.mysql.ComicMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.ComicPageMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.CreatorMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.PublisherMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.RelationCreatorComicMysqlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComicService {

    private final ComicNatalieRepository comicNatalieRepository;
    private final ComicPageMysqlRepository comicPageMysqlRepository;
    private final PublisherMysqlRepository publisherMysqlRepository;
    private final ComicMysqlRepository comicMysqlRepository;
    private final CreatorMysqlRepository creatorMysqlRepository;
    private final RelationCreatorComicMysqlRepository relationCreatorComicMysqlRepository;

    public void batchComicPage() {
        final List<ComicPage> comicPageList = comicNatalieRepository.getComicPageList();
        comicPageMysqlRepository.post(comicPageList);
    }

    public void batchComic() {
        final List<ComicPage> comicPageList = comicPageMysqlRepository.get();
        for (final ComicPage comicPage : comicPageList) {
            try {
                final List<Comic> comicList = comicNatalieRepository.getComicList(comicPage.getUrl());
                publisherMysqlRepository.post(comicList);
                comicMysqlRepository.post(comicList);
                creatorMysqlRepository.post(comicList);
                relationCreatorComicMysqlRepository.post(comicList);

                log.info(comicList.size() + " comics were processed");

                comicPageMysqlRepository.put(comicPage.toBuilder()
                                                      .processedTime(LocalDateTime.now())
                                                      .build());
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
    }

}
