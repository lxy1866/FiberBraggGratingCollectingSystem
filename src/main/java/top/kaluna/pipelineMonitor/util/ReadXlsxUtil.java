package top.kaluna.pipelineMonitor.util;

import com.alibaba.excel.util.StringUtils;
import jxl.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadXlsxUtil {

    public static ArrayList<String> readxlsxStr(String filepath, int index, int startrow) throws IOException {
        ArrayList<String> colList = new ArrayList<>();
        String value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int maxRow = sheet.getLastRowNum();
        for (int i = startrow;i < maxRow;i++){
            XSSFCell cell = sheet.getRow(i).getCell(index);
            if(StringUtils.isEmpty(cell.getStringCellValue())){
                continue;
            }
            value = cell.getStringCellValue();
            colList.add(value);
        }
        return colList;
    }

    public static ArrayList<Double> readxlsxDou(String filepath, int index, int startrow) throws IOException {
        ArrayList<Double> colList = new ArrayList<>();
        Double value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int maxRow = sheet.getLastRowNum();
        for (int i = startrow;i < maxRow;i++){
            XSSFCell cell = sheet.getRow(i).getCell(index);
            if(cell.getStringCellValue().contains("﹢")){
                value = Double.valueOf(cell.getStringCellValue().replace("﹢",""));
            }else{
                if(StringUtils.isEmpty(cell.getStringCellValue()) || isContainChinese(cell.getStringCellValue())){
                    value = null;
                }else{
                    value = Double.valueOf(cell.getStringCellValue());
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

}
