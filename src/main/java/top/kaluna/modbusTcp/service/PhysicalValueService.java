package top.kaluna.modbusTcp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.PhysicalValue;
import top.kaluna.modbusTcp.mapper.PhysicalValueMapper;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.resp.PhysicalValueQueryResp;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/2/22/0022 - 12:20
 */
@Service
public class PhysicalValueService {
    @Resource
    private PhysicalValueMapper physicalValueMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PhysicalValueService.class);

    public List<PhysicalValue> list(){

        return physicalValueMapper.selectByExample(null);
    }

//    public PageResp<PhysicalValueQueryResp> list(PhysicalValueQueryResp physicalValueQueryResp){
//
//        physicalValueQueryResp.
//        return physicalValueMapper.selectpvList();
//    }
}
