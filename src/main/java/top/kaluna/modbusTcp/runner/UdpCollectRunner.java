package top.kaluna.modbusTcp.runner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.domain.FbgValue;
import top.kaluna.modbusTcp.mapper.FbgValueMapper;
import top.kaluna.modbusTcp.service.WsService;
import top.kaluna.modbusTcp.util.NumTypeChangeUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static top.kaluna.modbusTcp.util.ReadXlsxUtil.readxlsxVal;

@Component
@Order(1)
public class UdpCollectRunner  implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(UdpCollectRunner.class);
    @Resource
    private WsService wsService;
    @Resource
    private FbgValueMapper fbgValueMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }
    public void start() throws InterruptedException, IOException {
        boolean started = true;
        // 创建服务端接收socket对象
        LOG.info("监听4002端口开始");
        DatagramSocket ds = new DatagramSocket(4002);
        //接受振动波长数据
        byte[] bytes = new byte[64];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        // 调用DatagramSocket对象的方法接收数据

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // 解析数据包，并把数据在控制台显示
                byte[] datas = dp.getData();
                List<Float> Values = new ArrayList<>();
                //获取应变
                float s1 = (float) ((NumTypeChangeUtil.bytesToFloat3(datas, 6)/ 1000 +1520-1533.822)/4.79);
                float s2 = (float) ((NumTypeChangeUtil.bytesToFloat3(datas, 9)/ 1000 +1520-1544.772)/4.74);
                Values.add(s1);
                Values.add(s2);
                Values.add(s1);
                //websocket推送
                String logId = (String) MDC.get("LOG_ID");
                String jsonString = JSONObject.toJSONString(Values, SerializerFeature.MapSortField);
//                System.out.println("线程2："+jsonString);
                wsService.sendInfo(jsonString, logId);
//                存储到数据库
//                fbgValueMapper.multipleInsert(Values);
            }
        });

        //新启一个线程专门收集振动波长 每5秒渲染一次页面
        AtomicInteger count = new AtomicInteger(1);
        Thread vibrationWaveThread = new Thread(() ->{
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // 解析数据包，并把数据在控制台显示
                byte[] datas = dp.getData();
                //获取振动波长
                float s1 = NumTypeChangeUtil.bytesToFloat3(datas, 9)/ 1000 +1520;
                String key = "viberationWave";
                if(count.get() <= 500){
                    redisTemplate.opsForList().rightPush(key, s1);
                    count.getAndIncrement();
                }
                if(count.get() == 501){
                    count.set(1);
                    List list=redisTemplate.opsForList().range(key,0,-1);
                    redisTemplate.delete(key);
                    //websocket推送
                    String logId = (String) MDC.get("LOG_ID");
                    String jsonString = JSONObject.toJSONString(list, SerializerFeature.MapSortField);
                    System.out.println("线程："+jsonString);
                    wsService.sendVibrationWaveInfo(jsonString, logId);
                }
            }
        });

//        Thread MEMSThread =new Thread(() ->{
//            while(true){
//                List<Double> MEMSvalue = new ArrayList<>();
//                for(int i=2;i<23;i=i+4){
//                    try {
//                        MEMSvalue=readxlsxVal("/root/fbgData/data.xlsx",i,0);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    String logId = (String) MDC.get("LOG_ID");
//                    String jsonString = JSONObject.toJSONString(MEMSvalue, SerializerFeature.MapSortField);
//                    wsService.sendMEMSInfo(jsonString, logId);
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

        thread.start();
        vibrationWaveThread.start();
//        MEMSThread.start();
//        ds.close();
    }

}
