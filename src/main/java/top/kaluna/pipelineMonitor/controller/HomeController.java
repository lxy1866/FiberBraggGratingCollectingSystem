package top.kaluna.pipelineMonitor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.pipelineMonitor.req.LeftTopAttributeReq;
import top.kaluna.pipelineMonitor.req.MiddleBottomAttributeReq;
import top.kaluna.pipelineMonitor.resp.CommonResp;
import top.kaluna.pipelineMonitor.service.HomeService;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/5/18/0018 - 17:00
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;
    @PostMapping("/leftTopAttributeSave")
    public CommonResp leftTopSave(@RequestBody LeftTopAttributeReq req){
        CommonResp resp = new CommonResp<>();
        Boolean tag = homeService.leftTopAttributeSave(req);
        resp.setSuccess(tag);
        return resp;
    }

    @PostMapping("/middleBottomAttributeSave")
    public CommonResp middleBottomSave(@RequestBody MiddleBottomAttributeReq req){
        CommonResp resp = new CommonResp<>();
        Boolean tag = homeService.middleBottomAttributeSave(req);
        resp.setSuccess(tag);
        return resp;
    }
}
