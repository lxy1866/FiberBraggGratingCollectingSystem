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
//import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
//import top.kaluna.modbusTcp.domain.PhysicalValue;
//import top.kaluna.modbusTcp.service.WsService;
//import top.kaluna.modbusTcp.util.CommonUtil;
//import top.kaluna.modbusTcp.util.SnowFlake;
//
//import javax.annotation.Resource;
//
///**
// * @author 86158
// */
//@Component
//@Order(1)
//public class CollectDataRunner implements ApplicationRunner {
//    @Resource
//    private SnowFlake snowFlake;
//    @Resource
//    private WsService wsService;
//    @Autowired
//    private PhysicalValueMapper physicalValueMapper;
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private final List<ModbusTcpMaster> masters = new CopyOnWriteArrayList<>();
//    private volatile boolean started = false;
//    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private static final int TOTALPHYSICVAL = 6;
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
//                master.sendRequest(new ReadHoldingRegistersRequest(0, 12), 1);
//        future.whenCompleteAsync((response, ex) -> {
//            if (response != null) {
//                System.out.println("Response: "+ ByteBufUtil.hexDump(response.getRegisters()));
//
//                byte[][] bytes = new byte[6][4];
//                int[] physicValueInt = new int[6];
//                Float[] physicValueFloat = new Float[6];
//                //Map<String, Float> map = new HashMap<>(6);
//                /**
//                 * 这个physicalValue是映射到数据库对象
//                 */
//                PhysicalValue physicalValue = new PhysicalValue();
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
//                setSixValueAndTag(physicValueFloat,physicalValue);
//                //websocket推送
//                String logId = MDC.get("LOG_ID");
//                wsService.sendInfo(physicalValue.toString(), logId);
//                //存储到数据库
//                physicalValueMapper.insert(physicalValue);
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
//
//
//    }
//    public void setSixValueAndTag(Float[] physicValueFloat, PhysicalValue physicalValue){
//        Date date = new Date();
//        byte tag = 0;
//        physicalValue.setVal1(new BigDecimal(Float.toString(physicValueFloat[0])));
//        physicalValue.setVal2(new BigDecimal(Float.toString(physicValueFloat[1])));
//        physicalValue.setVal3(new BigDecimal(Float.toString(physicValueFloat[2])));
//        physicalValue.setVal4(new BigDecimal(Float.toString(physicValueFloat[3])));
//        physicalValue.setVal5(new BigDecimal(Float.toString(physicValueFloat[4])));
//        physicalValue.setVal6(new BigDecimal(Float.toString(physicValueFloat[5])));
//        if(physicalValue.getVal1() == null){
//            tag = 1;
//        }else if(physicalValue.getVal2() == null){
//            tag = 2;
//        }else if(physicalValue.getVal3() == null){
//            tag = 3;
//        }else if(physicalValue.getVal4() == null){
//            tag = 4;
//        }else if(physicalValue.getVal5() == null){
//            tag = 5;
//        }else if(physicalValue.getVal6() == null){
//            tag = 6;
//        }
//        physicalValue.setCreateTime(date.getTime());
//        physicalValue.setId(snowFlake.nextId());
//        physicalValue.setTag(tag);
//    }
//    public void stop() {
//        started = false;
//        masters.forEach(ModbusTcpMaster::disconnect);
//        masters.clear();
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        start();
//    }
//}