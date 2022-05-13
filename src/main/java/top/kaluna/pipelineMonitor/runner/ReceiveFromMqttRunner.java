//package top.kaluna.pipelineMonitor.runner;
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import top.kaluna.pipelineMonitor.job.PhysicalValueJob;
//import top.kaluna.pipelineMonitor.service.WsService;
//import top.kaluna.pipelineMonitor.util.SnowFlake;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//
///**
// * @author Yuery
// * @date 2022/4/2/0002 - 15:34
// * 订阅mqtt服务器的内容
// */
//@Component
//@Order(1)
//public class ReceiveFromMqttRunner implements ApplicationRunner {
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        receive();
//    }
//    private static MqttClient connect() throws MqttException {
//        MemoryPersistence persistence = new MemoryPersistence();
//        MqttConnectOptions connOpts = new MqttConnectOptions();
////       String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.206:1883"};
//        connOpts.setCleanSession(false);
//        connOpts.setUserName("danly");
//        connOpts.setPassword("danly".toCharArray());
//        connOpts.setConnectionTimeout(10);
//        connOpts.setKeepAliveInterval(20);
////         connOpts.setServerURIs(uris);
////         connOpts.setWill(topic, "close".getBytes(), 2, true);
//        String broker = "tcp://101.132.252.118:1883";
//        MqttClient mqttClient = new MqttClient(broker, "client-id-1", persistence);
//        mqttClient.setCallback(new ReceiveCallback("test1"));
//        mqttClient.connect(connOpts);
//        return mqttClient;
//    }
//    private static void receive() throws MqttException{
//        int Qos  = 0;
//        String topic = "/tcp/strain";
//        MqttClient mqttClient = connect();
//        mqttClient.subscribe(topic, Qos);
//    }
//}
//class ReceiveCallback implements MqttCallback{
//
//    public ReceiveCallback(String threadId){
//    }
//    @Override
//    public void connectionLost(Throwable cause) {
//
//    }
//
//    @Override
//    public void messageArrived(String topic, MqttMessage message) throws Exception {
//        //发给websocket
//        final String string = message.toString();
//        PushToWebsocket.pushToWebsocket.doPushToWebsocket(string);
//        //不打算在这里传到数据库了，在本地即可，转换需要时间
//        //SaveDataToMysql.saveDataToMysql.doSaveDataToMysql(string);
//    }
//    @Override
//    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//
//    }
//    @Component
//    static class PushToWebsocket{
//        @Resource
//        private SnowFlake snowFlake;
//        @Resource
//        private WsService wsService;
//        private static PushToWebsocket pushToWebsocket;
//        @PostConstruct
//        public void init(){
//            pushToWebsocket = this;
//        }
//
//        private final Logger LOG = LoggerFactory.getLogger(PhysicalValueJob.class);
//
//        public void doPushToWebsocket(String string){
//
//            MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
//            LOG.info("推送新消息到websocket");
//            long start = System.currentTimeMillis();
//            wsService.sendInfo(string,MDC.get("LOG_ID"));
//            LOG.info("推送新消息结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//        }
//    }
////    @Component
////    static class SaveDataToMysql{
////
////        @Resource
////        FbgValueMapper fbgValueMapper;
////
////        private static SaveDataToMysql saveDataToMysql;
////        @PostConstruct
////        public void init(){
////            saveDataToMysql = this;
////        }
////        //存储到数据库
////        public void doSaveDataToMysql(String string){		//把Dao层的操作封装成一个方法
////            //这里省略了string转FbgValues
////            //fbgValueMapper.multipleInsert(fbgValues);
////        }
////    }
//}
