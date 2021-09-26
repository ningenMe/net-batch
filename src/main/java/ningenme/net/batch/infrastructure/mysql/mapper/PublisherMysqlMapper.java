package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.PublisherMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublisherMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT IGNORE INTO publishers (publisher_name) VALUES " +
        "<foreach item='item' collection='publisherMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.publisherName}) " +
        "</foreach>" +
        "</script>"
    )
    void insert(@Param("publisherMysqlDtoList") List<PublisherMysqlDto> publisherMysqlDtoList);
}
