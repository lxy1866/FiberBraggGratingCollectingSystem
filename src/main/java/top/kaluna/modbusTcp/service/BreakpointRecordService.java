package top.kaluna.modbusTcp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.BreakpointRecord;
import top.kaluna.modbusTcp.domain.BreakpointRecordExample;
import top.kaluna.modbusTcp.domain.BreakpointRecordFinish;
import top.kaluna.modbusTcp.mapper.BreakpointRecordCustMapper;
import top.kaluna.modbusTcp.mapper.BreakpointRecordFinishMapper;
import top.kaluna.modbusTcp.mapper.BreakpointRecordMapper;
import top.kaluna.modbusTcp.req.BreakpointRecordQueryReq;
import top.kaluna.modbusTcp.resp.BreakpointRecordQueryResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.util.CopyUtil;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 10:48
 */
@Service
public class BreakpointRecordService {
    private static final Logger LOG = LoggerFactory.getLogger(PhysicalValueService.class);
    @Resource
    private BreakpointRecordCustMapper breakpointRecordCustMapper;
    @Resource
    private BreakpointRecordMapper breakpointRecordMapper;
    @Resource
    private BreakpointRecordFinishMapper breakpointRecordFinishMapper;
    public void insertBreakpointInfoByScanPhysicalValue(){
        Long createTime = DateUtil.getNowTime().getTime();
        breakpointRecordCustMapper.insertBreakpointInfoByScanPhysicalValue(createTime);
    }

    public PageResp<BreakpointRecordQueryResp> list(BreakpointRecordQueryReq req) {
        BreakpointRecordExample breakpointRecordExample = new BreakpointRecordExample();
        BreakpointRecordExample.Criteria criteria = breakpointRecordExample.createCriteria();

        criteria.andStateEqualTo(req.getState());
        List<BreakpointRecord> breakpointRecords = breakpointRecordMapper.selectByExample(breakpointRecordExample);

        PageHelper.startPage(req.getPage(), req.getSize());
        PageInfo<BreakpointRecord> pageInfo = new PageInfo<>(breakpointRecords);
        LOG.info("BreakpointRecord总行数：{}", pageInfo.getTotal());
        LOG.info("BreakpointRecord总页数：{}",pageInfo.getPages());

        final List<BreakpointRecordQueryResp> respsList = CopyUtil.copyList(breakpointRecords, BreakpointRecordQueryResp.class);
        PageResp<BreakpointRecordQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }

    public void update(Long id) {
        BreakpointRecordExample breakpointRecordExample = new BreakpointRecordExample();
        BreakpointRecordExample.Criteria criteria = breakpointRecordExample.createCriteria();

        criteria.andIdEqualTo(id);
        List<BreakpointRecord> breakpointRecords = breakpointRecordMapper.selectByExample(breakpointRecordExample);

        Byte state = 1;
        Byte tag = 0;

        breakpointRecords.forEach((BreakpointRecord breakpointRecord)->{
            breakpointRecord.setState(state);
            breakpointRecord.setTag(tag);
            BreakpointRecordFinish breakpointRecordFinish = CopyUtil.copy(breakpointRecord, BreakpointRecordFinish.class);
            breakpointRecordFinishMapper.insert(breakpointRecordFinish);
        });
        breakpointRecordMapper.deleteByExample(breakpointRecordExample);
    }
}
