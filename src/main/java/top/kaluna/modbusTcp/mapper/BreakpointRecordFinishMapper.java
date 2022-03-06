package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.BreakpointRecordFinish;
import top.kaluna.modbusTcp.domain.BreakpointRecordFinishExample;

public interface BreakpointRecordFinishMapper {
    long countByExample(BreakpointRecordFinishExample example);

    int deleteByExample(BreakpointRecordFinishExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BreakpointRecordFinish record);

    int insertSelective(BreakpointRecordFinish record);

    List<BreakpointRecordFinish> selectByExample(BreakpointRecordFinishExample example);

    BreakpointRecordFinish selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BreakpointRecordFinish record, @Param("example") BreakpointRecordFinishExample example);

    int updateByExample(@Param("record") BreakpointRecordFinish record, @Param("example") BreakpointRecordFinishExample example);

    int updateByPrimaryKeySelective(BreakpointRecordFinish record);

    int updateByPrimaryKey(BreakpointRecordFinish record);
}