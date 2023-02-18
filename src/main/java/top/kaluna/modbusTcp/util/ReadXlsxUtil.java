package top.kaluna.modbusTcp.util;

import com.alibaba.druid.util.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
            if(StringUtils.isEmpty(cell.toString())){
                continue;
            }
            value = cell.toString();
            colList.add(value);
        }
        return colList;
    }

    public static ArrayList<Double> readxlsxVal(String filepath, int index, int startrow) throws IOException {
        ArrayList<Double> colList = new ArrayList<>();
        Double value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int maxRow = sheet.getLastRowNum();
        for (int i = startrow;i < maxRow;i++){
            XSSFCell cell = sheet.getRow(i).getCell(index);
            if(StringUtils.isEmpty(cell.toString())){
                continue;
            }
            value = cell.getNumericCellValue();
            colList.add(value);
        }
        return colList;
    }

}
