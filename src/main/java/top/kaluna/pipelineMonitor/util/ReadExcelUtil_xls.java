package top.kaluna.pipelineMonitor.util;

import top.kaluna.pipelineMonitor.domain.VariantPoint;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.alibaba.excel.util.StringUtils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;

public class ReadExcelUtil_xls {

    public static ArrayList<VariantPoint> readExcel(String filepath, int startrow, int val1, int val2) throws IOException, BiffException, IntrospectionException, InvocationTargetException, IllegalAccessException, ParseException {
        ArrayList<VariantPoint> result = new ArrayList<>();
        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = Workbook.getWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        Double value;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        for(int i = startrow;i < rows;i++) {
            VariantPoint variantPoint = new VariantPoint();
            Cell cell1 = sheet.getCell(val1, i);
            Cell cell2 = sheet.getCell(val2, i);
            String temp = cell1.getContents();
            ArrayList<Integer> list = searchAllIndex(temp,"/");
            String date;
            if(StringUtils.isEmpty(temp)){
                break;
            }else if(!temp.contains("-")){
                if(temp.length()<15){
                    if(temp.startsWith("2022")){
                        date = temp + ":00";
                        date = date.replace("/","-");
                    }else{
                        date = "20"+temp.substring(list.get(1)+1,list.get(1)+3)+"-"+temp.substring(0,list.get(0))+"-"+temp.substring(list.get(0)+1,list.get(1))+" "+temp.substring(list.get(1)+4)+":00";
                    }
                }else{
                    date = temp.replace("/","-");
                }
            }else{
                date = temp;
            }
            if(searchAllIndex(date,":").size()==1){
                date = date + ":00";
            }
            if(searchAllIndex(date,"-").size()==3){
                StringBuilder sb = new StringBuilder(date);
                sb.replace(9,10," ");
                date = sb.toString();
            }
            Date date_parse = format.parse(date);
//            Long timestamp = date_parse.getTime();

//            if(StringUtils.isEmpty(temp)){
//                break;
//            }else if(temp.substring(0, 3).contains("/")){
//                date = "20" + temp.substring(4, 6) + "/" + temp.substring(0, 1) + "/" + temp.substring(2, 3) + " " + temp.substring(7) + ":00";
//            }else{
//                date = temp;
//            }

            if (cell2.getContents().contains("﹢")) {
                value = Double.valueOf(cell2.getContents().replace("﹢", ""));
            } else {
                if (StringUtils.isEmpty(cell2.getContents()) || isContainChinese(cell2.getContents())) {
                    value = 0.0;
                } else {
                    value = Double.valueOf(cell2.getContents());
                }
            }
            variantPoint = setValue(variantPoint, "date", "value", date_parse, value);
            result.add(variantPoint);
        }
        return result;
    }

    private static VariantPoint setValue(VariantPoint variantPoint, String dateName, String valueName, Date date, Double value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
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

    public static ArrayList<Integer> searchAllIndex(String str, String target) {
        ArrayList<Integer> result = new ArrayList<>();
        int a = str.indexOf(target);//*第一个出现的索引位置
        while (a != -1) {
            result.add(a);
            a = str.indexOf(target, a + 1);//*从这个索引往后开始第一个出现的位置
        }
        return result;
    }
}
