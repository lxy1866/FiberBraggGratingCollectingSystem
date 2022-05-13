package top.kaluna.pipelineMonitor.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.FbgValue;
import top.kaluna.pipelineMonitor.domain.FbgValueExample;
import top.kaluna.pipelineMonitor.resp.LastNHoursMinAndMaxResp;

public interface FbgValueMapper {
    long countByExample(FbgValueExample example);

    int deleteByExample(FbgValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FbgValue record);

    int insertSelective(FbgValue record);

    List<FbgValue> selectByExample(FbgValueExample example);

    FbgValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FbgValue record, @Param("example") FbgValueExample example);

    int updateByExample(@Param("record") FbgValue record, @Param("example") FbgValueExample example);

    int updateByPrimaryKeySelective(FbgValue record);

    int updateByPrimaryKey(FbgValue record);

    List<FbgValue> selectForAbnormal(Long startTime, Long endTime);

    int multipleInsert(List<FbgValue> fbgValues);

    BigDecimal temperatureNow();

    LastNHoursMinAndMaxResp minAndMaxFrom24Hours(long lastNHoursStart, long lastNHoursEnd);
}