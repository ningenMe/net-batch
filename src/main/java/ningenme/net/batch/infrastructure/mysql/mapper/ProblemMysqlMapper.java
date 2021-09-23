package ningenme.net.batch.infrastructure.mysql.mapper;

import ningenme.net.batch.infrastructure.mysql.dto.ProblemMysqlDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProblemMysqlMapper {

    @Insert(
        "<script>" +
        "INSERT INTO problems (url,estimation) VALUES " +
        "<foreach item='item' collection='problemMysqlDtoList' open='' separator=',' close=''>" +
        "(#{item.url},#{item.estimation}) " +
        "</foreach>" +
        "ON DUPLICATE KEY UPDATE estimation=VALUES(estimation)" +
        "</script>"
    )
    void insert(@Param("problemMysqlDtoList") List<ProblemMysqlDto> problemMysqlDtoList);
}
