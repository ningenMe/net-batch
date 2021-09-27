package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.WorkMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT IGNORE INTO works (work_name, publisher_name) VALUES " +
        "<foreach item='item' collection='workMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.workName}, #{item.publisherName}) " +
        "</foreach>" +
        "</script>"
    )
    void insert(@Param("workMysqlDtoList") List<WorkMysqlDto> workMysqlDtoList);
}
