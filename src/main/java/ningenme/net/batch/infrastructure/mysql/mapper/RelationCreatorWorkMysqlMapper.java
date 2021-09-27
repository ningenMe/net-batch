package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.RelationCreatorWorkMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationCreatorWorkMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT IGNORE INTO relation_creators_works (creator_id, work_id) VALUES " +
        "<foreach item='item' collection='relationCreatorWorkMysqlDtoList' open='' separator=',' close=''>" +
        "(" +
        " (SELECT creator_id FROM creators WHERE creator_name = #{item.creatorName}), " +
        " (SELECT work_id FROM works WHERE work_name = #{item.workName})" +
        ") " +
        "</foreach>" +
        "</script>"
    )
    void insert(@Param("relationCreatorWorkMysqlDtoList") List<RelationCreatorWorkMysqlDto> relationCreatorWorkMysqlDtoList);
}
