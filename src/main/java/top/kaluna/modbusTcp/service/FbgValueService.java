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
import top.kaluna.modbusTcp.mapper.FbgValueInfoMapper;
import top.kaluna.modbusTcp.mapper.FbgValueMapper;
import top.kaluna.modbusTcp.mapper.MinMaxValueForTemperatureMapper;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.resp.FbgValueQueryResp;
import top.kaluna.modbusTcp.resp.LastNHoursMinAndMaxResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Resource
    private FbgValueInfoMapper fbgValueInfoMapper;
    @Resource
    private MinMaxValueForTemperatureMapper minMaxValueForTemperatureMapper;
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

        Map<String, String> map = new HashMap<>();
        List<String> respsList = new ArrayList<>();
        collects.forEach((item)->{
            for (int i = 1; i <= item.size(); i++) {
                map.put("val"+i, item.get(i-1).getValue().toString());
            }
            map.put("createTime", item.get(0).getCreateTime().toString());
            String astr = JSONObject.toJSONString(map,SerializerFeature.MapSortField);
            respsList.add(astr);
        });
        PageHelper.startPage(req.getPage(), req.getPageSize());
        PageInfo<FbgValueQueryResp> pageInfo = new PageInfo<>();
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());

        PageResp<String> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }

    public PageResp<Map<String, String>> list2(DateRangeReq req) throws ParseException {
        Long startTime;
        Long endTime;
        FbgValueExample txtValueExample = new FbgValueExample();
        FbgValueExample.Criteria criteria = txtValueExample.createCriteria();

        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        startTime = req.getStartTime();
        endTime =  req.getEndTime();

        if(startTime == null || endTime == null){
            return null;
        }
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ,Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        criteria.andCreateTimeBetween(format.parse(format.format(startTime)).getTime(), format.parse(format.format(endTime)).getTime());
        //分页前先算好总条数
        final long totalProperty = fbgValueInfoMapper.countByExample(fbgValueInfoExample);
        final long total = fbgValueMapper.countByExample(txtValueExample) / totalProperty;
        PageHelper.startPage(req.getPage(), (int) (req.getPageSize() * totalProperty));
        //PageInfo<TxtValue> pageInfo = new PageInfo<>();
        //查询到指定时间范围的数据
        final List<FbgValue> txtValues = fbgValueMapper.selectByExample(txtValueExample);
        //分组
        final Collection<List<FbgValue>> collects = txtValues.stream().collect(groupingBy(FbgValue::getCreateTime)).values();
        //分成很多组，每组有多条数据
        List<Map<String, String>> respList = new ArrayList<>();

        collects.forEach((item)->{
            //item是有多条数据组成
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i <= item.size(); i++) {
                map.put(item.get(i-1).getPhysicalValueInfoId().toString(), item.get(i-1).getValue().toString());
            }
            map.put("createTime", format.format(new Date(item.get(0).getCreateTime())));
            respList.add(map);
        });
        respList.sort(Comparator.comparing(obj->
                obj.get("createTime")));
        PageResp<Map<String, String>> pageResp = new PageResp<>();
        pageResp.setTotal(total);
        pageResp.setList(respList);
        pageResp.setPageCount(req.getPageSize());
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

        //按日期分组
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
            PageHelper.startPage(req.getPage(), req.getPageSize());
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
    public int abnormalListTimes() {
        Long startTime = DateUtil.getStartTime().getTime();
        Long endTime = DateUtil.getEndTime().getTime();
        //联表查询出异常的数据
        final List<FbgValue> fbgValues = fbgValueMapper.selectForAbnormal(startTime, endTime);
        return fbgValues.size();
    }
    public int thisYearAbnormalListTimes() {
        Long startTime = DateUtil.getCurrYearFirst().getTime();
        Long endTime = DateUtil.getCurrYearLast().getTime();
        //联表查询出异常的数据
        final List<FbgValue> fbgValues = fbgValueMapper.selectForAbnormal(startTime, endTime);
        return fbgValues.size();
    }
    /**
     * 查询此时此刻温度传感器的温度值
     * 联合fbg_value和fbg_value_info两个表来进行查询：
     * 条件1：fbg_value_info的category为2
     * 条件2：fbg_value的create_time最大
     */
    public BigDecimal temperatureNow() {
        return fbgValueMapper.temperatureNow();
    }

    /**
     * 查询过去24小时的温度最大最小值
     * 联合fbg_value和fbg_value_info两个表来进行查询：
     * 条件1：fbg_value_info的category为2
     * 条件2：fbg_value 为最小 或者 最大
     * 条件3：按照时间范围查询
     * 传入时间参数
     * 要得到每个小时的最小值以及最大值
     * @return
     */
//    public List<LastNHoursMinAndMaxResp> minAndMaxFromLast24Hours() {
//        List<LastNHoursMinAndMaxResp> minAndMaxFromLast24Hours = new ArrayList<>();
//        for (int i = 1; i <= 24;i++) {
//            if (i == 1) {
//                minAndMaxFromLast24Hours.add(fbgValueMapper.minAndMaxFrom24Hours(DateUtil.LastNHoursStart(i).getTime(), DateUtil.getNowTime().getTime()));
//            }else{
//                minAndMaxFromLast24Hours.add(fbgValueMapper.minAndMaxFrom24Hours(DateUtil.LastNHoursStart(i).getTime(),DateUtil.LastNHoursStart(i - 1).getTime()));
//            }
//        }
//        //System.out.println(minAndMaxFromLast24Hours.toString());
//        return minAndMaxFromLast24Hours;
//    }
    public MinMaxValueForTemperature computeAndInsertTheMaxMinTemperatureInThisHour() throws ParseException {
        MinMaxValueForTemperature minMaxValueForTemperature = computeMinAndMax();
        insertToMinMaxValueForTemperature(minMaxValueForTemperature);
        return minMaxValueForTemperature;
    }
    public void insertToMinMaxValueForTemperature(MinMaxValueForTemperature minMaxValueForTemperature) {
        minMaxValueForTemperatureMapper.insert(minMaxValueForTemperature);
    }
    public MinMaxValueForTemperature computeMinAndMax() throws ParseException {
        Date now = new Date();
        SimpleDateFormat hourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date currentHourStart = hourSdf.parse(hourSdf.format(now));
        Date startTime = DateUtil.LastNHoursStartAt00mm00ss00sss(1);
        long time = startTime.getTime();
        FbgValueExample fbgValueExample = new FbgValueExample();
        FbgValueExample.Criteria criteria = fbgValueExample.createCriteria();
        Date date = new Date(time);
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(6);
        criteria.andCreateTimeGreaterThan(date.getTime()).andChannelIn(integers);
        List<FbgValue> fbgValues = fbgValueMapper.selectByExample(fbgValueExample);

        FbgValue fbgValue1 = fbgValues.stream().max(
                Comparator.comparing(FbgValue::getValue)
        ).get();
        FbgValue fbgValue2 = fbgValues.stream().min(
                Comparator.comparing(FbgValue::getValue)
        ).get();
        MinMaxValueForTemperature e = new MinMaxValueForTemperature();
        e.setCreateTime(currentHourStart);
        e.setMax(fbgValue1.getValue());
        e.setMin(fbgValue2.getValue());
        return e;
    }
}
