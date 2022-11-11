package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.MinMaxValueForTemperature;
import top.kaluna.modbusTcp.domain.MinMaxValueForTemperatureExample;

public interface MinMaxValueForTemperatureMapper {
    long countByExample(MinMaxValueForTemperatureExample example);

    int deleteByExample(MinMaxValueForTemperatureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MinMaxValueForTemperature record);

    int insertSelective(MinMaxValueForTemperature record);

    List<MinMaxValueForTemperature> selectByExample(MinMaxValueForTemperatureExample example);

    MinMaxValueForTemperature selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MinMaxValueForTemperature record, @Param("example") MinMaxValueForTemperatureExample example);

    int updateByExample(@Param("record") MinMaxValueForTemperature record, @Param("example") MinMaxValueForTemperatureExample example);

    int updateByPrimaryKeySelective(MinMaxValueForTemperature record);

    int updateByPrimaryKey(MinMaxValueForTemperature record);

    void multipleInsert(List<MinMaxValueForTemperature> minMaxValueForTemperatures);
}