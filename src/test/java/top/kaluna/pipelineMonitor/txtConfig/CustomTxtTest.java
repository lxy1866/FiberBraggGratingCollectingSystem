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
//import top.kaluna.pipelineMonitor.service.HomeService;
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
//    @Resource
//    private HomeService homeService;
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
//    //2022.8.19-12%变形点1管道位移原始数据最后一个节点是定位环一共是10个节点
//    //2022.8.19-12%变形点2传感阵列原始数据最后一个节点是管卡一共是10个节点
//    //2022.8.19-22%变形点管道位移原始数据最后一个节点是管卡一共是12个节点
//    @Test
//    public void testCustomParserTxt20220819() throws IOException, ParseException {
//        CustomParserTxtUtil.simpleRead("D:/desktop/demandAnalysis/data/20220819/2022.8.19-12%变形点1管道位移原始数据最后一个节点是定位环一共是10个节点.csv",12,1);
//        CustomParserTxtUtil.simpleRead("D:/desktop/demandAnalysis/data/sensor2.txt",10,2);
//    }
//    @Test
//    public void testAvgService() throws ParseException {
//        avgService.getAvgAndInsert(12,1);
//        avgService.getAvgAndInsert(10,2);
//    }
//
//    /**
//     * 给曲线赋值
//     */
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
//    //生成这个月的数据 假设是7月
//    @Test
//    public void testGenerateLastMonthData(){
//        List<AvgSensor> list = new ArrayList<>();
//        for(int i = 1; i <= 31; i++){
//            //10个传感器
//            for(int  j = 1; j <= 10; j++){
//                AvgSensor avgSensor1 = new AvgSensor(2, "节点"+(j), RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetThisMonthStartTime(i, 1));
//                AvgSensor avgSensor2 = new AvgSensor(2, "节点"+(j), RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetThisMonthStartTime(i, 14));
//                list.add(avgSensor1);
//                list.add(avgSensor2);
//            }
//            //12个传感器
//            for(int k = 1; k <= 12; k++){
//                AvgSensor avgSensor3 = new AvgSensor(1, "节点"+(k), RandomUtil.doubleFrom30TO4O(),DateUtil.GivenDayAndHourGetThisMonthStartTime(i,1));
//                AvgSensor avgSensor4 = new AvgSensor(1, "节点"+(k), RandomUtil.doubleFrom30TO4O(),DateUtil.GivenDayAndHourGetThisMonthStartTime(i,14));
//                list.add(avgSensor3);
//                list.add(avgSensor4);
//            }
//        }
//        avgSensorMapper.insertBatch(list);
//    }
//    @Test
//    public void testGenerateThisMonthData(){
//        List<AvgSensor> list = new ArrayList<>();
//        //生成上个月的数据 假设是6月
//        for(int i = 1; i <= 30; i++){
//            for(int  j = 1; j <= 10; j++){
//                AvgSensor avgSensor1 = new AvgSensor(2, "节点"+(j), RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetLastMonthStartTime(i, 1));
//                AvgSensor avgSensor2 = new AvgSensor(2, "节点"+(j), RandomUtil.doubleFrom30TO4O(), DateUtil.GivenDayAndHourGetLastMonthStartTime(i, 14));
//                list.add(avgSensor1);
//                list.add(avgSensor2);
//            }
//            for(int k = 1; k <= 12; k++){
//                AvgSensor avgSensor3 = new AvgSensor(1, "节点"+(k), RandomUtil.doubleFrom30TO4O(),DateUtil.GivenDayAndHourGetLastMonthStartTime(i,1));
//                AvgSensor avgSensor4 = new AvgSensor(1, "节点"+(k), RandomUtil.doubleFrom30TO4O(),DateUtil.GivenDayAndHourGetLastMonthStartTime(i,14));
//                list.add(avgSensor3);
//                list.add(avgSensor4);
//            }
//        }
//        avgSensorMapper.insertBatch(list);
//    }
//    @Test
//    public void testQueryLeftTopData(){
//        homeService.leftTopDataGet();
//    }
//}
