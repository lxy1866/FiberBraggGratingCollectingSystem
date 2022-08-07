package top.kaluna.pipelineMonitor.util;

import com.csvreader.CsvReader;
import top.kaluna.pipelineMonitor.domain.ExcelData;
import top.kaluna.pipelineMonitor.service.TxtService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/6/1/0001 - 15:48
 */
public class ParseCsvUtil {
    /**
     * CsvReader 读取
     * @param filePath
     * @return
     */
    public static ArrayList<String[]> readCsvByCsvReader(String filePath,  int sensorNums, int arraySn) throws ParseException, IOException {
        ArrayList<String[]> arrList = new ArrayList<>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', StandardCharsets.UTF_8);
            while (reader.readRecord()) {
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        insertToExcelData(arrList, arraySn);
        return arrList;
    }

    /**
     *  插入一个文件的数据 也就是一周的数据 一个阵列的数据
     * @param arrayList 解析的每行数据
     * @param arraySn 阵列号
     * @throws ParseException 时间格式化解析异常
     */
    public static void insertToExcelData(ArrayList<String[]> arrayList, int arraySn) throws ParseException, IOException {
        List<ExcelData> list = new ArrayList<>();
        for (String[] strings : arrayList) {
            for (int j = 1; j <= (strings.length - 1) / 3; j++) {
                ExcelData excelData = new ExcelData(arraySn,"节点"+j,Double.parseDouble(strings[3*j-2]),Double.parseDouble(strings[3*j-1]),Double.parseDouble(strings[3*j]), new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(strings[0]));
                list.add(excelData);
            }
        }
        //这里借用之前用来保存解析txt文件后所得的数据的TxtService.save
        new TxtService().save(list);
    }
}
