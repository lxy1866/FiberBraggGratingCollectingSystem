package top.kaluna.modbusTcp.controller;
import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.DateRangeReq;
import top.kaluna.modbusTcp.req.PhysicalValueSaveReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.resp.PhysicalValueQueryResp;
import top.kaluna.modbusTcp.service.PhysicalValueService;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Yuery
 * @date 2021/12/15/0015 - 16:19
 */
@RestController
@RequestMapping("/pv")
public class PhysicalValueController {
    @Resource
    private PhysicalValueService physicalValueService;

    @GetMapping("/abnormalList")
    @ResponseBody
    public CommonResp<PageResp<PhysicalValueQueryResp>> abnormalList(@Valid DateRangeReq req){
        CommonResp<PageResp<PhysicalValueQueryResp>> resp = new CommonResp<>();
        PageResp<PhysicalValueQueryResp> list = physicalValueService.abnormalList(req);
        resp.setContent(list);
        return resp;
    }
}
