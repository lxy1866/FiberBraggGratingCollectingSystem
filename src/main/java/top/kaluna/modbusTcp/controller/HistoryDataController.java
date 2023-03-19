package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.mapper.HistoryQueryMapper;
import top.kaluna.modbusTcp.resp.CommonResp;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static top.kaluna.modbusTcp.util.ReadXlsxUtil.readxlsxStr;
import static top.kaluna.modbusTcp.util.ReadXlsxUtil.readxlsxVal;

@RestController
@RequestMapping("/historyData")
public class HistoryDataController {

    @Resource
    private HistoryQueryMapper historyQueryMapper;

    @GetMapping("/tempX")
    public CommonResp<List<String>> getHisX() throws IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsxStr(getLastModified("/root/fbgData/temperature").toString(),1,0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/tempY")
    public CommonResp<List<Double>> getHisY() throws IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsxVal(getLastModified("/root/fbgData/temperature").toString(),0,0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/strainX")
    public CommonResp<List<String>> getStrainX() throws IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = new ArrayList<>();
        for(int i=9;i<14;i++){
            if(i==13){
                for(int j=1;j<33;j++){
                    if(j==32){
                        String str = "2023/"+ Integer.toString(1)+"/"+Integer.toString(31);
                        list.add(str);
                        list.add(str);
                        list.add(str);
                    }else{
                        String str = "2023/"+ Integer.toString(1)+"/"+Integer.toString(j);
                        list.add(str);
                        list.add(str);
                        list.add(str);
                    }
                }
            }else{
                if(i==9 || i==11){
                    for(int j=1;j<33;j++){
                        if(j==31 || j==32){
                            String str = "2022/"+ Integer.toString(i)+"/"+Integer.toString(30);
                            list.add(str);
                            list.add(str);
                            list.add(str);
                        }else{
                            String str = "2022/"+ Integer.toString(i)+"/"+Integer.toString(j);
                            list.add(str);
                            list.add(str);
                            list.add(str);
                        }
                }}else{
                        for(int j=1;j<33;j++){
                            if(j==32){
                                String str = "2022/"+ Integer.toString(i)+"/"+Integer.toString(31);
                                list.add(str);
                                list.add(str);
                                list.add(str);
                            }else{
                                String str = "2022/"+ Integer.toString(i)+"/"+Integer.toString(j);
                                list.add(str);
                                list.add(str);
                                list.add(str);
                            }
                        }
                    }

            }
        }
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/strainY")
    public CommonResp<Map<String,List<Double>>> getStrainY() throws IOException {
        CommonResp<Map<String,List<Double>>> resp = new CommonResp<>();
        Map<String,List<Double>> result = new HashMap<>();
        List<Double> list1 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),0,0);
        List<Double> list2 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),1,0);
        List<Double> list3 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),2,0);
        List<Double> list4 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),3,0);
        List<Double> list5 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),4,0);
        List<Double> list6 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),5,0);
        List<Double> list7 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),6,0);
        List<Double> list8 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),7,0);
        List<Double> list9 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),8,0);
        List<Double> list10 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),9,0);
        List<Double> list11 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),10,0);
        List<Double> list12 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),11,0);
        List<Double> list13 = readxlsxVal(getLastModified("/root/fbgData/strain").toString(),12,0);
        result.put("strain1",list1);
        result.put("strain2",list2);
        result.put("strain3",list3);
        result.put("strain4",list4);
        result.put("strain5",list5);
        result.put("strain6",list6);
        result.put("strain7",list7);
        result.put("strain8",list8);
        result.put("strain9",list9);
        result.put("strain10",list10);
        result.put("strain11",list11);
        result.put("strain12",list12);
        result.put("strain13",list13);
        resp.setContent(result);
        return resp;
    }

    @GetMapping("/shortTimeX")
    public CommonResp<List<String>> getShortTimeX() throws IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsxStr("/root/fbgData/shorttime.xlsx",1,0);
//        List<String> list = readxlsxStr("F:\\数据\\短时能量.xlsx",1,0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/shortTimeY")
    public CommonResp<List<Double>> getShortTimeY() throws IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsxVal("/root/fbgData/shorttime.xlsx",0,0);
//        List<Double> list = readxlsxVal("F:\\数据\\短时能量.xlsx",0,0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/vibrationX")
    public CommonResp<List<String>> getVibrationX() throws IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsxStr("/root/fbgData/vibration.xlsx",1,0);
