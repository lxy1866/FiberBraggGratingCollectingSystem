package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.BreakpointRecordQueryReq;
import top.kaluna.modbusTcp.resp.BreakpointRecordQueryResp;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.service.BreakpointRecordFinishService;
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
    @Resource
    private BreakpointRecordFinishService breakpointRecordFinishService;

    @GetMapping("/list")
    @ResponseBody
    public CommonResp<PageResp<BreakpointRecordQueryResp>> list(@Valid BreakpointRecordQueryReq req){
        CommonResp<PageResp<BreakpointRecordQueryResp>> resp = new CommonResp<>();
        PageResp<BreakpointRecordQueryResp> list = breakpointRecordService.list(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/update/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        CommonResp<Object> resp = new CommonResp<>();
        breakpointRecordService.update(id);
        return resp;
    }
    @GetMapping("/finishList")
    @ResponseBody
    public CommonResp<PageResp<BreakpointRecordQueryResp>> finishList(@Valid BreakpointRecordQueryReq req){
        CommonResp<PageResp<BreakpointRecordQueryResp>> resp = new CommonResp<>();
        PageResp<BreakpointRecordQueryResp> list = breakpointRecordFinishService.list(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/calculateOnLineRate")
    public CommonResp<Integer> calculateOnLine(){
        CommonResp<Integer> resp = new CommonResp<>();
         int tagForOnLineRate = breakpointRecordService.calculateOnLine();
         resp.setContent(tagForOnLineRate);
         return resp;
    }
}

