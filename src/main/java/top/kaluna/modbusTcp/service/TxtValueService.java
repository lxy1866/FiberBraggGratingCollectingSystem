package top.kaluna.modbusTcp.service;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.*;
import top.kaluna.modbusTcp.mapper.FbgValueInfoMapper;
import top.kaluna.modbusTcp.mapper.MinMaxValueForTemperatureMapper;
import top.kaluna.modbusTcp.mapper.PositionValueMapper;
import top.kaluna.modbusTcp.mapper.TxtValueMapper;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class TxtValueService {

    @Resource
    private TxtValueMapper txtValueMapper;
    @Resource
    private FbgValueInfoMapper fbgValueInfoMapper;
    private final Logger LOG = LoggerFactory.getLogger(TxtValueService.class);
    @Resource
    private PositionValueMapper positionValueMapper;
    @Resource
    private MinMaxValueForTemperatureMapper minMaxValueForTemperatureMapper;
    public int insert(List<TxtValue> list){
        int i = txtValueMapper.multipleInsert(list);
        return i;
    }

    public PageResp<Map<String, String>> list(DateRangeReq req) throws ParseException {
        Long startTime;
        Long endTime;
        TxtValueExample txtValueExample = new TxtValueExample();
        TxtValueExample.Criteria criteria = txtValueExample.createCriteria();

        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        startTime = req.getStartTime();
        endTime =  req.getEndTime();

        if(startTime == null || endTime == null){
            return null;
        }
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ,Locale.US);
        format.setTimeZone(new SimpleTimeZone(0, "GMT"));
        criteria.andCreateTimeBetween(format.parse(format.format(startTime)),format.parse(format.format(endTime)));
        //分页前先算好总条数
        final long totalProperty = fbgValueInfoMapper.countByExample(fbgValueInfoExample);
        final long total = txtValueMapper.countByExample(txtValueExample) / totalProperty;
        PageHelper.startPage(req.getPage(), (int) (req.getPageSize() * totalProperty));
        //PageInfo<TxtValue> pageInfo = new PageInfo<>();
        //查询到指定时间范围的数据
        final List<TxtValue> txtValues = txtValueMapper.selectByExample(txtValueExample);
        //分组
        final Collection<List<TxtValue>> collects = txtValues.stream().collect(groupingBy(TxtValue::getCreateTime)).values();
        //分成很多组，每组有多条数据
        List<Map<String, String>> respList = new ArrayList<>();
        collects.forEach((item)->{
            //item是有多条数据组成
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i <= item.size(); i++) {
                map.put(item.get(i-1).getPropertyName(), item.get(i-1).getValue().toString());
            }
            map.put("createTime", item.get(0).getCreateTime().toString());
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
    public List<List<Object>> listLastHour(DateRangeReq req, int category) throws ParseException {
        Long startTime;
        Long endTime;
        TxtValueExample txtValueExample = new TxtValueExample();
        TxtValueExample.Criteria criteria = txtValueExample.createCriteria();

        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        FbgValueInfoExample.Criteria criteria1 = fbgValueInfoExample.createCriteria();
        criteria1.andCategoryEqualTo(category);

        List<FbgValueInfo> list = fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
        List<String> listPropertyNames = new ArrayList<>();
        list.forEach(obj->{
            listPropertyNames.add(obj.getPropertyName());
        });
        //查询匹配的传感器名字
        criteria.andPropertyNameIn(listPropertyNames);

        startTime = req.getStartTime();
        endTime =  req.getEndTime();
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        criteria.andCreateTimeBetween(format.parse(format.format(startTime)),format.parse(format.format(endTime)));
        //查询到指定时间范围的数据
        final List<TxtValue> txtValues = txtValueMapper.selectByExample(txtValueExample);
        List<List<Object>> result = new ArrayList<>();
        txtValues.forEach(obj->{
            List list1 = new ArrayList<>();
            list1.add(obj.getPropertyName());
            list1.add(obj.getValue());
            list1.add(obj.getCreateTime().toString());
            result.add(list1);
        });
        return result;
    }
    public List<Map<String, Object>> listLastHourForVibration(DateRangeReq req, int category) throws ParseException {
        Long startTime;
        Long endTime;
        TxtValueExample txtValueExample = new TxtValueExample();
        TxtValueExample.Criteria criteria = txtValueExample.createCriteria();

        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        FbgValueInfoExample.Criteria criteria1 = fbgValueInfoExample.createCriteria();
        criteria1.andCategoryEqualTo(category);

        List<FbgValueInfo> list = fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
        List<String> listPropertyNames = new ArrayList<>();
        list.forEach(obj->{
            listPropertyNames.add(obj.getPropertyName());
        });
        //查询匹配的传感器名字
        criteria.andPropertyNameIn(listPropertyNames);

        startTime = req.getStartTime();
        endTime =  req.getEndTime();
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        criteria.andCreateTimeBetween(format.parse(format.format(startTime)),format.parse(format.format(endTime)));
        //查询到指定时间范围的数据
        final List<TxtValue> txtValues = txtValueMapper.selectByExample(txtValueExample);
        txtValues.stream().sorted(Comparator.comparing(TxtValue::getCreateTime));

        ArrayList<Map<String, Object>> result = new ArrayList<>();
        txtValues.forEach(obj->{
            Map map = new HashMap();
            map.put("name", obj.getCreateTime());
            ArrayList<Object> value = new ArrayList<>();
            value.add(obj.getCreateTime());
            value.add(obj.getValue());
            map.put("value", value);
            result.add(map);
        });
        return result;
    }
    public Double getLastHourAtTheLastSecondForTemperature(DateRangeReq req) throws ParseException {
        Long endTime;
        TxtValueExample txtValueExample = new TxtValueExample();
        TxtValueExample.Criteria criteria = txtValueExample.createCriteria();

        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        FbgValueInfoExample.Criteria criteria1 = fbgValueInfoExample.createCriteria();
        criteria1.andCategoryEqualTo(req.getCategory());

        List<FbgValueInfo> list = fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
        List<String> listPropertyNames = new ArrayList<>();
        list.forEach(obj->{
            listPropertyNames.add(obj.getPropertyName());
        });
        //查询匹配的传感器名字
        criteria.andPropertyNameIn(listPropertyNames);
        endTime =  req.getEndTime();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        criteria.andCreateTimeEqualTo(format.parse(format.format(endTime)));

        //查询到指定时间的数据
        final List<TxtValue> txtValues = txtValueMapper.selectByExample(txtValueExample);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < txtValues.size(); i++) {
           sum = sum.add(txtValues.get(i).getValue());
        }
        Double average = sum.doubleValue() / txtValues.size();
        return average;
    }

    /**
     * 按照正确的时间应该调用这个方法
     * @return 过去24小时温度的在每个时间点的最大最小值
     */
    public List<MinMaxValueForTemperature> minAndMaxFromLast24Hours() {
        List<MinMaxValueForTemperature> minAndMaxFromLast24Hours = new ArrayList<>();
        for (int i = 1; i <= 24;i++) {
            minAndMaxFromLast24Hours.add(txtValueMapper.minAndMaxFrom24Hours(DateUtil.LastNHoursStart(i)));
        }
        //System.out.println(minAndMaxFromLast24Hours.toString());
        return minAndMaxFromLast24Hours;
    }
    public List<MinMaxValueForTemperature> minAndMaxFromLast24HoursForTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2022,9,3,0,0,0);
        //获得 Instant
        Instant instant = Instant.ofEpochSecond(localDateTime.toEpochSecond(ZoneOffset.ofHours(8)));
        // 获得 Date
        Date date = Date.from(instant);
        List<MinMaxValueForTemperature> minAndMaxFromLast24Hours = new ArrayList<>();
        for (int i = 1; i <= 24;i++) {
            minAndMaxFromLast24Hours.add(txtValueMapper.minAndMaxFrom24Hours(DateUtil.LastNHoursStartOnTheDate(date, i)));
        }
        //System.out.println(minAndMaxFromLast24Hours.toString());
        return minAndMaxFromLast24Hours;
    }
    public PageResp<PositionValue> listPosition(DateRangeReq req) throws ParseException {
        Long startTime;
        Long endTime;
        PositionValueExample positionValueExample = new PositionValueExample();
        PositionValueExample.Criteria criteria = positionValueExample.createCriteria();

        startTime = req.getStartTime();
        endTime =  req.getEndTime();
        if(startTime == null  || endTime == null){
            return null;
        }
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ,Locale.US);
        format.setTimeZone(new SimpleTimeZone(0, "GMT"));
        criteria.andCreateTimeBetween(format.parse(format.format(startTime)),format.parse(format.format(endTime)));
        long count = positionValueMapper.countByExample(positionValueExample);
        PageHelper.startPage(req.getPage(), req.getPageSize());

        //查询到指定时间范围的数据
        final List<PositionValue> positionValues = positionValueMapper.selectByExample(positionValueExample);
        positionValues.sort(Comparator.comparing(PositionValue::getCreateTime));
        LOG.info("MEMS传感器历史数据时间问题："+positionValues.get(0));
        PageResp<PositionValue> pageResp = new PageResp<>();
        pageResp.setTotal(count);
        pageResp.setList(positionValues);
        pageResp.setPageCount(req.getPageSize());

        return pageResp;
    }