//        List<String> list = readxlsxStr("F:\\数据\\振动波长.xlsx",1,0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/vibrationY")
    public CommonResp<List<Double>> getVibrationY() throws IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsxVal("/root/fbgData/vibration.xlsx",0,0);
//        List<Double> list = readxlsxVal("F:\\数据\\振动波长.xlsx",0,0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/queryMEMS")
    @ResponseBody
    public CommonResp<Map<String,List<?>>> getQueryMEMS(String startTime, String endTime) throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        Long timeLong1 = Long.parseLong(startTime);
        Long timeLong2 = Long.parseLong(endTime);
        Date startTime_parse = format.parse(format.format(timeLong1));
        Date endTime_parse = format.parse(format.format(timeLong2));

        List<String> time25 = historyQueryMapper.selectT25(startTime_parse,endTime_parse);
        List<String> time26 = historyQueryMapper.selectT26(startTime_parse,endTime_parse);
        List<String> time27 = historyQueryMapper.selectT27(startTime_parse,endTime_parse);
        List<String> time28 = historyQueryMapper.selectT28(startTime_parse,endTime_parse);
        List<String> time29 = historyQueryMapper.selectT29(startTime_parse,endTime_parse);
        List<String> time30 = historyQueryMapper.selectT30(startTime_parse,endTime_parse);
        List<String> time31 = historyQueryMapper.selectT31(startTime_parse,endTime_parse);
        List<String> time32 = historyQueryMapper.selectT32(startTime_parse,endTime_parse);
        List<String> time33 = historyQueryMapper.selectT33(startTime_parse,endTime_parse);
        List<String> time34 = historyQueryMapper.selectT34(startTime_parse,endTime_parse);
        List<String> time35 = historyQueryMapper.selectT35(startTime_parse,endTime_parse);
        List<String> time36 = historyQueryMapper.selectT36(startTime_parse,endTime_parse);
        List<String> time37 = historyQueryMapper.selectT37(startTime_parse,endTime_parse);
        List<String> time38 = historyQueryMapper.selectT38(startTime_parse,endTime_parse);
        List<String> time39 = historyQueryMapper.selectT39(startTime_parse,endTime_parse);
        List<Double> x25 = historyQueryMapper.select25x(startTime_parse,endTime_parse);
        List<Double> y25 = historyQueryMapper.select25y(startTime_parse,endTime_parse);
        List<Double> z25 = historyQueryMapper.select25z(startTime_parse,endTime_parse);
        List<Double> x26 = historyQueryMapper.select26x(startTime_parse,endTime_parse);
        List<Double> y26 = historyQueryMapper.select26y(startTime_parse,endTime_parse);
        List<Double> z26 = historyQueryMapper.select26z(startTime_parse,endTime_parse);
        List<Double> x27 = historyQueryMapper.select27x(startTime_parse,endTime_parse);
        List<Double> y27 = historyQueryMapper.select27y(startTime_parse,endTime_parse);
        List<Double> z27 = historyQueryMapper.select27z(startTime_parse,endTime_parse);
        List<Double> x28 = historyQueryMapper.select28x(startTime_parse,endTime_parse);
        List<Double> y28 = historyQueryMapper.select28y(startTime_parse,endTime_parse);
        List<Double> z28 = historyQueryMapper.select28z(startTime_parse,endTime_parse);
        List<Double> x29 = historyQueryMapper.select29x(startTime_parse,endTime_parse);
        List<Double> y29 = historyQueryMapper.select29y(startTime_parse,endTime_parse);
        List<Double> z29 = historyQueryMapper.select29z(startTime_parse,endTime_parse);
        List<Double> x30 = historyQueryMapper.select30x(startTime_parse,endTime_parse);
        List<Double> y30 = historyQueryMapper.select30y(startTime_parse,endTime_parse);
        List<Double> z30 = historyQueryMapper.select30z(startTime_parse,endTime_parse);
        List<Double> x31 = historyQueryMapper.select31x(startTime_parse,endTime_parse);
        List<Double> y31 = historyQueryMapper.select31y(startTime_parse,endTime_parse);
        List<Double> z31 = historyQueryMapper.select31z(startTime_parse,endTime_parse);
        List<Double> x32 = historyQueryMapper.select32x(startTime_parse,endTime_parse);
        List<Double> y32 = historyQueryMapper.select32y(startTime_parse,endTime_parse);
        List<Double> z32 = historyQueryMapper.select32z(startTime_parse,endTime_parse);
        List<Double> x33 = historyQueryMapper.select33x(startTime_parse,endTime_parse);
        List<Double> y33 = historyQueryMapper.select33y(startTime_parse,endTime_parse);
        List<Double> z33 = historyQueryMapper.select33z(startTime_parse,endTime_parse);
        List<Double> x34 = historyQueryMapper.select34x(startTime_parse,endTime_parse);
        List<Double> y34 = historyQueryMapper.select34y(startTime_parse,endTime_parse);
        List<Double> z34 = historyQueryMapper.select34z(startTime_parse,endTime_parse);
        List<Double> x35 = historyQueryMapper.select35x(startTime_parse,endTime_parse);
        List<Double> y35 = historyQueryMapper.select35y(startTime_parse,endTime_parse);
        List<Double> z35 = historyQueryMapper.select35z(startTime_parse,endTime_parse);
        List<Double> x36 = historyQueryMapper.select36x(startTime_parse,endTime_parse);
        List<Double> y36 = historyQueryMapper.select36y(startTime_parse,endTime_parse);
        List<Double> z36 = historyQueryMapper.select36z(startTime_parse,endTime_parse);
        List<Double> x37 = historyQueryMapper.select37x(startTime_parse,endTime_parse);
        List<Double> y37 = historyQueryMapper.select37y(startTime_parse,endTime_parse);
        List<Double> z37 = historyQueryMapper.select37z(startTime_parse,endTime_parse);
        List<Double> x38 = historyQueryMapper.select38x(startTime_parse,endTime_parse);
        List<Double> y38 = historyQueryMapper.select38y(startTime_parse,endTime_parse);
        List<Double> z38 = historyQueryMapper.select38z(startTime_parse,endTime_parse);
        List<Double> x39 = historyQueryMapper.select39x(startTime_parse,endTime_parse);
        List<Double> y39 = historyQueryMapper.select39y(startTime_parse,endTime_parse);
        List<Double> z39 = historyQueryMapper.select39z(startTime_parse,endTime_parse);


        CommonResp<Map<String,List<?>>> resp = new CommonResp<>();
        Map<String,List<?>> result = new HashMap<>();

        result.put("time25",time25);
        result.put("time26",time26);
        result.put("time27",time27);
        result.put("time28",time28);
        result.put("time29",time29);
        result.put("time30",time30);
        result.put("time31",time31);
        result.put("time32",time32);
        result.put("time33",time33);
        result.put("time34",time34);
        result.put("time35",time35);
        result.put("time36",time36);
        result.put("time37",time37);
        result.put("time38",time38);
        result.put("time39",time39);
        result.put("x25",x25);
        result.put("y25",y25);
        result.put("z25",z25);
        result.put("x26",x26);
        result.put("y26",y26);
        result.put("z26",z26);
        result.put("x27",x27);
        result.put("y27",y27);
        result.put("z27",z27);
        result.put("x28",x28);
        result.put("y28",y28);
        result.put("z28",z28);
        result.put("x29",x29);
        result.put("y29",y29);
        result.put("z29",z29);
        result.put("x30",x30);
        result.put("y30",y30);
        result.put("z30",z30);
        result.put("x31",x31);
        result.put("y31",y31);
        result.put("z31",z31);
        result.put("x32",x32);
        result.put("y32",y32);
        result.put("z32",z32);
        result.put("x33",x33);
        result.put("y33",y33);
        result.put("z33",z33);
        result.put("x34",x34);
        result.put("y34",y34);
        result.put("z34",z34);
        result.put("x35",x35);
        result.put("y35",y35);
        result.put("z35",z35);
        result.put("x36",x36);
        result.put("y36",y36);
        result.put("z36",z36);
        result.put("x37",x37);
        result.put("y37",y37);
        result.put("z37",z37);
        result.put("x38",x38);
        result.put("y38",y38);
        result.put("z38",z38);
        result.put("x39",x39);
        result.put("y39",y39);
        result.put("z39",z39);

        resp.setContent(result);
        return resp;
    }

    @GetMapping("/query")
    @ResponseBody
    public CommonResp<Map<String,List<?>>> getQuery(String startTime, String endTime) throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        Long timeLong1 = Long.parseLong(startTime);
        Long timeLong2 = Long.parseLong(endTime);
        Date startTime_parse = format.parse(format.format(timeLong1));
        Date endTime_parse = format.parse(format.format(timeLong2));

        List<String> time = historyQueryMapper.selectT(startTime_parse,endTime_parse);
        List<Double> t1 = historyQueryMapper.selectT1(startTime_parse,endTime_parse);
        for (int i = 0; i < t1.size(); i++) {
            double value = t1.get(i);
            t1.set(i, value + 2);
        }
        List<Double> t2 = historyQueryMapper.selectT2(startTime_parse,endTime_parse);
        for (int i = 0; i < t2.size(); i++) {
            double value = t2.get(i);
            t2.set(i, value + 1);
        }
        List<Double> t3 = historyQueryMapper.selectT3(startTime_parse,endTime_parse);
        for (int i = 0; i < t3.size(); i++) {
            double value = t3.get(i);
            t3.set(i, value + 0.3);
        }
        List<Double> v = historyQueryMapper.selectV(startTime_parse,endTime_parse);
        List<Double> s1 = historyQueryMapper.selectS1(startTime_parse,endTime_parse);
        List<Double> s2 = historyQueryMapper.selectS2(startTime_parse,endTime_parse);
        List<Double> s3 = historyQueryMapper.selectS3(startTime_parse,endTime_parse);
        List<Double> s4 = historyQueryMapper.selectS4(startTime_parse,endTime_parse);
        List<Double> s5 = historyQueryMapper.selectS5(startTime_parse,endTime_parse);
        List<Double> s6 = historyQueryMapper.selectS6(startTime_parse,endTime_parse);
        List<Double> s7 = historyQueryMapper.selectS7(startTime_parse,endTime_parse);
        List<Double> s8 = historyQueryMapper.selectS8(startTime_parse,endTime_parse);
        List<Double> s9 = historyQueryMapper.selectS9(startTime_parse,endTime_parse);
        List<Double> s10 = historyQueryMapper.selectS10(startTime_parse,endTime_parse);
        List<Double> s11 = historyQueryMapper.selectS11(startTime_parse,endTime_parse);
        List<Double> s12 = historyQueryMapper.selectS12(startTime_parse,endTime_parse);
        List<Double> s13 = historyQueryMapper.selectS13(startTime_parse,endTime_parse);


        CommonResp<Map<String,List<?>>> resp = new CommonResp<>();
        Map<String,List<?>> result = new HashMap<>();

        result.put("time",time);
        result.put("temperature1",t1);
        result.put("temperature2",t2);
        result.put("temperature3",t3);
        result.put("vibration",v);
        result.put("strain1",s1);
        result.put("strain2",s2);
        result.put("strain3",s3);
        result.put("strain4",s4);
        result.put("strain5",s5);
        result.put("strain6",s6);
        result.put("strain7",s7);
        result.put("strain8",s8);
        result.put("strain9",s9);
        result.put("strain10",s10);
        result.put("strain11",s11);
        result.put("strain12",s12);
        result.put("strain13",s13);

        resp.setContent(result);
        return resp;
    }

//    @GetMapping("/shape")
//    public CommonResp<Map<String,List<Double>>> getShape() throws IOException {
//        CommonResp<Map<String,List<Double>>> resp = new CommonResp<>();
//        Map<String,List<Double>> result = new HashMap<>();
//        List<Double> list1 = readxlsxVal("F:\\数据\\数据.xlsx",2,0);
//        List<Double> list2 = readxlsxVal("F:\\数据\\数据.xlsx",6,0);
//        List<Double> list3 = readxlsxVal("F:\\数据\\数据.xlsx",10,0);
//        List<Double> list4 = readxlsxVal("F:\\数据\\数据.xlsx",14,0);
//        List<Double> list5 = readxlsxVal("F:\\数据\\数据.xlsx",18,0);
//        List<Double> list6 = readxlsxVal("F:\\数据\\数据.xlsx",22,0);
//        result.put("shape1",list1);
//        result.put("shape2",list2);
//        result.put("shape3",list3);
//        result.put("shape4",list4);
//        result.put("shape5",list5);
//        result.put("shape6",list6);
//        resp.setContent(result);
//        return resp;
//    }

    public static File getLastModified(String FilePath) {
        File directory = new File(FilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;
        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        return chosenFile;
    }

}
