package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.domain.MinMaxValueForTemperature;
import top.kaluna.modbusTcp.domain.PositionValue;
import top.kaluna.modbusTcp.domain.TxtValue;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.LastNHoursMinAndMaxResp;
import top.kaluna.modbusTcp.resp.PageResp;

import top.kaluna.modbusTcp.service.PositionValueService;
import top.kaluna.modbusTcp.service.TxtValueService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/txt")
public class TxtValueController {
    @Resource
    private TxtValueService txtValueService;

    @Resource
    private PositionValueService positionValueService;
    @GetMapping("/list")
    @ResponseBody
    public CommonResp<PageResp<Map<String, String>>> list(@Valid DateRangeReq req) throws ParseException {
        CommonResp<PageResp<Map<String, String>>> resp = new CommonResp<>();
        PageResp<Map<String, String>> list = txtValueService.list(req);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/listLastHour")
    @ResponseBody
    public CommonResp<List<List<Object>>> listLastHour(@Valid DateRangeReq req) throws ParseException {
        CommonResp<List<List<Object>>> resp = new CommonResp<>();
        List<List<Object>> list = txtValueService.listLastHour(req, req.getCategory());
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/listLastHourForVibration")
    @ResponseBody
    public CommonResp<List<Map<String, Object>>> listLastHourForVibration(@Valid DateRangeReq req) throws ParseException {
        CommonResp<List<Map<String, Object>>> resp = new CommonResp<>();
        List<Map<String, Object>> list = txtValueService.listLastHourForVibration(req, req.getCategory());
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/getLastHourAtTheLastSecondForTemperature")
    @ResponseBody
    public CommonResp<Double> getLastHourAtTheLastSecondForTemperature(@Valid DateRangeReq req) throws ParseException {
        CommonResp<Double> resp = new CommonResp<>();
        Double average = txtValueService.getLastHourAtTheLastSecondForTemperature(req);
        resp.setContent(average);
        return resp;
    }
    @GetMapping("/minAndMaxFromLast24Hours")
    public CommonResp<List<MinMaxValueForTemperature>> minAndMaxFromLast24Hours(){
        CommonResp<List<MinMaxValueForTemperature>> resp = new CommonResp<>();
        List<MinMaxValueForTemperature> lists = txtValueService.minAndMaxFromLast24Hours();
        resp.setContent(lists);
        return resp;
    }
    @GetMapping("/minAndMaxFromLast24HoursForTest")
    public CommonResp<List<MinMaxValueForTemperature>> minAndMaxFromLast24HoursForTest(){
        CommonResp<List<MinMaxValueForTemperature>> resp = new CommonResp<>();
        List<MinMaxValueForTemperature> lists = txtValueService.minAndMaxFromLast24HoursForTest();
        resp.setContent(lists);
        return resp;
    }
    @GetMapping("/listPosition")
    @ResponseBody
    public CommonResp<PageResp<PositionValue>> listPosition(@Valid DateRangeReq req) throws ParseException {
        CommonResp<PageResp<PositionValue>> resp = new CommonResp<>();
        PageResp<PositionValue> list = txtValueService.listPosition(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/queryLast15")
    @ResponseBody
    public CommonResp<List<PositionValue>> queryLast15() {
        CommonResp<List<PositionValue>> resp = new CommonResp<>();
        List<PositionValue> list = positionValueService.queryLast15();
        resp.setContent(list);
        return resp;
    }
}
