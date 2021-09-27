package ningenme.net.batch.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ningenme.net.batch.domain.entity.Comic;
import ningenme.net.batch.domain.entity.ComicPage;
import ningenme.net.batch.domain.entity.Work;
import ningenme.net.batch.infrastructure.comicNatalie.ComicNatalieRepository;
import ningenme.net.batch.infrastructure.mysql.ComicMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.ComicPageMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.CreatorMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.PublisherMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.RelationCreatorWorkMysqlRepository;
import ningenme.net.batch.infrastructure.mysql.WorkMysqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComicService {

    private final ComicNatalieRepository comicNatalieRepository;
    private final ComicPageMysqlRepository comicPageMysqlRepository;
    private final PublisherMysqlRepository publisherMysqlRepository;
    private final ComicMysqlRepository comicMysqlRepository;
    private final CreatorMysqlRepository creatorMysqlRepository;
    private final WorkMysqlRepository workMysqlRepository;
    private final RelationCreatorWorkMysqlRepository relationCreatorWorkMysqlRepository;
//    private final RelationCreatorComicMysqlRepository relationCreatorComicMysqlRepository;

    public void batchComicPage() {
        final List<ComicPage> comicPageList = comicNatalieRepository.getComicPageList();
        comicPageMysqlRepository.post(comicPageList);
    }

    public void batchComic() {
        final List<ComicPage> comicPageList = comicPageMysqlRepository.get();
        for (final ComicPage comicPage : comicPageList) {
            try {
                log.info("url = {} , name= {} , process start", comicPage.getUrl().getValue(), comicPage.getName());
                final List<Comic> comicList = comicNatalieRepository.getComicList(comicPage.getUrl());
                final List<Work> workList = comicList.stream().map(Comic::getWork).collect(Collectors.toList());

                publisherMysqlRepository.post(comicList);
                creatorMysqlRepository.post(comicList);
                workMysqlRepository.post(workList);

                comicMysqlRepository.post(comicList);
                relationCreatorWorkMysqlRepository.post(workList);

                comicPageMysqlRepository.put(comicPage.toBuilder()
                                                      .processedTime(LocalDateTime.now())
                                                      .build());

                if (CollectionUtils.isEmpty(comicList)) {
                    log.error("url = {}, 0 comics were processed", comicPage.getUrl().getValue());
                } else {
                    log.info("{} comics were processed", comicList.size());
                }

            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
    }

}
