package top.kaluna.pipelineMonitor.controller;

import com.alibaba.excel.util.StringUtils;
import jxl.read.biff.BiffException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jxl.Workbook; //java读取excel表使用的类
import jxl.Cell;  //java读取表格里的单元格的类
import jxl.Sheet;
import top.kaluna.pipelineMonitor.resp.CommonResp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @GetMapping("/leftTopX")
    public CommonResp<List<String>> getX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr("D:\\browserDownload\\ycy\\data\\ss.xls",0);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftTopY")
    public CommonResp<List<Double>> getY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou("D:\\browserDownload\\ycy\\data\\ss.xls",1);
        resp.setContent(list);
        return resp;
    }

    public static ArrayList<String> readxlsStr(String filepath, int index) throws IOException, BiffException {
        ArrayList<String> colList = new ArrayList<>();
        String value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        for (int i = 2;i < rows;i++){
            Cell cell = sheet.getCell(index,i);
            value = cell.getContents();
            colList.add(value);
        }
        return colList;
    }

    public static ArrayList<Double> readxlsDou(String filepath, int index) throws IOException, BiffException {
        ArrayList<Double> colList = new ArrayList<>();
        Double value;
        File file = new File(filepath);
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        Workbook workbook = Workbook.getWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        for (int i = 2;i < rows;i++){
            Cell cell = sheet.getCell(index,i);

            if(cell.getContents().contains("﹢")){
                value = Double.valueOf(cell.getContents().replace("﹢",""));
            }else{
                if(StringUtils.isEmpty(cell.getContents())){
                    break;
                }
                value = Double.valueOf(cell.getContents());
            }
            colList.add(value);
        }
        return colList;
    }
}

