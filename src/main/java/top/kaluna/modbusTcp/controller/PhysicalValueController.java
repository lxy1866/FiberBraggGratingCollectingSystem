package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.DateRangeReq;

import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.PhysicalValueQueryResp;
import top.kaluna.modbusTcp.service.PhysicalValueService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2021/12/15/0015 - 16:19
 */
@RestController
@RequestMapping("/pv")
public class PhysicalValueController {
    @Resource
    private PhysicalValueService physicalValueService;

    @GetMapping("/list")
    @ResponseBody
    public CommonResp list(DateRangeReq req){
        CommonResp<List<PhysicalValueQueryResp>> resp = new CommonResp<>();
        List<PhysicalValueQueryResp> list = physicalValueService.list(req);
        resp.setContent(list);
        return resp;
    }
}
