package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Comic;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RelationCreatorComicMysqlDto {
    private String creatorName;
    private String url;

    public static List<RelationCreatorComicMysqlDto> getRelationCreatorComicMysqlDto(@NonNull final Comic comic) {
        final String url = comic.getUrl().getValue();
        return comic.getComicCreatorList()
                    .stream()
                    .map(comicCreator -> {
                        RelationCreatorComicMysqlDto relationCreatorComicMysqlDto = new RelationCreatorComicMysqlDto();
                        {
                            relationCreatorComicMysqlDto.setCreatorName(comicCreator.getValue());
                            relationCreatorComicMysqlDto.setUrl(url);
                        }
                        return relationCreatorComicMysqlDto;
                    })
                    .collect(Collectors.toList());
    }

}
