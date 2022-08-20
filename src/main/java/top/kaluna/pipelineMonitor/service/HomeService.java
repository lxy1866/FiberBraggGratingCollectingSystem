package top.kaluna.pipelineMonitor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.kaluna.pipelineMonitor.domain.*;
import top.kaluna.pipelineMonitor.mapper.AvgSensorMapper;
import top.kaluna.pipelineMonitor.mapper.HomePageLeftTopMapper;
import top.kaluna.pipelineMonitor.mapper.HomePageMiddleBottomMapper;
import top.kaluna.pipelineMonitor.req.LeftTopAttributeReq;
import top.kaluna.pipelineMonitor.req.MiddleBottomAttributeReq;
import top.kaluna.pipelineMonitor.util.CopyUtil;
import top.kaluna.pipelineMonitor.util.DateUtil;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yuery
 * @date 2022/5/18/0018 - 21:34
 */
@Service
@Slf4j
public class HomeService {

    @Resource
    private HomePageLeftTopMapper homePageLeftTopMapper;
    @Resource
    private HomePageMiddleBottomMapper homePageMiddleBottomMapper;
    @Resource
    private AvgSensorMapper avgSensorMapper;

    public Boolean leftTopAttributeSave(LeftTopAttributeReq req){
        HomePageLeftTop homePageLeftTop = CopyUtil.copy(req, HomePageLeftTop.class);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < req.getEachArrayNum().size(); i++){
            stringBuilder.append(req.getEachArrayNum().get(i));
            if(i != req.getEachArrayNum().size() - 1){
                stringBuilder.append('_');
            }else {
                break;
            }
        }
        homePageLeftTop.setEachArrayNum(stringBuilder.toString());
        HomePageLeftTopExample homePageLeftTopExample = new HomePageLeftTopExample();
        HomePageLeftTopExample.Criteria criteria = homePageLeftTopExample.createCriteria();
        criteria.andIdEqualTo(1L);

        final List<HomePageLeftTop> homePageLeftTops = homePageLeftTopMapper.selectByExample(homePageLeftTopExample);

