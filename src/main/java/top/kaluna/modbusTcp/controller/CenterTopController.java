package top.kaluna.modbusTcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.resp.CenterTopResp;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.service.BreakpointRecordService;
import top.kaluna.modbusTcp.service.FbgValueService;
import top.kaluna.modbusTcp.service.FbgValueInfoService;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/3/24/0024 - 10:27
 */
@RestController
@RequestMapping("/ct")
public class CenterTopController {
    @Resource
    private FbgValueInfoService normalRangeSaveService;
    @Resource
    private BreakpointRecordService breakpointRecordService;
    @Resource
    private FbgValueService fbgValueService;
    @GetMapping("/calculateOnLine")
    @ResponseBody
    public CommonResp<CenterTopResp> calculateOnLine(){
        CommonResp<CenterTopResp> resp = new CommonResp<>();
        CenterTopResp centerTopResp = new CenterTopResp();
        int total = normalRangeSaveService.getTotal();
        int onLine = normalRangeSaveService.getTotal();
        //int onLine = breakpointRecordService.calculateOnLine();
        //int abnormalTimes = fbgValueService.abnormalListTimes();
        //int thisYearAbnormalTimes = fbgValueService.thisYearAbnormalListTimes();
        int onlineRate = onLine * 100 / total ;

        centerTopResp.setTotal(total);
        centerTopResp.setOnLine(onLine);
        //centerTopResp.setAbnormalTimes(abnormalTimes);
        centerTopResp.setOnlineRate(onlineRate);
        //centerTopResp.setThisYearAbnormalTimes(thisYearAbnormalTimes);
        resp.setContent(centerTopResp);
        return resp;
    }
}
