package top.kaluna.modbusTcp.service;

import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.mapper.BreakpointRecordCustMapper;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 10:48
 */
@Service
public class BreakpointRecordService {
    @Resource
    private BreakpointRecordCustMapper breakpointRecordCustMapper;

    public void insertBreakpointInfoByScanPhysicalValue(){
        Long createTime = DateUtil.getNowTime().getTime();
        breakpointRecordCustMapper.insertBreakpointInfoByScanPhysicalValue(createTime);
    }
}
