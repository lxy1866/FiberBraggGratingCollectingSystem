package top.kaluna.pipelineMonitor.service;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.stereotype.Service;
import top.kaluna.pipelineMonitor.domain.AvgSensor;
import top.kaluna.pipelineMonitor.domain.ExcelData;
import top.kaluna.pipelineMonitor.domain.ExcelDataExample;
import top.kaluna.pipelineMonitor.domain.QueryTimeStartAndEnd;
import top.kaluna.pipelineMonitor.mapper.AvgSensorMapper;
import top.kaluna.pipelineMonitor.mapper.ExcelDataMapper;
import top.kaluna.pipelineMonitor.util.DateUtil;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
}
