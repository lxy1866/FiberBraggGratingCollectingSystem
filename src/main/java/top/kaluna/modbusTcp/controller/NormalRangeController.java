package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.NormalRangeSaveReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.NormalRangeQueryResp;
import top.kaluna.modbusTcp.service.NormalRangeService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author Yuery
 * @date 2022/3/6/0006 - 23:46
 */
@RestController
@RequestMapping("/nr")
public class NormalRangeController {
    @Resource
    private NormalRangeService normalRangeSaveService;
    @PostMapping("/save")
    public CommonResp save(@RequestBody String req){
        CommonResp resp = new CommonResp<>();
        int save = normalRangeSaveService.save(req);
        resp.setSuccess(save == 1);
        return resp;
    }
    @GetMapping("findMaxId")
    @ResponseBody
    public CommonResp<NormalRangeQueryResp> findMaxid(){
        CommonResp<NormalRangeQueryResp> resp = new CommonResp<>();
        NormalRangeQueryResp maxIdRecord = normalRangeSaveService.findMaxId();
        resp.setContent(maxIdRecord);
        return resp;
    }
}
