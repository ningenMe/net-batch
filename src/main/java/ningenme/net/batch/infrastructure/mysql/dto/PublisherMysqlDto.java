package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ningenme.net.batch.domain.entity.Comic;

@Data
@NoArgsConstructor
public class PublisherMysqlDto {
    private String publisherName;

    public PublisherMysqlDto(@NonNull final Comic comic) {
        publisherName = comic.getPublisherName().getValue();
    }
}
