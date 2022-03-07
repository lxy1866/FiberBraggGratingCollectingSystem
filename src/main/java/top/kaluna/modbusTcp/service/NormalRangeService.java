package top.kaluna.modbusTcp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.NormalRange;
import top.kaluna.modbusTcp.domain.NormalRangeExample;
import top.kaluna.modbusTcp.domain.SingleNormalRange;
import top.kaluna.modbusTcp.mapper.NormalRangeMapper;
import top.kaluna.modbusTcp.req.NormalRangeSaveReq;
import top.kaluna.modbusTcp.resp.NormalRangeQueryResp;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/6/0006 - 23:48
 */
@Service
public class NormalRangeService {
    @Resource
    private NormalRangeMapper normalRangeMapper;

    public NormalRangeQueryResp findMaxId() {
        return normalRangeMapper.findMaxId();
    }

    public int save(String reqStr) {
        List<SingleNormalRange> lists = JSONObject.parseArray(reqStr, SingleNormalRange.class);

        NormalRange normalRange = new NormalRange();
        int index = 1;
        for (SingleNormalRange list : lists) {
            normalRange.setValue(index, list.getMinValue(), list.getMaxValue());
            index++;
        }
        normalRange.setCreateTime(DateUtil.getNowTime().getTime());
        return normalRangeMapper.insert(normalRange);
    }
}
