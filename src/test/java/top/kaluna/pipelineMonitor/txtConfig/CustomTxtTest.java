//package top.kaluna.pipelineMonitor.txtConfig;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import top.kaluna.pipelineMonitor.PipelineMonitorApplication;
//import top.kaluna.pipelineMonitor.domain.AvgSensor;
//import top.kaluna.pipelineMonitor.domain.ExcelData;
//import top.kaluna.pipelineMonitor.mapper.AvgSensorMapper;
//import top.kaluna.pipelineMonitor.service.AvgService;
//import top.kaluna.pipelineMonitor.util.CustomParserTxtUtil;
//import top.kaluna.pipelineMonitor.util.DateUtil;
//import top.kaluna.pipelineMonitor.util.RandomUtil;
//import top.kaluna.pipelineMonitor.util.Txt2xlsxUtil;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Yuery
// * @date 2022/5/23/0023 - 20:59
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = PipelineMonitorApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CustomTxtTest {
//    @Resource
//    private AvgService avgService;
//    @Resource
//    private AvgSensorMapper avgSensorMapper;
//    @Test
//    public void testParseLocalFile()  {
//        final ArrayList<ExcelData> txtInfos = Txt2xlsxUtil.getTxtInfos(1);
//        Txt2xlsxUtil.TransToExcel(txtInfos);
//    }
//    @Test
//    public void testCustomParserTxt() throws IOException, ParseException {
//        CustomParserTxtUtil.simpleRead("D:/desktop/demandAnalysis/data/sensor1.txt",12,1);
//        CustomParserTxtUtil.simpleRead("D:/desktop/demandAnalysis/data/sensor2.txt",10,2);
//    }
//    @Test
//    public void testAvgService() throws ParseException {
//        avgService.getAvgAndInsert(12,1);
//        avgService.getAvgAndInsert(10,2);
//    }
//    @Test
//    public void testInsertCurveData(){
//        List<AvgSensor> list1 = new ArrayList<>();
//        List<AvgSensor> list2 = new ArrayList<>();
//        for(int i = 1; i <= 31; i++){
//            AvgSensor avgSensor1 = new AvgSensor(1, "节点12", RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetLastMonthStartTime(i, 1));
//            AvgSensor avgSensor2 = new AvgSensor(2, "节点10", RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetLastMonthStartTime(i, 1));
//            //节点12是定位环 接近37.8
//            //AvgSensor avgSensor1 = new AvgSensor(1, "节点12", RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetThisMonthStartTime(i, 1));
//            //节点10是结构管卡 接近 TODO
//            //AvgSensor avgSensor2 = new AvgSensor(2, "节点10", RandomUtil.doubleFrom100TO1000(), DateUtil.GivenDayAndHourGetThisMonthStartTime(i, 1));
//            list1.add(avgSensor1);
//            list2.add(avgSensor2);
//        }
//        avgSensorMapper.insertBatch(list1);
//        avgSensorMapper.insertBatch(list2);
//    }
//}
