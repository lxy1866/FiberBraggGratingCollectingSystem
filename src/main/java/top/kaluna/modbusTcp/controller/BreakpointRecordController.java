package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.BreakpointRecordQueryReq;
import top.kaluna.modbusTcp.resp.BreakpointRecordQueryResp;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.service.BreakpointRecordService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 22:14
 */
@RestController
@RequestMapping("/bpr")
public class BreakpointRecordController {
    @Resource
    private BreakpointRecordService breakpointRecordService;

    @GetMapping("/list")
    @ResponseBody
    public CommonResp<PageResp<BreakpointRecordQueryResp>> list(@Valid BreakpointRecordQueryReq req){
        CommonResp<PageResp<BreakpointRecordQueryResp>> resp = new CommonResp<>();
        PageResp<BreakpointRecordQueryResp> list = breakpointRecordService.list(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/update/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        breakpointRecordService.update(id);
        return resp;
    }
}

