package top.kaluna.modbusTcp.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.kaluna.modbusTcp.domain.FbgValue;
import top.kaluna.modbusTcp.domain.FbgValueExample;
import top.kaluna.modbusTcp.mapper.FbgValueMapper;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.resp.FbgValueQueryResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Yuery
 * @date 2022/3/21/0021 - 19:38
 */
@Service
public class FbgValueService {
    @Resource
    private FbgValueMapper fbgValueMapper;
    private static final Logger LOG = LoggerFactory.getLogger(PhysicalValueService.class);
    public PageResp<String> list(DateRangeReq req) {
        FbgValueExample fbgValueExample = new FbgValueExample();
        FbgValueExample.Criteria criteria = fbgValueExample.createCriteria();
        //默认时间是今天
        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
            criteria.andCreateTimeBetween(DateUtil.getStartTime().getTime(), DateUtil.getEndTime().getTime());

        }else{
            criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
        }
        fbgValueExample.setOrderByClause("create_time ASC");
        //查询到指定时时间范围的数据
        final List<FbgValue> fbgValues = fbgValueMapper.selectByExample(fbgValueExample);
        //分组
        final Collection<List<FbgValue>> collects = fbgValues.stream().collect(groupingBy(FbgValue::getCreateTime)).values();

        FbgValueQueryResp fbgValueQueryResp = new FbgValueQueryResp();
        Map<String, String> map = new HashMap<>();
        List<String> respsList = new ArrayList<>();
        collects.forEach((item)->{
            for (int i = 1; i <= item.size(); i++) {
                map.put("val"+i, item.get(i-1).getValue().toString());
            }
            map.put("createTime", item.get(0).getCreateTime().toString());
            String astr = JSONObject.toJSONString(map,SerializerFeature.MapSortField);
            fbgValueQueryResp.setMap(map);
            respsList.add(astr);
        });

        PageHelper.startPage(req.getPage(), req.getSize());
        PageInfo<FbgValueQueryResp> pageInfo = new PageInfo<>();
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());

        PageResp<String> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }
}
