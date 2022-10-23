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

import static top.kaluna.pipelineMonitor.util.ReadXlsUtil.*;
import static top.kaluna.pipelineMonitor.util.FileUtil.*;
import static top.kaluna.pipelineMonitor.util.ReadXlsxUtil.readxlsxDou;
import static top.kaluna.pipelineMonitor.util.ReadXlsxUtil.readxlsxStr;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @GetMapping("/leftTopX")
    public CommonResp<List<String>> getleftTopX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified("/root/ycyData/twelveconstruction").toString(),0,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftTopY")
    public CommonResp<List<Double>> getleftTopY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified("/root/ycyData/twelveconstruction").toString(),1,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftBottomX")
    public CommonResp<List<String>> getleftBottomX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified("/root/ycyData/twentytwoconstruction").toString(),0,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftBottomY")
    public CommonResp<List<Double>> getleftBottomY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified("/root/ycyData/twentytwoconstruction").toString(),1,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomBottomX")
    public CommonResp<List<String>> getCenterBottomBottomX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified("/root/ycyData/twentytwooperation").toString(),0,2);
        System.out.println(list);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomBottomY")
    public CommonResp<List<Double>> getCenterBottomBottomY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified("/root/ycyData/twentytwooperation").toString(),41,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomX1")
    public CommonResp<List<String>> getCenterBottomX1() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified("/root/ycyData/twelveoneoperation").toString(),0,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomY1")
    public CommonResp<List<Double>> getCenterBottomY1() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified("/root/ycyData/twelveoneoperation").toString(),37,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomX2")
    public CommonResp<List<String>> getCenterBottomX2() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified("/root/ycyData/twelvetwooperation").toString(),0,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomY2")
    public CommonResp<List<Double>> getCenterBottomY2() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified("/root/ycyData/twelvetwooperation").toString(),37,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/rightBottom")
    public CommonResp<List<Double>> getRightBottomData() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        ArrayList<Double> list = new ArrayList<>();
        double valueOfPipeClamp = readLastValue("/root/ycyData/2022.xls",41);
        double valueOfLocatingRing = readLastValue("/root/ycyData/2022.xls",1);
        list.add(valueOfPipeClamp);
        list.add(valueOfLocatingRing);
        System.out.println(list);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/rightTop")
    public CommonResp<List<Double>> getRightTopData() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        ArrayList<Double> list = new ArrayList<>();
        double valueOfPipeClamp = readLastValue("/root/ycyData/20229261.xls",37);
        double valueOfLocatingRing = readLastValue("/root/ycyData/20229262.xls",37);
        list.add(valueOfPipeClamp);
        list.add(valueOfLocatingRing);
        System.out.println(list);
        resp.setContent(list);
        return resp;
    }

}

