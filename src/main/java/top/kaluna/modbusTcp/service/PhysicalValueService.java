package top.kaluna.modbusTcp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.kaluna.modbusTcp.domain.PhysicalValue;
import top.kaluna.modbusTcp.domain.PhysicalValueExample;
import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.req.PageReq;
import top.kaluna.modbusTcp.req.PhysicalValueReq;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.resp.PhysicalValueQueryResp;
import top.kaluna.modbusTcp.util.CopyUtil;
import top.kaluna.modbusTcp.util.DateUtil;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/2/22/0022 - 12:20
 */
@Service
public class PhysicalValueService {
    @Resource
    private PhysicalValueMapper physicalValueMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PhysicalValueService.class);

    public PageResp<PhysicalValueQueryResp> list(DateRangeReq req){
        PhysicalValueExample physicalValueExample = new PhysicalValueExample();
        PhysicalValueExample.Criteria criteria = physicalValueExample.createCriteria();
        //默认时间是今天
        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
            criteria.andCreateTimeBetween(DateUtil.getStartTime(), DateUtil.getEndTime());
        }else{
            criteria.andCreateTimeBetween(req.startTime, req.endTime);
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
}
