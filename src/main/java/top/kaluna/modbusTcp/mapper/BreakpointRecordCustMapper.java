package top.kaluna.modbusTcp.mapper;

import top.kaluna.modbusTcp.domain.BreakpointRecord;

import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 10:46
 */
public interface BreakpointRecordCustMapper {

    void insertBreakpointInfoByScanPhysicalValue(Long createTime);

    List<BreakpointRecord> calculateOnline();
}
