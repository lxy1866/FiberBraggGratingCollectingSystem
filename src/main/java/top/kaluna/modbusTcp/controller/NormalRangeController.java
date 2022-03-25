package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.domain.FbgValueInfo;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.service.NormalRangeService;

import javax.annotation.Resource;
import java.util.List;

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
        resp.setSuccess(save != 0);
        return resp;
    }
    @GetMapping("/total")
    public CommonResp getTotal(){
        CommonResp resp = new CommonResp();
        int total = normalRangeSaveService.getTotal();
        resp.setContent(total);
        return resp;
    }
    @GetMapping("/vibrationDistance")
    public CommonResp<List<FbgValueInfo>> getVibrationDistance(){
        CommonResp<List<FbgValueInfo>> resp = new CommonResp<>();
        final List<FbgValueInfo> vibrationDistance = normalRangeSaveService.getVibrationDistance();
        resp.setContent(vibrationDistance);
        return resp;
    }
    @GetMapping("/strainDistance")
    public CommonResp<List<FbgValueInfo>> getStrainDistance(){
        CommonResp<List<FbgValueInfo>> resp = new CommonResp<>();
        final List<FbgValueInfo> vibrationDistance = normalRangeSaveService.getStrainDistance();
        resp.setContent(vibrationDistance);
        return resp;
    }
}
