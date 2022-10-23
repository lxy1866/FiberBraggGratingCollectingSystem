package top.kaluna.pipelineMonitor.util;

import com.alibaba.excel.util.StringUtils;
import org.apache.poi.ss.usermodel.*;
import top.kaluna.pipelineMonitor.domain.VariantPoint;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadExcelUtil {

    public static ArrayList<VariantPoint> readExcelConstruction(String filepath,int startrow,int valueOne,int valueTwo) throws IOException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        ArrayList<VariantPoint> result = new ArrayList<>();

        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();

        for(int i = startrow;i < rows;i++){
            VariantPoint variantPoint = new VariantPoint();
            Double value = 0.0;
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(valueOne);
            Cell cell2 = row.getCell(valueTwo);
            String date = cell1.toString();
            if(cell2==null || isContainChinese(cell2.toString())){
                value = 0.0;
            }else{
                if(cell2.getCellType()==CellType.STRING){
                    if(cell2.toString().contains("﹢")){
                        value = Double.valueOf(cell2.toString().replace("﹢",""));
                    }else {
                        value =Double.valueOf(cell2.toString());
                    }
                }else {
                    value = cell2.getNumericCellValue();
                }

            }
//            if(cell2.toString().contains("﹢")){
//                value = Double.valueOf(cell2.toString().replace("﹢",""));
//            }else{
//                if(StringUtils.isEmpty(cell2.toString())){
//                    break;
//                }else{
//                    value = Double.valueOf(cell2.toString());
//                }
//
//            }

            variantPoint = setValue(variantPoint, "date", "value", date, value);
            result.add(variantPoint);
        }

        return result;
    }


    private static VariantPoint setValue(VariantPoint variantPoint, String dateName, String valueName, String date, Double value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Class<? extends VariantPoint> clazz = variantPoint.getClass();
        PropertyDescriptor descriptor1 = new PropertyDescriptor(dateName,clazz);
        PropertyDescriptor descriptor2 = new PropertyDescriptor(valueName,clazz);
        Method method1 = descriptor1.getWriteMethod();
        Method method2 = descriptor2.getWriteMethod();
        method1.invoke(variantPoint,date);
        method2.invoke(variantPoint,value);
        return variantPoint;
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
