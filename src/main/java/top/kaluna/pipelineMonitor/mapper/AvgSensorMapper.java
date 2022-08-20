package top.kaluna.pipelineMonitor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.AvgSensor;
import top.kaluna.pipelineMonitor.domain.AvgSensorExample;

public interface AvgSensorMapper {
    long countByExample(AvgSensorExample example);

    int deleteByExample(AvgSensorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AvgSensor record);

    int insertSelective(AvgSensor record);

    List<AvgSensor> selectByExample(AvgSensorExample example);

    AvgSensor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AvgSensor record, @Param("example") AvgSensorExample example);

    int updateByExample(@Param("record") AvgSensor record, @Param("example") AvgSensorExample example);

    int updateByPrimaryKeySelective(AvgSensor record);

    int updateByPrimaryKey(AvgSensor record);

    void insertBatch(List<AvgSensor> avgSensors);


}