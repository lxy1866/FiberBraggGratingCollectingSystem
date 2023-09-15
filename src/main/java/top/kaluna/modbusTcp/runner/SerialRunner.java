package top.kaluna.modbusTcp.runner;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import gnu.io.*;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.domain.PositionValue;
import top.kaluna.modbusTcp.service.WsService;
import top.kaluna.modbusTcp.websocket.WebSocketMEMSServer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class SerialRunner {

    @Resource
    private WsService wsService;

    @PostConstruct
    public void init() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException {

        DecimalFormat df=new DecimalFormat("0.000");
        List MEMSvalue =new ArrayList();

        for (int i = 0; i < 8; i++) {
            PositionValue init = new PositionValue();
            init.setaX(new BigDecimal(i+1));
            init.setaY(new BigDecimal(i+1));
            init.setaZ(new BigDecimal(i+1));
            MEMSvalue.add(init);
        }

        String portName = "COM4"; // 串口名称，根据具体情况修改
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        CommPort commPort = portIdentifier.open(portName, 2000);
        SerialPort serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(38400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        byte[] command2 = { (byte)0xFF, (byte)0xA5, 0x10, 0x06, 0x15,
                0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte)0x2C, 0x26  }; // 要发送的指令
        OutputStream out = serialPort.getOutputStream();
        InputStream in = serialPort.getInputStream();
        out.write(command2);

        Thread thread = new Thread(() -> {
            try {
                int numBytes = -1;
                byte[] readBuffer = new byte[32];
                while (true) {
                    numBytes = in.read(readBuffer);

                    if (numBytes > 0) {
                        switch (toHexString(readBuffer[5])){
                            case "20":
                                PositionValue sensor20 = new PositionValue();
                                sensor20.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor20.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor20.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(0,sensor20);
                                break;
                            case "21":
                                PositionValue sensor21 = new PositionValue();
                                sensor21.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor21.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor21.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(1,sensor21);
                                break;
                            case "22":
                                PositionValue sensor22 = new PositionValue();
                                sensor22.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor22.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor22.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(2,sensor22);
                                break;
                            case "23":
                                PositionValue sensor23 = new PositionValue();
                                sensor23.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor23.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor23.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(3,sensor23);
                                break;
                            case "24":
                                PositionValue sensor24 = new PositionValue();
                                sensor24.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor24.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor24.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(4,sensor24);
                                break;
                            case "25":
                                PositionValue sensor25 = new PositionValue();
                                sensor25.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor25.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor25.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(5,sensor25);
                                break;
                            case "26":
                                PositionValue sensor26 = new PositionValue();
                                sensor26.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor26.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor26.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(6,sensor26);
                                break;
                            case "27":
                                PositionValue sensor27 = new PositionValue();
                                sensor27.setaX(new BigDecimal(df.format((double)bytesToInt(readBuffer[8],readBuffer[9])/32768*16*9.8)));
                                sensor27.setaY(new BigDecimal(df.format((double)bytesToInt(readBuffer[10],readBuffer[11])/32768*16*9.8)));
                                sensor27.setaZ(new BigDecimal(df.format((double)bytesToInt(readBuffer[12],readBuffer[13])/32768*16*9.8)));
                                MEMSvalue.set(7,sensor27);
                                break;
                        }
                        String logId = (String) MDC.get("LOG_ID");
                        String jsonString = JSONObject.toJSONString(MEMSvalue, SerializerFeature.MapSortField);
                        System.out.println(jsonString);
                        wsService.sendMEMSInfo(jsonString, logId);
                        readBuffer = new byte[32];// 重新构造缓冲对象，否则有可能会影响接下来接收的数据
                    } else {
                        System.out.println("额没有读到数据");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
        thread.start();
    }

    public static String toHexString(byte data) {
        StringBuilder hexString = new StringBuilder();
        // 将字节转换为无符号整数，并转换为两位十六进制数
        String hex = Integer.toHexString(Byte.toUnsignedInt(data)).toUpperCase();
        if (hex.length() == 1) {
            hexString.append("0");
        }
        hexString.append(hex).append(" ");
        return hexString.toString().trim();
    }

    public static String toHexString(byte[] data) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : data) {
            // 将字节转换为无符号整数，并转换为两位十六进制数
            String hex = Integer.toHexString(Byte.toUnsignedInt(b)).toUpperCase();
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex).append(" ");
        }
        return hexString.toString().trim();
    }

    public static int bytesToInt(byte data1,byte data2) {
        StringBuilder hexString = new StringBuilder();
        // 将字节转换为无符号整数，并转换为两位十六进制数
        String hex1 = Integer.toHexString(Byte.toUnsignedInt(data1)).toUpperCase();
        if (hex1.length() == 1) {
            hexString.append("0");
        }
        String hex2 = Integer.toHexString(Byte.toUnsignedInt(data2)).toUpperCase();
        if (hex2.length() == 1) {
            hexString.append("0");
        }
        hexString.append(hex1).append(hex2);
        int result=Integer.valueOf(hexString.toString(),16).shortValue();
        return result;
    }
}
