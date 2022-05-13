package top.kaluna.pipelineMonitor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.BreakpointRecord;
import top.kaluna.pipelineMonitor.domain.BreakpointRecordExample;

public interface BreakpointRecordMapper {
    long countByExample(BreakpointRecordExample example);

    int deleteByExample(BreakpointRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BreakpointRecord record);

    int insertSelective(BreakpointRecord record);

    List<BreakpointRecord> selectByExample(BreakpointRecordExample example);

    BreakpointRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BreakpointRecord record, @Param("example") BreakpointRecordExample example);

    int updateByExample(@Param("record") BreakpointRecord record, @Param("example") BreakpointRecordExample example);

    int updateByPrimaryKeySelective(BreakpointRecord record);

    int updateByPrimaryKey(BreakpointRecord record);
}