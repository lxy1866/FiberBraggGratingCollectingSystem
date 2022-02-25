package top.kaluna.modbusTcp.controller;

import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.DateRangeReq;

import top.kaluna.modbusTcp.req.PageReq;
import top.kaluna.modbusTcp.req.PhysicalValueSaveReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.PageResp;
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
    public CommonResp<PageResp<PhysicalValueQueryResp>> list(DateRangeReq req){
        CommonResp<PageResp<PhysicalValueQueryResp>> resp = new CommonResp<>();
        PageResp<PhysicalValueQueryResp> list = physicalValueService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp save(@RequestBody PhysicalValueSaveReq ebookQueryReq){
        CommonResp resp = new CommonResp<>();
        physicalValueService.save(ebookQueryReq);
        return resp;
    }
}
