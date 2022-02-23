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
//import org.springframework.beans.factory.annotation.Autowired;
//import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
//import top.kaluna.modbusTcp.domain.PhysicalValue;
//
///**
// * @author 86158
// */
//public class MasterExample {
//    @Autowired
//    private PhysicalValueMapper physicalValueMapper;
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private final List<ModbusTcpMaster> masters = new CopyOnWriteArrayList<>();
//    private volatile boolean started = false;
//    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private static final int TOTALPHYSICVAL = 6;
//
//    private final int nMasters;
//    private final int nRequest;
//
//    public MasterExample(int nMasters, int nRequests) {
//        this.nMasters = nMasters;
//        this.nRequest = nRequests;
//    }
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
//
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
//                setSixValue(physicValueFloat,physicalValue);
//                //websocket推送
//
//                //存储到数据库
//                physicalValueMapper.insertSixPhysicalValue(physicalValue);
//                //删除map数据
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
//    }
//    public void setSixValue(Float[] physicValueFloat,PhysicalValue physicalValue){
//        physicalValue.setVal1(physicValueFloat[0]);
//        physicalValue.setVal2(physicValueFloat[1]);
//        physicalValue.setVal3(physicValueFloat[2]);
//        physicalValue.setVal4(physicValueFloat[3]);
//        physicalValue.setVal5(physicValueFloat[4]);
//        physicalValue.setVal6(physicValueFloat[5]);
//    }
//    public void stop() {
//        started = false;
//        masters.forEach(ModbusTcpMaster::disconnect);
//        masters.clear();
//    }
//}