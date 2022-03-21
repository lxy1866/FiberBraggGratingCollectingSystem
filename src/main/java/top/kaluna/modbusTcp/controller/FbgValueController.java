//package top.kaluna.modbusTcp.controller;
//
//import org.springframework.web.bind.annotation.*;
//import top.kaluna.modbusTcp.req.DateRangeReq;
//import top.kaluna.modbusTcp.resp.CommonResp;
//import top.kaluna.modbusTcp.resp.PageResp;
//import top.kaluna.modbusTcp.resp.FbgValueQueryResp;
//import top.kaluna.modbusTcp.service.FbgValueService;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//
///**
// * @author Yuery
// * @date 2022/3/21/0021 - 19:35
// */
//@RestController
//@RequestMapping("/fbg")
//public class FbgValueController {
//    @Resource
//    private FbgValueService fbgValueService;
//
//    @GetMapping("/list")
//    @ResponseBody
//    public CommonResp<PageResp<FbgValueQueryResp>> list(@Valid DateRangeReq req){
//        CommonResp<PageResp<FbgValueQueryResp>> resp = new CommonResp<>();
//        PageResp<FbgValueQueryResp> list = fbgValueService.list(req);
//        resp.setContent(list);
//        return resp;
//    }
//    @PostMapping("/save")
//    public CommonResp save(@Valid @RequestBody FbgValueSaveReq ebookQueryReq){
//        CommonResp resp = new CommonResp<>();
//        fbgValueService.save(ebookQueryReq);
//        return resp;
//    }
//    @DeleteMapping("/delete/{id}")
//    public CommonResp delete(@PathVariable Long id){
//        CommonResp resp = new CommonResp<>();
//        fbgValueService.delete(id);
//        return resp;
//    }
//    @GetMapping("/abnormalList")
//    @ResponseBody
//    public CommonResp<PageResp<FbgValueQueryResp>> abnormalList(@Valid DateRangeReq req){
//        CommonResp<PageResp<FbgValueQueryResp>> resp = new CommonResp<>();
//        PageResp<FbgValueQueryResp> list = fbgValueService.abnormalList(req);
//        resp.setContent(list);
//        return resp;
//    }
//}