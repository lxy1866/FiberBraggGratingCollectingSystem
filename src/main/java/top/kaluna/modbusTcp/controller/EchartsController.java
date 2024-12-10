package top.kaluna.modbusTcp.controller;

import jxl.read.biff.BiffException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.config.TxtFileConfig;
import top.kaluna.modbusTcp.resp.CommonResp;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static top.kaluna.modbusTcp.util.FileUtil.getLastModified;
import static top.kaluna.modbusTcp.util.ReadXlsxUtil.*;
import static top.kaluna.pipelineMonitor.util.ReadXlsUtil.readxlsDou;
import static top.kaluna.pipelineMonitor.util.ReadXlsUtil.readxlsStr;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private TxtFileConfig txtFileConfig;

    @GetMapping("/centerBottomX1")
    public CommonResp<List<String>> getCenterBottomX1() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified(txtFileConfig.getYcypath() +"twelveoneoperation").toString(),0,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomY1")
    public CommonResp<List<Double>> getCenterBottomY1() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified(txtFileConfig.getYcypath() +"twelveoneoperation").toString(),37,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomX2")
    public CommonResp<List<String>> getCenterBottomX2() throws BiffException, IOException {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> list = readxlsStr(getLastModified(txtFileConfig.getYcypath() +"twelvetwooperation").toString(),0,3);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/centerBottomY2")
    public CommonResp<List<Double>> getCenterBottomY2() throws BiffException, IOException {
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> list = readxlsDou(getLastModified(txtFileConfig.getYcypath() +"twelvetwooperation").toString(),37,3);
        resp.setContent(list);
        return resp;
    }

}

