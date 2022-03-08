package top.kaluna.modbusTcp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.kaluna.modbusTcp.domain.PhysicalValue;
import top.kaluna.modbusTcp.domain.PhysicalValueExample;
import top.kaluna.modbusTcp.mapper.NormalRangeMapper;
import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.req.PhysicalValueSaveReq;
import top.kaluna.modbusTcp.resp.NormalRangeQueryResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.resp.PhysicalValueQueryResp;
import top.kaluna.modbusTcp.util.CopyUtil;
import top.kaluna.modbusTcp.util.DateUtil;
import top.kaluna.modbusTcp.util.SnowFlake;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/2/22/0022 - 12:20
 */
@Service
public class PhysicalValueService {

    @Resource
    private SnowFlake snowFlake;
    @Resource
    private PhysicalValueMapper physicalValueMapper;
    @Resource
    private NormalRangeMapper normalRangeMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PhysicalValueService.class);

    public PageResp<PhysicalValueQueryResp> list(DateRangeReq req){
        PhysicalValueExample physicalValueExample = new PhysicalValueExample();
        PhysicalValueExample.Criteria criteria = physicalValueExample.createCriteria();
        //默认时间是今天
        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
            criteria.andCreateTimeBetween(DateUtil.getStartTime().getTime(), DateUtil.getEndTime().getTime());
        }else{
            criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
        }
        final List<PhysicalValue> physicalValues = physicalValueMapper.selectByExample(physicalValueExample);

        PageHelper.startPage(req.getPage(), req.getSize());
        PageInfo<PhysicalValue> pageInfo = new PageInfo<>(physicalValues);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());

        final List<PhysicalValueQueryResp> respsList = CopyUtil.copyList(physicalValues, PhysicalValueQueryResp.class);
        PageResp<PhysicalValueQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }

    public void save(PhysicalValueSaveReq physicalValueSaveReq) {
        PhysicalValue physicalValue = CopyUtil.copy(physicalValueSaveReq, PhysicalValue.class);

        PhysicalValueExample physicalValueExample = new PhysicalValueExample();
        PhysicalValueExample.Criteria criteria = physicalValueExample.createCriteria();
        criteria.andIdEqualTo(physicalValueSaveReq.getId());
        if(ObjectUtils.isEmpty(physicalValueMapper.selectByExample(physicalValueExample))){
            //新增
            physicalValue.setCreateTime(DateUtil.getNowTime().getTime());
            physicalValue.setId(snowFlake.nextId());
            physicalValueMapper.insert(physicalValue);
        }else {
            physicalValueMapper.updateByPrimaryKey(physicalValue);
        }
    }

    public void delete(Long id) {
        physicalValueMapper.deleteByPrimaryKey(id);
    }

    public PageResp<PhysicalValueQueryResp> abnormalList(DateRangeReq req) {
        PhysicalValueExample physicalValueExample = new PhysicalValueExample();
        PhysicalValueExample.Criteria criteria = physicalValueExample.createCriteria();
        //默认时间是今天
        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
            criteria.andCreateTimeBetween(DateUtil.getStartTime().getTime(), DateUtil.getEndTime().getTime());
        }else{
            criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
        }
        NormalRangeQueryResp normalRangeQueryResp = normalRangeMapper.findMaxId();

        physicalValueExample.or(new PhysicalValueExample.Criteria().andVal1NotBetween(normalRangeQueryResp.getVal1MinValue(),normalRangeQueryResp.getVal1MaxValue()));
        physicalValueExample.or(new PhysicalValueExample.Criteria().andVal2NotBetween(normalRangeQueryResp.getVal2MinValue(),normalRangeQueryResp.getVal2MaxValue()));
        physicalValueExample.or(new PhysicalValueExample.Criteria().andVal3NotBetween(normalRangeQueryResp.getVal3MinValue(),normalRangeQueryResp.getVal3MaxValue()));
        physicalValueExample.or(new PhysicalValueExample.Criteria().andVal4NotBetween(normalRangeQueryResp.getVal4MinValue(),normalRangeQueryResp.getVal4MaxValue()));
        physicalValueExample.or(new PhysicalValueExample.Criteria().andVal5NotBetween(normalRangeQueryResp.getVal5MinValue(),normalRangeQueryResp.getVal5MaxValue()));
        physicalValueExample.or(new PhysicalValueExample.Criteria().andVal6NotBetween(normalRangeQueryResp.getVal6MinValue(),normalRangeQueryResp.getVal6MaxValue()));

        final List<PhysicalValue> physicalValues = physicalValueMapper.selectByExample(physicalValueExample);

        PageHelper.startPage(req.getPage(), req.getSize());
        PageInfo<PhysicalValue> pageInfo = new PageInfo<>(physicalValues);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());

        final List<PhysicalValueQueryResp> respsList = CopyUtil.copyList(physicalValues, PhysicalValueQueryResp.class);
        PageResp<PhysicalValueQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }
}
