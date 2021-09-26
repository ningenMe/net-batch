package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.RelationCreatorComicMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationCreatorComicMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT IGNORE INTO relation_creators_comics (creator_id, comic_id) VALUES " +
        "<foreach item='item' collection='relationCreatorComicMysqlDtoList' open='' separator=',' close=''>" +
        "(" +
        " (SELECT creator_id FROM creators WHERE creator_name = #{item.creatorName}), " +
        " (SELECT comic_id FROM comics WHERE url = #{item.url})" +
        ") " +
        "</foreach>" +
        "</script>"
    )
    void insert(@Param("relationCreatorComicMysqlDtoList") List<RelationCreatorComicMysqlDto> relationCreatorComicMysqlDtoList);
}
