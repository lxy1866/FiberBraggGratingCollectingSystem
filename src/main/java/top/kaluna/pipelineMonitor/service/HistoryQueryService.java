package top.kaluna.pipelineMonitor.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.kaluna.pipelineMonitor.domain.FbgValue;
import top.kaluna.pipelineMonitor.domain.FbgValueExample;
import top.kaluna.pipelineMonitor.domain.QueryResultExample;
import top.kaluna.pipelineMonitor.domain.QueryResulyRow;
import top.kaluna.pipelineMonitor.mapper.FbgValueMapper;
import top.kaluna.pipelineMonitor.mapper.VariantPointMapper;
import top.kaluna.pipelineMonitor.req.DateRangeReq;
import top.kaluna.pipelineMonitor.resp.FbgValueQueryResp;
import top.kaluna.pipelineMonitor.resp.PageResp;
import top.kaluna.pipelineMonitor.util.DateUtil;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
public class HistoryQueryService {

    @Resource
    private VariantPointMapper variantPointMapper;

//    public PageResp<String> list(DateRangeReq req) {
//        Long startTime;
//        Long endTime;
//        QueryResultExample queryResultExample = new QueryResultExample();
//        QueryResultExample.Criteria criteria = queryResultExample.createCriteria();
//        //默认时间是今天
//        if(ObjectUtils.isEmpty(req.startTime) || ObjectUtils.isEmpty(req.endTime) || ObjectUtils.isEmpty(req)){
//            startTime = DateUtil.getStartTime().getTime();
//            endTime = DateUtil.getEndTime().getTime();
//            System.out.println("startTime:"+startTime);
//            System.out.println("endTime:"+endTime);
//        }else{
//            startTime = req.getStartTime();
//            endTime =  req.getEndTime();
//        }
//        criteria.andCreateTimeBetween(startTime,endTime);
//
//        queryResultExample.setOrderByClause("date ASC");
//        //查询到指定时间范围的数据
//        final List<QueryResulyRow> queryResult = variantPointMapper.selectByExample(queryResultExample);
//        //分组
//        final Collection<List<QueryResulyRow>> collects = queryResult.stream().collect(groupingBy(QueryResulyRow::getDate)).values();
//
//        Map<String, String> map = new HashMap<>();
//        List<String> respsList = new ArrayList<>();
//        collects.forEach((item)->{
//            for (int i = 1; i <= item.size(); i++) {
//                map.put("val"+i, item.get(i-1).getValue().toString());
//            }
//            map.put("createTime", item.get(0).getDate().toString());
//            String astr = JSONObject.toJSONString(map, SerializerFeature.MapSortField);
//            respsList.add(astr);
//        });
//        PageHelper.startPage(req.getPage(), req.getSize());
//        PageInfo<FbgValueQueryResp> pageInfo = new PageInfo<>();
////        LOG.info("总行数：{}", pageInfo.getTotal());
////        LOG.info("总页数：{}",pageInfo.getPages());
//
//        PageResp<String> pageResp = new PageResp<>();
//        pageResp.setTotal(pageInfo.getTotal());
//        pageResp.setList(respsList);
//        return pageResp;
//    }



}
