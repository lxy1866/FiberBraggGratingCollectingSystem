package top.kaluna.pipelineMonitor.controller;

import org.springframework.web.bind.annotation.*;
import top.kaluna.pipelineMonitor.domain.HomePageLeftTop;
import top.kaluna.pipelineMonitor.req.LeftTopAttributeReq;
import top.kaluna.pipelineMonitor.req.MiddleBottomAttributeReq;
import top.kaluna.pipelineMonitor.resp.CommonResp;
import top.kaluna.pipelineMonitor.service.HomeService;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/leftTopAttributeGet")
    public CommonResp<HomePageLeftTop> leftTopAttributeGet(){
        CommonResp<HomePageLeftTop> resp = new CommonResp<>();
        HomePageLeftTop homePageLeftTop = homeService.leftTopAttributeGet();
        resp.setContent(homePageLeftTop);
        return resp;
    }

    @PostMapping("/middleBottomAttributeSave")
    public CommonResp middleBottomSave(@RequestBody MiddleBottomAttributeReq req){
        CommonResp resp = new CommonResp<>();
        Boolean tag = homeService.middleBottomAttributeSave(req);
        resp.setSuccess(tag);
        return resp;
    }

    @GetMapping("/leftTopDataGet")
    @ResponseBody
    public CommonResp<List<Object>> leftTopDataGet(){
        CommonResp<List<Object>> resp = new CommonResp<>();
        List<Object> list = homeService.leftTopDataGet();
        if(list != null){
            resp.setContent(list);
        }else {
            resp.setSuccess(false);
            resp.setMessage("后端数据污染，请联系管理员");
            resp.setContent(null);
        }
        return resp;
    }
    @GetMapping("/leftTopTempDataGet")
    @ResponseBody
    public CommonResp<List<Object>> leftTopTempDataGet(){
        CommonResp<List<Object>> resp = new CommonResp<>();
        List<Object> list = homeService.leftTopTempDataGet();
        if(list != null){
            resp.setContent(list);
        }else {
            resp.setSuccess(false);
            resp.setMessage("后端数据污染，请联系管理员");
            resp.setContent(null);
        }
        return resp;
    }

    @GetMapping("/middleBottomGet")
    @ResponseBody
    public CommonResp<List<List<Double>>> middleBottomGet(){
        CommonResp<List<List<Double>>> resp = new CommonResp<>();
        List<List<Double>> avgList = homeService.middleBottomGet();
        resp.setContent(avgList);
        return resp;
    }
    @GetMapping("/rightTopGetForLatestData")
    @ResponseBody
    public CommonResp<List<Double>> rightTopGetForLatestData(){
        CommonResp<List<Double>> resp = new CommonResp<>();
        List<Double> avgList = homeService.rightTopGetForLatestData();
        resp.setContent(avgList);
        return resp;
    }
}
