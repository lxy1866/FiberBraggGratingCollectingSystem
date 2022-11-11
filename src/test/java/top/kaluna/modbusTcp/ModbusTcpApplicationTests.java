//package top.kaluna.modbusTcp;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import top.kaluna.modbusTcp.domain.MinMaxValueForTemperature;
//import top.kaluna.modbusTcp.domain.MinMaxValueForTemperatureExample;
//import top.kaluna.modbusTcp.mapper.MinMaxValueForTemperatureMapper;
//import top.kaluna.modbusTcp.service.FbgValueService;
//import top.kaluna.modbusTcp.service.TxtValueService;
//import top.kaluna.modbusTcp.util.TxtResolver;
//
//import javax.annotation.Resource;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootTest(classes = ModbusTcpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//class ModbusTcpApplicationTests {
//
//	@Resource
//    private TxtResolver txtResolver;
//	@Resource
//	private TxtValueService txtValueService;
//
//	@Resource
//	private MinMaxValueForTemperatureMapper mapper;
//	@Test
//	void test1() {
//        txtResolver.readTxtFile("");
//	}
//	@Test
//	void test2() {
//		for(int i = 0; i < 10; i++){
//			String filePath = "D:/browserDownload/舟山数据源/9.2/Data_20220902_0"+ i+"0000.txt";
//			txtResolver.readTxtFile(filePath);
//		}
//		for(int i = 10; i < 24; i++){
//			String filePath = "D:/browserDownload/舟山数据源/9.2/Data_20220902_"+ i+"0000.txt";
//			txtResolver.readTxtFile(filePath);
//		}
//	}
////    @Test
////    void test3(){
////        txtResolver.readTxtPositionFile("D:\\browserDownload\\fbgProjectRelevantData\\data20220920\\22-08-29-15-24-12\\22-08-29-15-24-12.txt");
////    @Test
////    void testTime(){
////        System.out.println(new Date().getMonth());
////    }
//}
//
//
