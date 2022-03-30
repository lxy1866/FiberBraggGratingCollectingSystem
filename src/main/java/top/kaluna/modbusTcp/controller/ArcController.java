package top.kaluna.modbusTcp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kaluna.modbusTcp.domain.ArcExample;
import top.kaluna.modbusTcp.domain.FbgValueInfo;
import top.kaluna.modbusTcp.mapper.ArcMapper;
import top.kaluna.modbusTcp.resp.CommonResp;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
