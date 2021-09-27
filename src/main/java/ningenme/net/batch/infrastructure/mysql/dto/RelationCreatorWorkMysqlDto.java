package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Work;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RelationCreatorWorkMysqlDto {
    private String creatorName;
    private String workName;

    public static List<RelationCreatorWorkMysqlDto> getRelationCreatorComicMysqlDto(@NonNull final Work work) {
        final String workName = work.getWorkName().getValue();
        return work.getCreatorNameList()
                   .stream()
                   .map(creatorName -> {
                       RelationCreatorWorkMysqlDto relationCreatorWorkMysqlDto = new RelationCreatorWorkMysqlDto();
                       {
                           relationCreatorWorkMysqlDto.setCreatorName(creatorName.getValue());
                           relationCreatorWorkMysqlDto.setWorkName(workName);
                       }
                       return relationCreatorWorkMysqlDto;
                   })
                   .collect(Collectors.toList());
    }

}