//
//    /**
//     *
//     * @return
//     */
//    //获取过去24小时温度最大最小值
//    public List<MinMaxValueForTemperature> minAndMaxFromLast24Hours3() {
//        List<MinMaxValueForTemperature> results = new ArrayList<>();
//        for (int i = 0; i <= 23;i++) {
//            //Date date1 = new Date(2022, Calendar.SEPTEMBER, 2, i, 0, 0);
//            //Date date2 = new Date(2022, Calendar.SEPTEMBER, 2, i, 59, 59);
//            LocalDateTime date1 = LocalDateTime.of(2022,9,2,i,0,0);
//            LocalDateTime date2 = LocalDateTime.of(2022,9,2,i,59,59);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
////            System.out.println(sdf.format(date1));
////            System.out.println(sdf.format(date2));
//            LastNHoursMinAndMaxResp lastNHoursMinAndMaxResp = txtValueMapper
//                    .minAndMaxFrom24Hours(date1, date2);
//            MinMaxValueForTemperature value = new MinMaxValueForTemperature();
//            value.setMin(lastNHoursMinAndMaxResp.getMin());
//            value.setMax(lastNHoursMinAndMaxResp.getMax());
//            // 获得 Instant
//            Instant instant = Instant.ofEpochSecond(date1.toEpochSecond(ZoneOffset.ofHours(8)));
//            // 获得 Date
//            Date date = Date.from(instant);
//            value.setCreateTime(date);
//            results.add(value);
//        }
//        //System.out.println(minAndMaxFromLast24Hours.toString());
//        return results;
//    }
    public MinMaxValueForTemperature computeMinAndMax() throws ParseException {
        Date now = new Date();
        SimpleDateFormat hourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date currentHourStart = hourSdf.parse(hourSdf.format(now));
        long time = currentHourStart.getTime();
        TxtValueExample txtValueExample = new TxtValueExample();
        TxtValueExample.Criteria criteria = txtValueExample.createCriteria();
        Date date = new Date(time);
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(6);
        criteria.andCreateTimeGreaterThan(date).andChannelIn(integers);
        List<TxtValue> txtValues = txtValueMapper.selectByExample(txtValueExample);

        TxtValue txtValue1 = txtValues.stream().max(
                Comparator.comparing(TxtValue::getValue)
        ).get();
        TxtValue txtValue2 = txtValues.stream().min(
                Comparator.comparing(TxtValue::getValue)
        ).get();
        MinMaxValueForTemperature e = new MinMaxValueForTemperature();
        e.setCreateTime(currentHourStart);
        e.setMax(txtValue1.getValue());
        e.setMin(txtValue2.getValue());
        return e;
    }

    public void insertToMinMaxValueForTemperature(MinMaxValueForTemperature minMaxValueForTemperature) {
        minMaxValueForTemperatureMapper.insert(minMaxValueForTemperature);
    }

    public List<MinMaxValueForTemperature> query9Month2Day() {
        MinMaxValueForTemperatureExample example = new MinMaxValueForTemperatureExample();
        MinMaxValueForTemperatureExample.Criteria criteria = example.createCriteria();
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour = localDateTime.getHour();
        criteria.andIdEqualTo(hour+1);
        List<MinMaxValueForTemperature> list = minMaxValueForTemperatureMapper.selectByExample(example);
        return list;
    }
}
