package top.kaluna.modbusTcp.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.kaluna.modbusTcp.domain.*;
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
    private static final Logger LOG = LoggerFactory.getLogger(FbgValueService.class);
    public PageResp<String> list(DateRangeReq req) {
        Long startTime;
        Long endTime;
        FbgValueExample fbgValueExample = new FbgValueExample();
        FbgValueExample.Criteria criteria = fbgValueExample.createCriteria();
        //默认时间是今天
        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
            startTime = DateUtil.getStartTime().getTime();
            endTime = DateUtil.getEndTime().getTime();
            System.out.println("startTime:"+startTime);
            System.out.println("endTime:"+endTime);
        }else{
            startTime = req.getStartTime();
            endTime =  req.getEndTime();
        }
        criteria.andCreateTimeBetween(startTime,endTime);

        fbgValueExample.setOrderByClause("create_time ASC");
        //查询到指定时间范围的数据
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

    //拼接异常信息 将时间和异常信息以JsonString的方式返回
    public PageResp<String> abnormalList(DateRangeReq req) {
        Long startTime;
        Long endTime;
        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
            startTime = DateUtil.getStartTime().getTime();
            endTime = DateUtil.getEndTime().getTime();
        }else{
            startTime = req.getStartTime();
            endTime = req.getEndTime();
        }
        //联表查询出异常的数据
        final List<FbgValue> fbgValues = fbgValueMapper.selectForAbnormal(startTime, endTime);

        //分组
        final Collection<List<FbgValue>> collects = fbgValues.stream().collect(groupingBy(FbgValue::getCreateTime)).values();
        FbgValueQueryResp fbgValueQueryResp = new FbgValueQueryResp();
        Map<String, String> map = new HashMap<>();
        List<String> respsList = new ArrayList<>();
        if(!collects.isEmpty()){
            collects.forEach((item)->{
                StringBuilder msg = new StringBuilder("异常信息：");
                for (int i = 1; i <= item.size(); i++) {
                    msg.append("val").append(item.get(i - 1).getPhysicalValueInfoId().toString())
                            .append("的异常值为：").append(item.get(i - 1).getValue()).append(";");
                }
                map.put("msg", msg.toString());
                map.put("createTime", item.get(0).getCreateTime().toString());
                String string = JSONObject.toJSONString(map,SerializerFeature.MapSortField);
                fbgValueQueryResp.setMap(map);
                respsList.add(string);
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
        return null;
    }

}
