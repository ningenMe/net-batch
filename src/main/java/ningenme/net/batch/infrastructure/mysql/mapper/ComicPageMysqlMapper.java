package ningenme.net.batch.infrastructure.mysql.mapper;

import lombok.NonNull;
import ningenme.net.batch.infrastructure.mysql.dto.ComicPageMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ComicPageMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT INTO comic_pages (url,name,processed_time) VALUES " +
        "<foreach item='item' collection='comicPageMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.url},#{item.name},#{item.processedTime}) " +
        "</foreach>" +
        "ON DUPLICATE KEY UPDATE name=VALUES(name)" +
        "</script>"
    )
    void insert(@Param("comicPageMysqlDtoList") List<ComicPageMysqlDto> comicPageMysqlDtoList);

    @Insert(
        "UPDATE comic_pages SET processed_time = #{processedTime} WHERE url = #{url} "
    )
    void update(@NonNull final ComicPageMysqlDto comicPageMysqlDto);

    @Select(
        "SELECT url,name FROM comic_pages ORDER BY processed_time ASC LIMIT 10"
    )
    List<ComicPageMysqlDto> selectOldest();
}
