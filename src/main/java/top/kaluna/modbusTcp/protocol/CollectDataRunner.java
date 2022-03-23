//package top.kaluna.modbusTcp.protocol;/*
// * Copyright 2016 Kevin Herron
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//import io.netty.util.ReferenceCountUtil;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//
//import com.digitalpetri.modbus.master.ModbusTcpMaster;
//import com.digitalpetri.modbus.master.ModbusTcpMasterConfig;
//import com.digitalpetri.modbus.requests.ReadHoldingRegistersRequest;
//import com.digitalpetri.modbus.responses.ReadHoldingRegistersResponse;
//import io.netty.buffer.ByteBufUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import top.kaluna.modbusTcp.domain.FbgValue;
//import top.kaluna.modbusTcp.mapper.FbgValueInfoMapper;
//import top.kaluna.modbusTcp.mapper.FbgValueMapper;
//import top.kaluna.modbusTcp.service.WsService;
//import top.kaluna.modbusTcp.util.CommonUtil;
//import javax.annotation.Resource;
//
///**
// * @author 86158
// */
//@Component
//@Order(1)
//public class CollectDataRunner implements ApplicationRunner {
//    @Resource
//    private WsService wsService;
//    @Autowired
//    private FbgValueMapper fbgValueMapper;
//    @Autowired
//    private FbgValueInfoMapper fbgValueInfoMapper;
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private final List<ModbusTcpMaster> masters = new CopyOnWriteArrayList<>();
//    private volatile boolean started = false;
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//    private static int TOTALPHYSICVAL = 6;
//
//
//    public void start() throws InterruptedException {
//        started = true;
//
//        ModbusTcpMasterConfig config = new ModbusTcpMasterConfig.Builder("192.168.1.100")
//                .setPort(502)
//                .build();
//        ModbusTcpMaster master = new ModbusTcpMaster(config);
//        master.connect();
//
//        Thread thread = new Thread(() -> {
//            while (started) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                sendAndReceive(master);
//            }
//        });
//        thread.start();
//    }
//
//    private void sendAndReceive(ModbusTcpMaster master) {
//        if (!started){
//            return;
//        }
//        CompletableFuture<ReadHoldingRegistersResponse> future =
//                master.sendRequest(new ReadHoldingRegistersRequest(0, TOTALPHYSICVAL * 2), 1);
//        future.whenCompleteAsync((response, ex) -> {
//            if (response != null) {
//                System.out.println("Response: "+ ByteBufUtil.hexDump(response.getRegisters()));
//
//                byte[][] bytes = new byte[TOTALPHYSICVAL][4];
//                int[] physicValueInt = new int[TOTALPHYSICVAL];
//                Float[] physicValueFloat = new Float[TOTALPHYSICVAL];
//                //Map<String, Float> map = new HashMap<>(6);
//                List<FbgValue> fbgValues = new ArrayList<>();
//                //将解析出来的数据设置到physicValueFloat
//                for(int i = 0; i < TOTALPHYSICVAL; i++){
//                    bytes[i] = ByteBufUtil.getBytes(response.getRegisters(),i*4,4);
//                    //System.out.println("第"+(i+1)+"个"+"物理值: "+ByteBufUtil.hexDump(response.getRegisters(),i*4,4));
//                    physicValueInt[i] = CommonUtil.bytesToInt2(bytes[i],0);
//                    //System.out.println("第"+(i+1)+"个"+"物理值(转成十进制): "+ physicValue[i]);
//                    physicValueFloat[i] = (float)physicValueInt[i]/(float) 10000;
//                    System.out.println("第"+(i+1)+"个"+"物理值(转成十进制，除以10000): "+ physicValueFloat[i]);
//                    //map.put("第"+(i+1)+"个"+"物理值(转成十进制，除以10000): ", physicValueFloat[i]);
//                }
//                setSixValueAndTag(physicValueFloat,fbgValues);
//                //websocket推送
//                String logId = MDC.get("LOG_ID");
//                wsService.sendInfo(fbgValues.toString(), logId);
//                //存储到数据库
//                fbgValueMapper.multipleInsert(fbgValues);
//                ReferenceCountUtil.release(response);
//            } else {
//                logger.error("Completed exceptionally, message={}", ex.getMessage(), ex);
//            }
//        }, scheduler);
//    }
//
//    /**
//     *
//     */
//    public void websocketPush(){
//    }
//
//    /**
//     * 只能知道第一个断裂的位置 后面的若断裂了会通过定时任务扫描发现
//     * @param physicValueFloat 传感器实时监听到的应变值
//     * @param fbgValues 数据库中fbgValues的映射
//     */
//    public void setSixValueAndTag(Float[] physicValueFloat, List<FbgValue> fbgValues){
//        Date date = new Date();
//        byte tag = 0;
//        for (int i = 0; i < TOTALPHYSICVAL; i++){
//            if (physicValueFloat[i] == null && tag == 0){
//                tag = (byte) (i+1);
//            }else{
//                tag = 0;
//            }
//            fbgValues.add(FbgValue.Builder.builder()
//                    .withPhysicalValueInfoId((long) (i+1))
//                    .withValue(new BigDecimal(Float.toString(physicValueFloat[i]==null ? 0 : physicValueFloat[i])))
//                    .withTag(tag)
//                    .withCreateTime(date.getTime()).build());
//        }
//    }
//    public void stop() {
//        started = false;
//        masters.forEach(ModbusTcpMaster::disconnect);
//        masters.clear();
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        getTotal();
//        start();
//    }
//
//    private void getTotal() {
//        TOTALPHYSICVAL = fbgValueInfoMapper.total();
//    }
//}