        if(homePageLeftTops.size()!=0){
            homePageLeftTop.setId(1L);
            homePageLeftTopMapper.updateByExample(homePageLeftTop,homePageLeftTopExample);
        }else{
            homePageLeftTopMapper.insert(homePageLeftTop);
        }
        return true;
    }
    public Boolean middleBottomAttributeSave(MiddleBottomAttributeReq req){

        HomePageMiddleBottom homePageMiddleBottom = CopyUtil.copy(req, HomePageMiddleBottom.class);
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0 ; i < req.getArrayNode().size(); i++){
            stringBuilder.append(req.getArrayNode().get(i).getKey());
            stringBuilder.append('_');
            stringBuilder.append(req.getArrayNode().get(i).getValue());
            if(i != req.getArrayNode().size() - 1){
                stringBuilder.append('_');
            }
        }
        homePageMiddleBottom.setArrayNode(stringBuilder.toString());

        HomePageMiddleBottomExample homePageMiddleBottomExample = new HomePageMiddleBottomExample();
        HomePageMiddleBottomExample.Criteria criteria = homePageMiddleBottomExample.createCriteria();
        criteria.andIdEqualTo(1L);
        final List<HomePageMiddleBottom> homePageMiddleBottoms = homePageMiddleBottomMapper.selectByExample(homePageMiddleBottomExample);
        if(homePageMiddleBottoms.size()!=0){
            homePageMiddleBottom.setId(1L);
            homePageMiddleBottomMapper.updateByExample(homePageMiddleBottom,homePageMiddleBottomExample);
        }else{
            homePageMiddleBottomMapper.insert(homePageMiddleBottom);
        }
        return true;
    }


    public HomePageLeftTop leftTopAttributeGet() {
        HomePageLeftTopExample example = new HomePageLeftTopExample();
        final List<HomePageLeftTop> homePageLeftTops = homePageLeftTopMapper.selectByExample(example);
        return homePageLeftTops.get(0);
    }

    public List<Object> leftTopDataGet() {
        //返回前一周的数据的查询条件
        AvgSensorExample avgSensorExample = new AvgSensorExample();
        AvgSensorExample.Criteria criteriaForWeek = avgSensorExample.createCriteria();
        //返回前一个月的数据的查询条件
        AvgSensorExample avgSensorExample1 = new AvgSensorExample();
        AvgSensorExample.Criteria criteriaForMonth = avgSensorExample1.createCriteria();

        //先查询上一个月的数据，如果一个月的数据全都有了，则轮播一个月上午下午的数据
        criteriaForMonth.andDateBetween(DateUtil.getLastMonthStartTime(),DateUtil.getThisMonthStartTime());
        final List<AvgSensor> listForMonth = avgSensorMapper.selectByExample(avgSensorExample1);
        final Map<Date, List<AvgSensor>> collectForMonthUnsorted = listForMonth.stream().collect(Collectors.groupingBy(AvgSensor::getDate));
        //先查询上一个月的数据，如果一个月的数据全都有了，则轮播上一个月上午下午的数据
        if(collectForMonthUnsorted.size() == DateUtil.getTotalDays(DateUtil.getLastMonthStartTime())*2){
            //按时间排序
            final List<AvgSensor> collectForMonth1 = listForMonth.stream().sorted(Comparator.comparing(AvgSensor::getDate)).collect(Collectors.toList());
            //按节点名称排序
            final List<AvgSensor> collectForMonth2 = collectForMonth1.stream().sorted(Comparator.comparing(obj -> Integer.valueOf(obj.getSensorNodeName().split("节点")[1]))).collect(Collectors.toList());
            final List<AvgSensor> collectForMonth3 = collectForMonth2.stream().sorted(Comparator.comparing(AvgSensor::getArraySn)).collect(Collectors.toList());
            final List<Object> objects = Arrays.asList(collectForMonth3.stream().collect(Collectors.groupingBy(AvgSensor::getDate, LinkedHashMap::new, Collectors.toCollection(ArrayList::new))).values().toArray());
            log.info("左上角的图表轮播上一个月上午下午的数据");
            return objects;
        }else if(collectForMonthUnsorted.size() == 14){
            criteriaForWeek.andDateBetween(DateUtil.getLastWeekMonday(DateUtil.getStartTime()), DateUtil.getThisWeekMonday(DateUtil.getStartTime()));
            final List<AvgSensor> list = avgSensorMapper.selectByExample(avgSensorExample);
            //按时间排序
            final List<AvgSensor> collect1 = list.stream().sorted(Comparator.comparing(AvgSensor::getDate)).collect(Collectors.toList());
            //按节点名称排序
            final List<AvgSensor> collect2 = collect1.stream().sorted(Comparator.comparing(obj -> Integer.valueOf(obj.getSensorNodeName().split("节点")[1]))).collect(Collectors.toList());
            //按阵列号排序
            final List<AvgSensor> collect3 = collect2.stream().sorted(Comparator.comparing(AvgSensor::getArraySn)).collect(Collectors.toList());
            //按时间分组
            final List<Object> values = Arrays.asList(collect3.stream().collect(Collectors.groupingBy(AvgSensor::getDate,LinkedHashMap::new, Collectors.toCollection(ArrayList::new))).values().toArray());
            log.info("左上角的图表轮播上一周上午下午的数据");
            return values;
        }
        return null;
    }
    public List<Object> leftTopTempDataGet() {
        //返回前一周的数据的查询条件
        AvgSensorExample avgSensorExample = new AvgSensorExample();
        AvgSensorExample.Criteria criteria = avgSensorExample.createCriteria();

        final List<AvgSensor> listForMonth = avgSensorMapper.selectByExample(avgSensorExample);
        final Map<Date, List<AvgSensor>> collectForMonthUnsorted = listForMonth.stream().collect(Collectors.groupingBy(AvgSensor::getDate));

        //按时间排序
        final List<AvgSensor> collectForMonth1 = listForMonth.stream().sorted(Comparator.comparing(AvgSensor::getDate)).collect(Collectors.toList());
        //按节点名称排序
        final List<AvgSensor> collectForMonth2 = collectForMonth1.stream().sorted(Comparator.comparing(obj -> Integer.valueOf(obj.getSensorNodeName().split("节点")[1]))).collect(Collectors.toList());
        final List<Object> objects = Arrays.asList(collectForMonth2.stream().collect(Collectors.groupingBy(AvgSensor::getDate, LinkedHashMap::new, Collectors.toCollection(ArrayList::new))).values().toArray());
        log.info("左上角的图表轮播临时数据");
        return objects;
    }
    public List<List<Double>> middleBottomGet() {
        //1_12_2_10
        final String[] arrayNodeNums = getArrayNodeNums();
        //存储结果
        List<List<Double>> avgList = new ArrayList<>();
        for(int i = 0; i < arrayNodeNums.length/2; i++){
            avgList.add(middleBottomGetForSingleNode(arrayNodeNums[i*2+1], i+1));
        }
        //System.err.println(avgList);
        return avgList;
    }
    public List<Double> middleBottomGetForSingleNode(String lastNode, Integer arraySn){
        //返回前一个月的数据 每个阵列最后一个节点的值
        AvgSensorExample avgSensorExample = new AvgSensorExample();
        AvgSensorExample.Criteria criteria = avgSensorExample.createCriteria();
        //前一个月的第一天 到 这个月的第一天
        criteria.andDateBetween(DateUtil.getLastMonthStartTime(), DateUtil.getThisMonthStartTime());
        //匹配节点名称
        criteria.andSensorNodeNameEqualTo("节点"+Integer.parseInt(lastNode));
        //匹配阵列序号
        criteria.andArraySnEqualTo(arraySn);
        final List<AvgSensor> avgSensors = avgSensorMapper.selectByExample(avgSensorExample);
        //按照日期排序
        final List<AvgSensor> collect1 = avgSensors.stream().sorted(Comparator.comparing(AvgSensor::getDate)).collect(Collectors.toList());
        //System.err.println("collect1:"+collect1.toString());
        //按照年月日分组
        final Map<String, List<AvgSensor>> collect = collect1.stream().collect(Collectors.groupingBy(obj -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(obj.getDate());
            final int i = cal.get(Calendar.DAY_OF_YEAR);
            return Integer.toString(i);
        }, LinkedHashMap::new, Collectors.toCollection(ArrayList::new)));
        //System.out.println("collect:"+collect.toString());
        //存储其中一个阵列的最后一个节点的前一个月数据
        List<Double> doubles = new ArrayList<>();
        for(String key : collect.keySet()){
            final Double aDouble = collect.get(key).stream().collect(Collectors.averagingDouble(AvgSensor::getAvg));
            doubles.add(aDouble);
        }
        return doubles;
    }
    public List<Double> rightTopGetForLatestData(){
        final String[] arrayNodeNums = getArrayNodeNums();
        //存储结果
        List<Double> avgList = new ArrayList<>();
        for(int i = 0; i < arrayNodeNums.length/2; i++){
            avgList.add(rightTopGetForSingleValue(arrayNodeNums[i*2+1], i+1));
        }
        System.err.println(avgList);
        return avgList;
    }
    public Double rightTopGetForSingleValue(String s, Integer arraySn){
        AvgSensorExample avgSensorExample = new AvgSensorExample();
        AvgSensorExample.Criteria criteria = avgSensorExample.createCriteria();
        //匹配节点名称
        criteria.andSensorNodeNameEqualTo("节点"+Integer.parseInt(s));
        //匹配阵列序号
        criteria.andArraySnEqualTo(arraySn);
        //找到上周最后一个时间范围的数据
        criteria.andDateBetween(DateUtil.GivenTimeLastNHoursStart(DateUtil.getThisWeekMonday(DateUtil.getStartTime()), 12),DateUtil.getThisWeekMonday(DateUtil.getStartTime()));
        final List<AvgSensor> avgSensors = avgSensorMapper.selectByExample(avgSensorExample);
        if(avgSensors.size() != 0){
            return avgSensors.get(0).getAvg();
        }
        return null;
    }
    public String[] getArrayNodeNums(){
        //查询各个阵列节点数量 获得每个阵列最后一个节点名称
        HomePageMiddleBottomExample homePageMiddleBottomExample = new HomePageMiddleBottomExample();
        final List<HomePageMiddleBottom> homePageMiddleBottoms = homePageMiddleBottomMapper.selectByExample(homePageMiddleBottomExample);
        final String arrayNode = homePageMiddleBottoms.get(0).getArrayNode();
        final String[] s = arrayNode.split("_");
        return  s;
    }
}
