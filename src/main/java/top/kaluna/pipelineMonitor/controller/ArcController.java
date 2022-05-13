package top.kaluna.pipelineMonitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.pipelineMonitor.mapper.ArcMapper;
import top.kaluna.pipelineMonitor.resp.CommonResp;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Yuery
 * @date 2022/3/30/0030 - 11:34
 */
@RestController
@RequestMapping("/arc")
public class ArcController {

    @Resource
    private ArcMapper arcMapper;
    @GetMapping("/length")
    public CommonResp<BigDecimal> getLength(){
        CommonResp<BigDecimal> resp = new CommonResp<>();
        final BigDecimal arcLength = arcMapper.arcLength();
        resp.setContent(arcLength);
        return resp;
    }
}
