package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.domain.FbgValueInfo;
import top.kaluna.modbusTcp.req.FbgValueInfoReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.service.FbgValueInfoService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/6/0006 - 23:46
 */
@RestController
@RequestMapping("/nr")
public class NormalRangeController {
    @Resource
    private FbgValueInfoService fbgValueInfoService;
    @PostMapping("/save")
    public CommonResp save(@RequestBody String req){
        CommonResp resp = new CommonResp<>();
        int save = fbgValueInfoService.save(req);
        resp.setSuccess(save != 0);
        return resp;
    }
    @GetMapping("/total")
    public CommonResp getTotal(){
        CommonResp resp = new CommonResp();
        int total = fbgValueInfoService.getTotal();
        resp.setContent(total);
        return resp;
    }
    @GetMapping("/vibrationDistance")
    public CommonResp<List<FbgValueInfo>> getVibrationDistance(){
        CommonResp<List<FbgValueInfo>> resp = new CommonResp<>();
        final List<FbgValueInfo> vibrationDistance = fbgValueInfoService.getFbgValueInfo(3);
        resp.setContent(vibrationDistance);
        return resp;
    }
    @GetMapping("/strainDistance")
    public CommonResp<List<FbgValueInfo>> getStrainDistance(){
        CommonResp<List<FbgValueInfo>> resp = new CommonResp<>();
        final List<FbgValueInfo> vibrationDistance = fbgValueInfoService.getFbgValueInfo(1);
        resp.setContent(vibrationDistance);
        return resp;
    }

    @GetMapping("/temperature")
    public CommonResp<List<FbgValueInfo>> getTemperature(){
        CommonResp<List<FbgValueInfo>> resp = new CommonResp<>();
        final List<FbgValueInfo> temperature = fbgValueInfoService.getFbgValueInfo(2);
        resp.setContent(temperature);
        return resp;
    }
    @DeleteMapping("/delete/{propertyName}")
    public CommonResp delete(@PathVariable String propertyName){
        CommonResp resp = new CommonResp<>();
        final int delete = fbgValueInfoService.delete(propertyName);
        if(delete != 0){
            resp.setSuccess(true);
            resp.setMessage("删除成功");
        }else {
            resp.setSuccess(false);
            resp.setMessage("删除失败");
        }
        return resp;
    }
    @PostMapping("/update")
    public CommonResp save(@Valid @RequestBody FbgValueInfoReq fbgValueInfoReq){
        CommonResp resp = new CommonResp<>();
        final int update = fbgValueInfoService.update(fbgValueInfoReq);
        if(update != 0){
            resp.setSuccess(true);
            resp.setMessage("修改成功");
        }else {
            resp.setSuccess(false);
            resp.setMessage("修改失败");
        }
        return resp;
    }
    @GetMapping("/getAll")
    public CommonResp<List<FbgValueInfo>> save(){
        CommonResp<List<FbgValueInfo>> resp = new CommonResp<>();
        final List<FbgValueInfo> list = fbgValueInfoService.getAll();
        if(list.size() != 0){
            resp.setSuccess(true);
            resp.setContent(list);
        }else {
            resp.setSuccess(false);
            resp.setContent(null);
        }
        return resp;
    }
}
