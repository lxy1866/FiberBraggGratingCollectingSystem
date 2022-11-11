package top.kaluna.modbusTcp.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.domain.*;
import top.kaluna.modbusTcp.service.FbgValueInfoService;
import top.kaluna.modbusTcp.service.FbgValueService;
import top.kaluna.modbusTcp.service.PositionValueService;
import top.kaluna.modbusTcp.service.TxtValueService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class TxtResolver {

    @Resource
    private FbgValueInfoService fbgValueInfoService;
    @Resource
    private TxtValueService txtValueService;

    @Resource
    private PositionValueService positionValueService;

    public void readTxtFile(String filePath) {
        //现在规定读取的文件是按照如下格式的
        //日期（2022-09-17,10:00:00）
        // /t通道1中心波长1，物理值1（1554.940,21.94）
        // /t通道1中心波长2，物理值2
        // /t通道1中心波长3，物理值3
        // ...
        // /t通道1中心波长7，物理值7
        // /t |
        // /t通道2中心波长1，物理值1
        // ...
        // /t通道2中心波长7，物理值7
        // /t |
        // /t通道2中心波长1，物理值1
        // ...
        // /t通道2中心波长7，物理值7
        // ...
        // /t通道16中心波长1，物理值1
        // ...
        // /t通道16中心波长7，物理值7
        // /t |
        //读取传感器属性
        List<FbgValueInfo> propertyList = fbgValueInfoService.getAll();
        try {

            File file = new File(filePath);
            if (file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(
                        Files.newInputStream(file.toPath()));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                List<TxtValue> saveList = new ArrayList<>();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] line = lineTxt.split("\t");
                    String dateStrOld = line[0];
                    String dateStrNew = dateStrOld.replace(",", " ");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = sdf.parse(dateStrNew);
                    for (FbgValueInfo fbgValueInfo : propertyList) {
                        int channel = fbgValueInfo.getChannel();
                        int arrayNum = fbgValueInfo.getArrayNum();
                        String waveLengthPhysicalValue = line[9 * (channel-1) + arrayNum];
                        String value = waveLengthPhysicalValue.split(",")[1];
                        TxtValue txtValue = new TxtValue();
                        txtValue.setValue(BigDecimal.valueOf(Double.parseDouble(value)));
                        txtValue.setChannel(channel);
                        txtValue.setArrayNum(arrayNum);
                        txtValue.setCreateTime(parsedDate);
                        txtValue.setPropertyName(fbgValueInfo.getPropertyName());
                        saveList.add(txtValue);
                    }
                }
                txtValueService.insert(saveList);
                //批量插入数据库
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public void readTxtFileSecondVersion(String prefixFilePath){
        //读取传感器属性
        List<FbgValueInfo> propertyList = fbgValueInfoService.getAll();
        //读取3 5 应变 1 2 6 温度 4 振动 一共6个文件
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HH0000");
        String dateFilePath = sdf.format(date);
        //应变
        readTxtFileSecondVersionForSingleFile(propertyList, prefixFilePath, dateFilePath, 1, 3);
        readTxtFileSecondVersionForSingleFile(propertyList, prefixFilePath, dateFilePath, 1, 5);
        //温度
        readTxtFileSecondVersionForSingleFile(propertyList, prefixFilePath, dateFilePath, 2, 1);
        readTxtFileSecondVersionForSingleFile(propertyList, prefixFilePath, dateFilePath, 2, 2);
        readTxtFileSecondVersionForSingleFile(propertyList, prefixFilePath, dateFilePath, 2, 6);
        //振动
        readTxtFileSecondVersionForSingleFile(propertyList, prefixFilePath, dateFilePath, 3, 4);

    }
    public void readTxtFileSecondVersionForSingleFile(List<FbgValueInfo> propertyList, String prefixFilePath, String dateFilePath, int category, int channel){
        String completeFilePath = prefixFilePath+"Wave_Ch0"+channel+"_"+dateFilePath+".txt";
        try {
            File file = new File(completeFilePath);
            if (file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(
                        Files.newInputStream(file.toPath()));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                List<TxtValue> saveList = new ArrayList<>();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] line = lineTxt.split("\t");
                    String dateStrOld = line[2];
                    String dateStrNew = dateStrOld.replace(",", " ");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = sdf.parse(dateStrNew);
                    //应变
                    if(category == 1 && channel == 3){
                        //读取6个值
                        for(int i = 0 ; i < 6; i++){
                            String waveLengthPhysicalValue = line[3+i];
                            String value = waveLengthPhysicalValue.split(",")[1];
                            TxtValue txtValue = new TxtValue();
                            txtValue.setValue(BigDecimal.valueOf(Double.parseDouble(value)));
                            txtValue.setChannel(channel);
                            txtValue.setArrayNum(i+1);
                            txtValue.setCreateTime(parsedDate);
                            txtValue.setPropertyName("strain"+(i+1));
                            saveList.add(txtValue);
                        }
                    }
                    if(category == 1 && channel == 5){
                        //读取7个值
                        for(int i = 0 ; i < 7; i++){
                            String waveLengthPhysicalValue = line[3+i];
                            String value = waveLengthPhysicalValue.split(",")[1];
                            TxtValue txtValue = new TxtValue();
                            txtValue.setValue(BigDecimal.valueOf(Double.parseDouble(value)));
                            txtValue.setChannel(channel);
                            txtValue.setArrayNum(i+1);
                            txtValue.setCreateTime(parsedDate);
                            txtValue.setPropertyName("strain"+(i+7));
                            saveList.add(txtValue);
                        }
                    }
                    //温度 channel 1 2 6
                    if(category == 2){
                        //读取一个值
                        String waveLengthPhysicalValue = line[3];
                        String value = waveLengthPhysicalValue.split(",")[1];
                        TxtValue txtValue = new TxtValue();
                        txtValue.setValue(BigDecimal.valueOf(Double.parseDouble(value)));
                        txtValue.setChannel(channel);
                        txtValue.setArrayNum(1);
                        txtValue.setCreateTime(parsedDate);
                        if(channel == 1){
                            txtValue.setPropertyName("T1");
                        }
                        if(channel == 2){
                            txtValue.setPropertyName("T2");
                        }
                        if(channel == 6){
                            txtValue.setPropertyName("T3");
                        }
                        saveList.add(txtValue);
                    }
                    //振动
                    if(category == 3){
                        //读取一个值
                        String waveLengthPhysicalValue = line[3];
                        String value = waveLengthPhysicalValue.split(",")[1];
                        TxtValue txtValue = new TxtValue();
                        txtValue.setValue(BigDecimal.valueOf(Double.parseDouble(value)));
                        txtValue.setChannel(channel);
                        txtValue.setArrayNum(1);
                        txtValue.setCreateTime(parsedDate);
                        if(channel == 1){
                            txtValue.setPropertyName("T1");
                        }
                        saveList.add(txtValue);
                    }
                }
                txtValueService.insert(saveList);
                //批量插入数据库
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public void generateFakeFbgValue(FbgValueInfoService fbgValueInfoService, TxtValueService txtValueService) throws ParseException {
        //读取传感器属性
        List<FbgValueInfo> propertyList = fbgValueInfoService.getAll();
        Date now = new Date();
        List<TxtValue> saveList = new ArrayList<>();
        SimpleDateFormat hourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date currentHourStart = hourSdf.parse(hourSdf.format(now));
        long time = currentHourStart.getTime();
        long realTime = 0;
        for(int i = 0; i < 3600; i++){
            realTime = time + 1000 * i;
            for (FbgValueInfo fbgValueInfo : propertyList) {
                extracted(saveList, realTime, fbgValueInfo);
            }
        }
        //Packet for query is too large (4,392,092 > 4,194,304). You can change this value on the server by setting the 'max_allowed_packet' variable.;
        txtValueService.insert(saveList.subList(0, saveList.size()/2));
        txtValueService.insert(saveList.subList(saveList.size()/2+1, saveList.size()));
    }

    private static void extracted(List<TxtValue> saveList, long realTime, FbgValueInfo fbgValueInfo) {
        Random random = new Random();
        double value = 0d;

        TxtValue txtValue = new TxtValue();
        int channel = fbgValueInfo.getChannel();
        int arrayNum = fbgValueInfo.getArrayNum();
        txtValue.setChannel(channel);
        txtValue.setArrayNum(arrayNum);

        txtValue.setCreateTime(new Date(realTime));
        txtValue.setPropertyName(fbgValueInfo.getPropertyName());

        if(fbgValueInfo.getCategory() == 1 ){
            if(fbgValueInfo.getChannel() == 3 && fbgValueInfo.getArrayNum() == 1){
                value = random.nextDouble()*2 - 15;
            }
            if(fbgValueInfo.getChannel() == 3 && fbgValueInfo.getArrayNum() == 2){
                value = random.nextDouble()*2 - 9;
            }
            if(fbgValueInfo.getChannel() == 3 && fbgValueInfo.getArrayNum() == 3){
                value = random.nextDouble()*2 - 28;
            }
            if(fbgValueInfo.getChannel() == 3 && fbgValueInfo.getArrayNum() == 4){
                value = random.nextDouble()*2 - 39;
            }
            if(fbgValueInfo.getChannel() == 3 && fbgValueInfo.getArrayNum() == 5){
                value = random.nextDouble()*2 - 28;
            }
            if(fbgValueInfo.getChannel() == 3 && fbgValueInfo.getArrayNum() == 6){
                value = random.nextDouble()*2 - 1;
            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 1){
                value = random.nextDouble()*2 - 26;
            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 2){
                value = random.nextDouble()*2 - 13;

            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 3){
                value = random.nextDouble()*2 - 24;
            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 4){
                value = random.nextDouble()*2 - 48;

            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 5){
                value = random.nextDouble()*2 - 23;

            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 6){
                value = random.nextDouble()*2 - 27;

            }
            if(fbgValueInfo.getChannel() == 5 && fbgValueInfo.getArrayNum() == 7){
                value = random.nextDouble()*2 - 8;

            }
        }
        if(fbgValueInfo.getCategory() == 2){
            if(fbgValueInfo.getChannel() == 1){
                value = random.nextDouble()*2 + 20;
            }
            if(fbgValueInfo.getChannel() == 2){
                value = random.nextDouble()*2 + 20;
            }
            if(fbgValueInfo.getChannel() == 6){
                value = random.nextDouble()*2 + 20;
            }
        }
        if(fbgValueInfo.getCategory() == 3){
            value = random.nextDouble()*2 + 40;
        }
        txtValue.setValue(BigDecimal.valueOf(value));
        saveList.add(txtValue);
    }
    public void readTxtPositionFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(
                        Files.newInputStream(file.toPath()));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                List<PositionValue> saveList = new ArrayList<>();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    PositionValue positionValue = new PositionValue();
                    String[] line = lineTxt.split(" ");
                    String dateStrOld1 = line[0];
                    String dateStrOld2 = line[1];
                    String dateStrNew1 = "20" + dateStrOld1.replace("/", "-");
                    String dateStrNew = dateStrNew1 + " " + dateStrOld2;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = sdf.parse(dateStrNew);
                    
                    String hexadecimal = line[2];

                    String dataStr = line[3];
                    String[] data = dataStr.split(",");
                    BigDecimal[] realData = new BigDecimal[9];
                    for(int i = 0; i < data.length; i++){
                        realData[i] = new BigDecimal(data[i]);
                    }

                    positionValue.setCreateTime(parsedDate);
                    positionValue.setHexadecimal(hexadecimal);
                    positionValue.setAngleX(realData[0]);
                    positionValue.setAngleY(realData[1]);
                    positionValue.setAngleZ(realData[2]);
                    positionValue.setaX(realData[3]);
                    positionValue.setaY(realData[4]);
                    positionValue.setaZ(realData[5]);
                    positionValue.setwX(realData[6]);
                    positionValue.setwY(realData[7]);
                    positionValue.setwZ(realData[8]);
                    
                    saveList.add(positionValue);
                }
                positionValueService.insert(saveList);
                //批量插入数据库
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public MinMaxValueForTemperature computeAndInsertTheMaxMinTemperatureInThisHour(TxtValueService txtValueService) throws ParseException {
        MinMaxValueForTemperature minMaxValueForTemperature = txtValueService.computeMinAndMax();
        txtValueService.insertToMinMaxValueForTemperature(minMaxValueForTemperature);
        return minMaxValueForTemperature;
    }
    //复制8月29日的数据
    public void generateFakeMemsValue(PositionValueService positionValueService) {
        //先把8月29日的数据拿出来
        List<PositionValue> positionValues = positionValueService.query8Month29Day();
        positionValueService.insert(positionValues);
    }

    public void generateFakeTemperatureValue(TxtValueService txtValueService) {
        List<MinMaxValueForTemperature> list = txtValueService.query9Month2Day();
        MinMaxValueForTemperature minMaxValueForTemperature = list.get(0);
        //不变的两个 分和秒
        LocalDateTime localDateTime = LocalDateTime.now();
        //当前date的分和秒重新赋值为minutes 和 seconds
        int hours = minMaxValueForTemperature.getCreateTime().getHours();
        LocalDateTime localDateTime1 = localDateTime.withMinute(0);
        LocalDateTime localDateTime2 = localDateTime1.withSecond(0);
        LocalDateTime localDateTime3 = localDateTime2.withHour(hours);
        Instant instant = Instant.ofEpochSecond(localDateTime3.toEpochSecond(ZoneOffset.ofHours(8)));
        Date date = Date.from(instant);
        minMaxValueForTemperature.setCreateTime(date);
        minMaxValueForTemperature.setId(null);
        txtValueService.insertToMinMaxValueForTemperature(minMaxValueForTemperature);
    }
}
