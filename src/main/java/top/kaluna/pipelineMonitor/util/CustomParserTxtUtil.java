package top.kaluna.pipelineMonitor.util;

import lombok.extern.slf4j.Slf4j;

import top.kaluna.pipelineMonitor.domain.ExcelData;
import top.kaluna.pipelineMonitor.service.TxtService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/24/0024 - 9:51
 */
/**
 * 这里虽说是excel，只是之前解析excel的方案，现在是直接解析txt了
 */
@Slf4j
public class CustomParserTxtUtil {



    /**
     * 规定的列数为3
     */
    private static final int GivenColumnNums = 3;

    public static void simpleRead(String filePath, int sensorNums, Integer arraySn) throws IOException, ParseException {
        File file = new File(filePath);
        List<ExcelData> list = txt2String(file,sensorNums,arraySn);
        long time1 = System.currentTimeMillis();
        new TxtService().save(list);
        long time2 = System.currentTimeMillis();
        log.info("插入耗时："+(time2 - time1));
    }
    public static ArrayList<ExcelData> txt2String(File file, int sensorNums,Integer arraySn) throws IOException, ParseException {
        int sensorRowNums = sensorNums / 3;
        if(sensorNums % 3 == 0){
            sensorRowNums = sensorRowNums+1;
        }else {
            sensorRowNums = sensorRowNums+2;
        }
        int Remainder = sensorNums % 3;
        ArrayList<ExcelData> arrayList = new ArrayList<>();
        try( BufferedReader br = new BufferedReader(new FileReader(file));){
            String line;
            String date = null;
            int index = 0;
            //增大到rowNums 就回到0
            int innerIndex = 0;
            while ((line = br.readLine()) != null){
                index++;
                innerIndex++;
                //记录时间
                if(index % sensorRowNums == 1){
                    date = line;
                }
                if((index % sensorRowNums != 1) && (index % sensorRowNums == innerIndex)){
                    readLine(arraySn, line,innerIndex - 2, date, arrayList, GivenColumnNums);
                }else if((sensorNums % 3 != 0) && index % sensorRowNums == 0) {
                    readLine(arraySn, line,3, date, arrayList, Remainder);
                    innerIndex = 0;
                }else if((sensorNums % 3 == 0) && index % sensorRowNums == 0){
                    readLine(arraySn, line, 3, date, arrayList,GivenColumnNums);
                    innerIndex = 0;
                }
            }
        }
        return arrayList;
    }
    public static void readLine(Integer arraySn, String line, int plus, String date, ArrayList<ExcelData> arrayList, int columnNums) throws ParseException {
        String[] value = line.split(",");
        //一行得到三个节点值，用逗号分割为9个值
        for(int i = 0; i < columnNums; i++){
            arrayList.add(new ExcelData(arraySn, "节点"+(i+1+plus*3),Double.valueOf(value[i*3]),Double.valueOf(value[i*3+1]),Double.valueOf(value[i*3+2]),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(date)));
        }
    }
}
