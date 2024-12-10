package top.kaluna.pipelineMonitor.util;

import com.alibaba.excel.util.StringUtils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadXlsUtil {

    public static ArrayList<String> readxlsStr(String filepath, int index, int startrow) throws IOException, BiffException {
        ArrayList<String> colList = new ArrayList<>();
        String value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        for (int i = startrow;i < rows;i++){
            Cell cell = sheet.getCell(index,i);
            if(StringUtils.isEmpty(cell.getContents())){
                continue;
            }
            value = cell.getContents();
            colList.add(value);
        }
        return colList;
    }

    public static ArrayList<Double> readxlsDou(String filepath, int index, int startrow) throws IOException, BiffException {
        ArrayList<Double> colList = new ArrayList<>();
        Double value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        for (int i = startrow;i < rows;i++){
            Cell cell = sheet.getCell(index,i);

            if(cell.getContents().contains("﹢")){
                value = Double.valueOf(cell.getContents().replace("﹢",""));
            }else{
                if(StringUtils.isEmpty(cell.getContents()) || isContainChinese(cell.getContents())){
                    value = null;
                }else{
                    value = Double.valueOf(cell.getContents());
                }
            }
            colList.add(value);
        }
        return colList;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static double readLastValue(String filepath, int index) throws IOException, BiffException {
        Double value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        Cell cell = sheet.getCell(index,rows-1);
        value = Double.valueOf(cell.getContents());
        return value;
    }


}
