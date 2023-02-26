package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.resp.CommonResp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.kaluna.modbusTcp.util.ReadXlsxUtil.readxlsxStr;
import static top.kaluna.modbusTcp.util.ReadXlsxUtil.readxlsxVal;

@RestController
@RequestMapping("/historyData")
public class HistoryDataController {

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
