package top.kaluna.pipelineMonitor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.kaluna.pipelineMonitor.domain.*;

import top.kaluna.pipelineMonitor.mapper.BreakpointRecordCustMapper;
import top.kaluna.pipelineMonitor.mapper.BreakpointRecordFinishMapper;
import top.kaluna.pipelineMonitor.mapper.BreakpointRecordMapper;
import top.kaluna.pipelineMonitor.mapper.FbgValueInfoMapper;
import top.kaluna.pipelineMonitor.req.BreakpointRecordQueryReq;
import top.kaluna.pipelineMonitor.resp.BreakpointRecordQueryResp;
import top.kaluna.pipelineMonitor.resp.PageResp;
import top.kaluna.pipelineMonitor.util.CopyUtil;
import top.kaluna.pipelineMonitor.util.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 10:48
 */
@Service
public class BreakpointRecordService {
    private static final Logger LOG = LoggerFactory.getLogger(BreakpointRecordService.class);
    @Resource
    private BreakpointRecordCustMapper breakpointRecordCustMapper;
    @Resource
    private BreakpointRecordMapper breakpointRecordMapper;
    @Resource
    private BreakpointRecordFinishMapper breakpointRecordFinishMapper;
    @Resource
    private FbgValueInfoMapper fbgValueInfoMapper;
    public void insertBreakpointInfoByScanFbgValue(){
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
        int arrayNum = 0;

        breakpointRecords.forEach((BreakpointRecord breakpointRecord)->{
            breakpointRecord.setState(state);
            breakpointRecord.setArrayNum(arrayNum);
            BreakpointRecordFinish breakpointRecordFinish = CopyUtil.copy(breakpointRecord, BreakpointRecordFinish.class);
            breakpointRecordFinish.setId(null);
            breakpointRecordFinishMapper.insert(breakpointRecordFinish);
        });
        breakpointRecordMapper.deleteByExample(breakpointRecordExample);
    }

    //在线的个数计算 将断点记录表中的所有(arrayNum-1)相加起来下来 由于有一些通道没有断点，将该通道下的所有传感器加起来
    public int calculateOnLine() {
        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        final FbgValueInfoExample.Criteria criteria = fbgValueInfoExample.createCriteria();

        int sum = 0;
        List channelList = new ArrayList();
        final List<BreakpointRecord> breakpointRecords = breakpointRecordCustMapper.calculateOnline();
        List<FbgValueInfo> fbgValueInfos = fbgValueInfoMapper.selectAllRecord();

        for (int i = 0; i < breakpointRecords.size(); i++) {
            channelList.add(breakpointRecords.get(i).getChannel());
            sum += breakpointRecords.get(i).getArrayNum()-1;
        }
        for (int i = 0; i < fbgValueInfos.size(); i++) {
            if(!channelList.contains(fbgValueInfos.get(i).getChannel())){

                criteria.andChannelEqualTo(fbgValueInfos.get(i).getChannel());
                //计算没有在断点记录表中的通道下的所有传感器个数
                final List<FbgValueInfo> fbgValueInfos1 = fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
                sum += fbgValueInfos1.size();
            }
        }
        return sum;
    }
}
