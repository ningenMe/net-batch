package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.BlogMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT INTO blogs (url,date,type,title,liked) VALUES " +
        "<foreach item='item' collection='blogMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.url},#{item.postedDate},#{item.blogType},#{item.blogTitle},#{item.liked}) " +
        "</foreach>" +
        "ON DUPLICATE KEY UPDATE title=VALUES(title)" +
        "</script>"
    )
    void insert(@Param("blogMysqlDtoList") List<BlogMysqlDto> blogMysqlDtoList);
}
