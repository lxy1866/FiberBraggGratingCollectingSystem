package top.kaluna.pipelineMonitor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.FbgValueInfo;
import top.kaluna.pipelineMonitor.domain.FbgValueInfoExample;

public interface FbgValueInfoMapper {
    long countByExample(FbgValueInfoExample example);

    int deleteByExample(FbgValueInfoExample example);

    int insert(FbgValueInfo record);

    int insertSelective(FbgValueInfo record);

    List<FbgValueInfo> selectByExample(FbgValueInfoExample example);

    int updateByExampleSelective(@Param("record") FbgValueInfo record, @Param("example") FbgValueInfoExample example);

    int updateByExample(@Param("record") FbgValueInfo record, @Param("example") FbgValueInfoExample example);

    int total();

    List<FbgValueInfo> selectAllRecord();

}