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
import static top.kaluna.pipelineMonitor.util.ReadXlsxUtil.readxlsxDou;
import static top.kaluna.pipelineMonitor.util.ReadXlsxUtil.readxlsxStr;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @GetMapping("/leftTopX")
    public CommonResp<List<String>> getleftTopX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr("F:\\数据\\6月18日至7月27日-12%变形点施工期间管道位移监测数据-显示B列.xls",0,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftTopY")
    public CommonResp<List<Double>> getleftTopY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou("F:\\数据\\6月18日至7月27日-12%变形点施工期间管道位移监测数据-显示B列.xls",1,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftBottomX")
    public CommonResp<List<String>> getleftBottomX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr("F:\\数据\\7月13日至7月27日-22%变形点施工期间管道位移监测数据-显示B列.xls",0,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/leftBottomY")
    public CommonResp<List<Double>> getleftBottomY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou("F:\\数据\\7月13日至7月27日-22%变形点施工期间管道位移监测数据-显示B列.xls",1,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomBottomX")
    public CommonResp<List<String>> getCenterBottomBottomX() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr("F:\\数据\\2022.9.26-22%变形点运行期间管道位移原始数据-显示AP列-后期值不更新.xls",0,2);
        System.out.println(list);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomBottomY")
    public CommonResp<List<Double>> getCenterBottomBottomY() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou("F:\\数据\\2022.9.26-22%变形点运行期间管道位移原始数据-显示AP列-后期值不更新.xls",41,2);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomX1")
    public CommonResp<List<String>> getCenterBottomX1() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr("F:\\数据\\2022.9.26-12%变形点1#运行期间管道位移原始数据(2).xls",0,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomY1")
    public CommonResp<List<Double>> getCenterBottomY1() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou("F:\\数据\\2022.9.26-12%变形点1#运行期间管道位移原始数据(2).xls",37,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomX2")
    public CommonResp<List<String>> getCenterBottomX2() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr("F:\\数据\\2022.9.26-12%变形点2#运行期间传感阵列原始数据(1).xls",0,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomY2")
    public CommonResp<List<Double>> getCenterBottomY2() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou("F:\\数据\\2022.9.26-12%变形点2#运行期间传感阵列原始数据(1).xls",37,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/rightBottom")
    public CommonResp<List<Double>> getRightBottomData() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        ArrayList<Double> list = new ArrayList<>();
        double valueOfPipeClamp = readLastValue("F:\\数据\\2022.9.26-22%变形点运行期间管道位移原始数据-显示AP列-后期值不更新.xls",41);
        double valueOfLocatingRing = readLastValue("F:\\数据\\2022.9.26-22%变形点运行期间管道位移原始数据-显示AP列-后期值不更新.xls",1);
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
        double valueOfPipeClamp = readLastValue("F:\\数据\\2022.9.26-12%变形点2#运行期间传感阵列原始数据(1).xls",37);
        double valueOfLocatingRing = readLastValue("F:\\数据\\2022.9.26-12%变形点1#运行期间管道位移原始数据(2).xls",37);
        list.add(valueOfPipeClamp);
        list.add(valueOfLocatingRing);
        System.out.println(list);
        resp.setContent(list);
        return resp;
    }

}

