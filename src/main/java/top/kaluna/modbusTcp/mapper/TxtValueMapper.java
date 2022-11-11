package top.kaluna.modbusTcp.mapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.FbgValue;
import top.kaluna.modbusTcp.domain.MinMaxValueForTemperature;
import top.kaluna.modbusTcp.domain.TxtValue;
import top.kaluna.modbusTcp.domain.TxtValueExample;
import top.kaluna.modbusTcp.resp.LastNHoursMinAndMaxResp;

public interface TxtValueMapper {
    long countByExample(TxtValueExample example);

    int deleteByExample(TxtValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TxtValue record);

    int insertSelective(TxtValue record);

    List<TxtValue> selectByExample(TxtValueExample example);

    TxtValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TxtValue record, @Param("example") TxtValueExample example);

    int updateByExample(@Param("record") TxtValue record, @Param("example") TxtValueExample example);

    int updateByPrimaryKeySelective(TxtValue record);

    int updateByPrimaryKey(TxtValue record);

    int multipleInsert(List<TxtValue> txtValues);

    MinMaxValueForTemperature minAndMaxFrom24Hours(Date createTime);
}