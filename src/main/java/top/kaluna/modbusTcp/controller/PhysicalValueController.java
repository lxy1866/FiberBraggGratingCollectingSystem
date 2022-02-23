package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.domain.PhysicalValue;
import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
import top.kaluna.modbusTcp.resp.CommonResp;
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
    public CommonResp list(){
        CommonResp<List<PhysicalValue>> resp = new CommonResp<>();
        List<PhysicalValue> list = physicalValueService.list();
        resp.setContent(list);
        return resp;
    }


}
