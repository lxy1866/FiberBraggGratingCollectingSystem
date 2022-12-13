package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.LastNHoursMinAndMaxResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.service.FbgValueService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Yuery
 * @date 2022/3/21/0021 - 19:35
 */
@RestController
@RequestMapping("/fbg")
public class FbgValueController {
    @Resource
    private FbgValueService fbgValueService;

    @GetMapping("/list")
    @ResponseBody
    public CommonResp<PageResp<Map<String, String>>> list(@Valid DateRangeReq req) throws ParseException {
        CommonResp<PageResp<Map<String, String>>> resp = new CommonResp<>();
        PageResp<Map<String, String>> list = fbgValueService.list2(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/abnormalList")
    @ResponseBody
    public CommonResp<PageResp<String>> abnormalList(@Valid DateRangeReq req){
        CommonResp<PageResp<String>> resp = new CommonResp<>();
        PageResp<String> list = fbgValueService.abnormalList(req);
        resp.setContent(list);
        return resp;
    }

    /**
     * 查询此时此刻温度传感器的温度值
     */
    @GetMapping("/temperatureNow")
    public CommonResp<BigDecimal> temperatureNow(){
        CommonResp<BigDecimal> resp = new CommonResp<>();
        BigDecimal temperatureNow = fbgValueService.temperatureNow();
        resp.setContent(temperatureNow);
        return resp;
    }
//    /**
//     * 查询过去24小时的温度最大最小值
//     * 传入时间参数
//     * 要得到每个小时的最小值以及最大值
//     */
//    @GetMapping("/minAndMaxFromLast24Hours")
//    public CommonResp<List<LastNHoursMinAndMaxResp>> minAndMaxFromLast24Hours(){
//        CommonResp<List<LastNHoursMinAndMaxResp>> resp = new CommonResp<>();
//        List<LastNHoursMinAndMaxResp> lists = fbgValueService.minAndMaxFromLast24Hours();
//        resp.setContent(lists);
//        return resp;
//    }

}
