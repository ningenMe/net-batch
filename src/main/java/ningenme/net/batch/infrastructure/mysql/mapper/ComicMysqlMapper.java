package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.ComicMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComicMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT INTO comics (url,comic_name,publisher_name) VALUES " +
        "<foreach item='item' collection='comicMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.url},#{item.comicName},#{item.publisherName}) " +
        "</foreach>" +
        "ON DUPLICATE KEY UPDATE comic_name=VALUES(comic_name), publisher_name=VALUES(publisher_name)" +
        "</script>"
    )
    void insert(@Param("comicMysqlDtoList") List<ComicMysqlDto> comicMysqlDtoList);
}
