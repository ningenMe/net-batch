package ningenme.net.batch.infrastructure.mysql.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BlogDto {
  private String url;
  private LocalDate postedDate;
  private String blogType;
  private String blogTitle;
  private Integer liked;
}
