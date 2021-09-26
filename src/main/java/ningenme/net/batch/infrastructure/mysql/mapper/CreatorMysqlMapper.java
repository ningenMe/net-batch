package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.CreatorMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreatorMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT IGNORE INTO creators (creator_name) VALUES " +
        "<foreach item='item' collection='creatorMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.creatorName}) " +
        "</foreach>" +
        "</script>"
    )
    void insert(@Param("creatorMysqlDtoList") List<CreatorMysqlDto> creatorMysqlDtoList);
}
