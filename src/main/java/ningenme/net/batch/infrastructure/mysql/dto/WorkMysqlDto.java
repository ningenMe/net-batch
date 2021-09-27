package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Work;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WorkMysqlDto {
    private String workName;
    private String publisherName;

    private WorkMysqlDto(@NonNull final Work work) {
        workName = work.getWorkName().getValue();
        publisherName = work.getPublisherName().getValue();
    }

    public static List<WorkMysqlDto> getWorkMysqlDtoList(@NonNull final List<Work> workList) {
        return workList.stream()
                       .map(WorkMysqlDto::new)
                       .collect(Collectors.toList());
    }
}
