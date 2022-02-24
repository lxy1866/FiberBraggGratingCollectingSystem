package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.domain.PhysicalValue;
import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
import top.kaluna.modbusTcp.req.PhysicalValueReq;
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

    @RequestMapping("/list")
    @ResponseBody
    public CommonResp list(PhysicalValueReq req){
        CommonResp<List<PhysicalValueQueryResp>> resp = new CommonResp<>();
        List<PhysicalValueQueryResp> list = physicalValueService.list(req);
        resp.setContent(list);
        return resp;
    }


}
