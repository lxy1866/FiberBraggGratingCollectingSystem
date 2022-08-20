package top.kaluna.pipelineMonitor.service;
import com.alibaba.excel.util.DateUtils;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.stereotype.Service;
import top.kaluna.pipelineMonitor.domain.*;
import top.kaluna.pipelineMonitor.mapper.AvgSensorMapper;
import top.kaluna.pipelineMonitor.mapper.ExcelDataMapper;
import top.kaluna.pipelineMonitor.util.DateUtil;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Yuery
 * @date 2022/5/20/0020 - 21:12
 */
@Service
public class AvgService {
    @Resource
    private AvgSensorMapper avgSensorMapper;
    @Resource
    private ExcelDataMapper excelDataMapper;
    /**
     * 将excel_data的数据插入avg_sensor表中 TODO 暂未处理一个月数据
     * @param sensorNum
     */
    public void getAvgAndInsert(int sensorNum, int arraySn) throws ParseException {

        //构造一个List
        //定时每周的第一天1点解析
        //那就要获取昨天的-1 -2 -3 …… -7天的早上00:00:00-12:00:00 和 下午12:00:00 - 24:00:00
        List<QueryTimeStartAndEnd> list = new ArrayList<>();
        Date date = DateUtil.getThisWeekMonday(DateUtil.getStartTime());
        //一周14个时间范围
        for(int index = 0; index < 14; index++){
            QueryTimeStartAndEnd queryTimeStartAndEnd = new QueryTimeStartAndEnd();
            queryTimeStartAndEnd.setStart(DateUtil.GivenTimeLastNHoursStart(date,  12 * (index+1)));
            queryTimeStartAndEnd.setEnd(DateUtil.GivenTimeLastNHoursStart(date, 12 * (index)));
            list.add(queryTimeStartAndEnd);
        }
        List<AvgSensor> avgSensors = new ArrayList<>();
        for(int i = 0; i < sensorNum; i++){
            for (QueryTimeStartAndEnd queryTimeStartAndEnd : list) {
                final AvgSensor avgSensor = setArgs(queryTimeStartAndEnd.getStart(), queryTimeStartAndEnd.getEnd(), "节点" + (i + 1), arraySn);
                avgSensors.add(avgSensor);
            }
        }
        final List<AvgSensor> collect = avgSensors.stream().sorted(Comparator.comparing(AvgSensor::getDate)).collect(Collectors.toList()).stream().sorted(Comparator.comparing(AvgSensor::getSensorNodeName)).collect(Collectors.toList());
        avgSensorMapper.insertBatch(collect);
    }

    public AvgSensor setArgs(Date start, Date end, String sensorNodeName, Integer arraySn){
        ExcelDataExample example = new ExcelDataExample();
        ExcelDataExample.Criteria criteria = example.createCriteria();
        criteria.andArraySnEqualTo(arraySn);
        criteria.andSensorNodeNameEqualTo(sensorNodeName);
        criteria.andDateBetween(start,end);
        final List<ExcelData> list = excelDataMapper.selectByExample(example);
        double sum = 0;
        for (ExcelData excelData : list) {
            sum += excelData.getZValue();
        }
        double avg = 0;
        if(list.size() == 0){
            avg = 0;
        }else{
            avg = sum / list.size();
        }
        AvgSensor avgSensor = new AvgSensor();
        avgSensor.setAvg(avg);
        avgSensor.setSensorNodeName(sensorNodeName);
        avgSensor.setArraySn(arraySn);
        avgSensor.setDate(DateUtil.GivenTimeLastNHoursStart(start, -1));
        return avgSensor;
    }
    /**
     * 将excel_data的数据插入avg_sensor表中 TODO
     */
    public void getAvgAndInsertForCommonData(int arraySn) throws ParseException {
        //构造一个List
        ExcelDataExample example = new ExcelDataExample();
        ExcelDataExample.Criteria criteria = example.createCriteria();
        criteria.andArraySnEqualTo(arraySn);
        final List<ExcelData> excelDataList = excelDataMapper.selectByExample(example);
        //分组
        final ArrayList<List<ExcelData>> groupList = new ArrayList<>(excelDataList.stream().collect(groupingBy((obj) -> {
            return DateUtils.format(obj.getDate()).split(" ")[0];
        })).values());
        //给每个时间中的每个节点计算平均值
        final List<AvgSensor> avgSensorList = calculateAvg(groupList, arraySn);

        avgSensorMapper.insertBatch(avgSensorList);
    }

    public List<AvgSensor> calculateAvg(ArrayList<List<ExcelData>> dateGroupList, int arraySn){
        //通过arraySn来判断节点的个数 arraySn为1的时候，节点个数为12
        List<AvgSensor> avgSensors = new ArrayList<>();
        dateGroupList.forEach((obj)->{
            final LinkedHashMap<String, ArrayList<ExcelData>> sensorNodeNameGroupbyData = obj.stream().collect(groupingBy(ExcelData::getSensorNodeName,
                    LinkedHashMap::new, Collectors.toCollection(ArrayList::new)));
            for(int i = 0; i < sensorNodeNameGroupbyData.size(); i++) {
                final ArrayList<ExcelData> arrayList = sensorNodeNameGroupbyData.get("节点"+(i+1));
                final double average = arrayList.stream().collect(Collectors.summarizingDouble(ExcelData::getZValue)).getAverage();
                avgSensors.add(new AvgSensor(arraySn, arrayList.get(0).getSensorNodeName(), average, arrayList.get(0).getDate()));
            }
        });
        return avgSensors;
    }
}
