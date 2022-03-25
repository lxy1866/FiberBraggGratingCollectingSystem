//package top.kaluna.modbusTcp.protocol;
//
//import org.slf4j.MDC;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import top.kaluna.modbusTcp.service.WsService;
//
//import javax.annotation.Resource;
//
///**
// * @author Yuery
// * @date 2022/3/25/0025 - 15:02
// */
//@Component
//@Order(1)
//public class CollectDataRunnerTest implements ApplicationRunner {
//
//    @Resource
//    private WsService wsService;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        //websocket推送
//        String logId = MDC.get("LOG_ID");
//        wsService.sendInfo("99999", logId);
//    }
//
//}
