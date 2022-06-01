package top.kaluna.pipelineMonitor.util;

import jxl.Workbook;
import jxl.write.*;
import top.kaluna.pipelineMonitor.config.FileConfig;
import top.kaluna.pipelineMonitor.domain.ExcelData;


import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * @author Yuery
 * @date 2022/5/22/0022 - 10:33
 */
public class Txt2xlsxUtil {

    @Resource
    private static FileConfig fileConfig;


    static String txtFilePath = "D:/desktop/demandAnalysis/data/log2022-5-9-16-43-55.txt";
    static String xlsxFilePath = "D://desktop/demandAnalysis/data/txt2xlsx.xls";

    static String c1Name, c2Name, c3Name, c4Name, c5Name;
    public  static ArrayList<ExcelData> getTxtInfos(Integer arraySn){
        ArrayList<ExcelData> txtFileList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath),"UTF-8"));) {
            String element = null;
            int index = 0;
            while ((element = bufferedReader.readLine())!= null){
                //如果此行为空，则跳过
                if(element.trim().equals("") ){
                    continue;
                }
                String[] value = element.trim().split("\\s");
                //第一行作为每列名称
                if(index == 0){
                    c1Name = value[0];
                    c2Name = value[1];
                    c3Name = value[2];
                    c4Name = value[3];
                    c5Name = value[4];
                    index = 1;
                    continue;
                }

                String dateString = value[4]+" "+value[5];
                final Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA).parse(dateString);

                //从第二行开始读取每行内容，以ExcelData形式存储
                ExcelData txtFile = new ExcelData(arraySn, value[0],Double.valueOf(value[1]),Double.valueOf(value[2]),Double.valueOf(value[3]), date);
                txtFileList.add(txtFile);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return txtFileList;
    }
    public static void TransToExcel(ArrayList<ExcelData> txtFileList){
        WritableWorkbook book = null;
        try{
            //创建一个xlsx文件
            book = Workbook.createWorkbook(new File(xlsxFilePath));
            WritableSheet sheet = book.createSheet("节点xyz值",0);
            Label label1 = new Label(0,0,c1Name);
            Label label2 = new Label(1,0,c2Name);
            Label label3 = new Label(2,0,c3Name);
            Label label4 = new Label(3,0,c4Name);
            Label label5 = new Label(4,0,c5Name);

            //将定义好的列名添加到工作表中
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            for(int i = 0; i <txtFileList.size(); i++){
                ExcelData txtFile  = txtFileList.get(i);
                Label sensorNodeName = new Label(0,i+1,txtFile.getSensorNodeName());
                jxl.write.Number xValue = new jxl.write.Number(1,i+1,txtFile.getXValue());
                jxl.write.Number yValue = new jxl.write.Number(2,i+1,txtFile.getYValue());
                jxl.write.Number zValue = new jxl.write.Number(3,i+1,txtFile.getZValue());

                DateTime dtLabel = new DateTime(4,i+1,txtFile.getDate());

                sheet.addCell(sensorNodeName);
                sheet.addCell(xValue);
                sheet.addCell(yValue);
                sheet.addCell(zValue);
                sheet.addCell(dtLabel);
            }
            book.write();
            book.close();
        } catch (IOException | WriteException exception) {
            exception.printStackTrace();
        }

    }

}
