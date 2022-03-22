package top.kaluna.modbusTcp.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.*;

import top.kaluna.modbusTcp.mapper.FbgValueInfoMapper;
import top.kaluna.modbusTcp.mapper.FbgValueMapper;
import top.kaluna.modbusTcp.mapper.NormalRangeMapper;

import top.kaluna.modbusTcp.resp.NormalRangeQueryResp;
import top.kaluna.modbusTcp.util.CopyUtil;
import top.kaluna.modbusTcp.util.DateUtil;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author Yuery
 * @date 2022/3/6/0006 - 23:48
 */
@Service
public class NormalRangeService {

    @Resource
    private FbgValueInfoMapper fbgValueInfoMapper;

    private NormalRangeMapper normalRangeMapper;
    public NormalRangeQueryResp findMaxId() {
        return normalRangeMapper.findMaxId();
    }

    public int save(String reqStr) {
        List<SingleNormalRange> lists = JSONObject.parseArray(reqStr, SingleNormalRange.class);

        //System.out.println(lists);
        //[SingleNormalRange{minValue=11, maxValue=22, name='val1'}, SingleNormalRange{minValue=22, maxValue=33, name='val2'}]

        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        FbgValueInfoExample.Criteria criteria = fbgValueInfoExample.createCriteria();

        for (SingleNormalRange list : lists) {
            criteria.andPropertyNameEqualTo(list.getName());
            List<FbgValueInfo> fbgValueInfos = fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
            if(fbgValueInfoMapper.selectByExample(fbgValueInfoExample) != null && fbgValueInfos.size() != 0){
                //对于已经存在的传感器，更新对应的min,max,distance
                FbgValueInfo fbgValueInfo = CopyUtil.copy(fbgValueInfos.get(0), FbgValueInfo.class);
                fbgValueInfo.setMin(list.getMinValue());
                fbgValueInfo.setMax(list.getMaxValue());
                fbgValueInfo.setCreateTime(DateUtil.getNowTime().getTime());
                fbgValueInfo.setDistance(list.getDistance());
                fbgValueInfoMapper.updateByExample(fbgValueInfo,fbgValueInfoExample);
            }else{
                //对于不已经存在的传感器，新增一条记录
                FbgValueInfo fbgValueInfo = new FbgValueInfo();
                fbgValueInfo.setPropertyName(list.getName());
                fbgValueInfo.setMin(list.getMinValue());
                fbgValueInfo.setMax(list.getMaxValue());
                fbgValueInfo.setCreateTime(DateUtil.getNowTime().getTime());
                fbgValueInfo.setId(Long.parseLong(String.valueOf(list.getName().charAt(3))));
                fbgValueInfo.setDistance(list.getDistance());
                fbgValueInfoMapper.insert(fbgValueInfo);
            }
        }
        return lists.size();
    }
}
