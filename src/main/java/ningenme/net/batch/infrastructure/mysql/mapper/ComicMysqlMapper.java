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
        "INSERT INTO comics (url,comic_name,work_id,published_date) VALUES " +
        "<foreach item='item' collection='comicMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.url},#{item.comicName},(SELECT work_id FROM works WHERE work_name = #{item.workName}),#{item.publishedDate}) " +
        "</foreach>" +
        "ON DUPLICATE KEY UPDATE comic_name=VALUES(comic_name), work_id=VALUES(work_id), published_date=VALUES(published_date)" +
        "</script>"
    )
    void insert(@Param("comicMysqlDtoList") List<ComicMysqlDto> comicMysqlDtoList);
}
