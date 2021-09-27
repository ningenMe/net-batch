package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Comic;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreatorMysqlDto {
    private String creatorName;

    public static List<CreatorMysqlDto> getCreatorMysqlDtoList(@NonNull final Comic comic) {
        return comic.getCreatorNameList()
                    .stream()
                    .map(comicCreator -> {
                        CreatorMysqlDto creatorMysqlDto = new CreatorMysqlDto();
                        creatorMysqlDto.setCreatorName(comicCreator.getValue());
                        return creatorMysqlDto;
                    })
                    .collect(Collectors.toList());
    }
}
