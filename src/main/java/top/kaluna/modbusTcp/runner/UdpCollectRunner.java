//package top.kaluna.modbusTcp.runner;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//
//@Component
//@Order(1)
//public class UdpCollectRunner  implements ApplicationRunner {
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // 创建服务端接收socket对象
//        DatagramSocket ds = new DatagramSocket(22);
//        // 创建数据包用于接收数据
//        byte[] bytes = new byte[1024];
//        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
//        // 调用DatagramSocket对象的方法接收数据
//        ds.receive(dp);
//        // 解析数据包，并把数据在控制台显示
//        byte[] datas = dp.getData();
//        int len = dp.getLength();
//        String data = new String(datas, 0, len);
//        System.out.println("服务端接收到数据 " + data);
//        // 关闭接收端
//        ds.close();
//
//    }
//}
