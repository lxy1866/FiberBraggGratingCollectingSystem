package top.kaluna.pipelineMonitor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.kaluna.pipelineMonitor.domain.BreakpointRecordFinish;
import top.kaluna.pipelineMonitor.domain.BreakpointRecordFinishExample;
import top.kaluna.pipelineMonitor.mapper.BreakpointRecordFinishMapper;
import top.kaluna.pipelineMonitor.req.BreakpointRecordQueryReq;
import top.kaluna.pipelineMonitor.resp.BreakpointRecordQueryResp;
import top.kaluna.pipelineMonitor.resp.PageResp;
import top.kaluna.pipelineMonitor.util.CopyUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/6/0006 - 15:39
 */
@Service
public class BreakpointRecordFinishService {
    private static final Logger LOG = LoggerFactory.getLogger(BreakpointRecordFinishService.class);
    @Resource
    private BreakpointRecordFinishMapper breakpointRecordFinishMapper;

    public PageResp<BreakpointRecordQueryResp> list(BreakpointRecordQueryReq req) {
        BreakpointRecordFinishExample breakpointRecordFinishExample = new BreakpointRecordFinishExample();
        BreakpointRecordFinishExample.Criteria criteria = breakpointRecordFinishExample.createCriteria();

        criteria.andStateEqualTo(req.getState());
        List<BreakpointRecordFinish> breakpointRecordFinishes = breakpointRecordFinishMapper.selectByExample(breakpointRecordFinishExample);

        PageHelper.startPage(req.getPage(), req.getSize());
        PageInfo<BreakpointRecordFinish> pageInfo = new PageInfo<>(breakpointRecordFinishes);
        LOG.info("BreakpointRecordFinish总行数：{}", pageInfo.getTotal());
        LOG.info("BreakpointRecordFinish总页数：{}",pageInfo.getPages());

        final List<BreakpointRecordQueryResp> respsList = CopyUtil.copyList(breakpointRecordFinishes, BreakpointRecordQueryResp.class);
        PageResp<BreakpointRecordQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }
}
