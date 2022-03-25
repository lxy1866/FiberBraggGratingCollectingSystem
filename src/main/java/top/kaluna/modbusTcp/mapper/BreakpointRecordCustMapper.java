package top.kaluna.modbusTcp.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 10:46
 */
public interface BreakpointRecordCustMapper {

    void insertBreakpointInfoByScanPhysicalValue(Long createTime);

    byte findTagForOnLine();
}
