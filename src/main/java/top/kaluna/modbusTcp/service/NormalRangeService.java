package top.kaluna.modbusTcp.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.*;

import top.kaluna.modbusTcp.mapper.FbgValueInfoMapper;

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


    public int save(String reqStr) {
        List<SingleNormalRange> lists = JSONObject.parseArray(reqStr, SingleNormalRange.class);

        //System.out.println(lists);
        //[SingleNormalRange{minValue=11, maxValue=22, name='val1'}, SingleNormalRange{minValue=22, maxValue=33, name='val2'}]

        System.out.println("reqStr:"+reqStr);
        for (SingleNormalRange singleNormalRange : lists) {
            FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
            FbgValueInfoExample.Criteria criteria = fbgValueInfoExample.createCriteria();
            criteria.andPropertyNameEqualTo(singleNormalRange.getName());
            List<FbgValueInfo> fbgValueInfos = fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
            if(fbgValueInfoMapper.selectByExample(fbgValueInfoExample) != null && fbgValueInfos.size() != 0){
                //对于已经存在的传感器，更新对应的min,max,distance,kind
                FbgValueInfo fbgValueInfo = CopyUtil.copy(fbgValueInfos.get(0), FbgValueInfo.class);
                fbgValueInfo.setMin(singleNormalRange.getMinValue());
                fbgValueInfo.setMax(singleNormalRange.getMaxValue());
                fbgValueInfo.setCreateTime(DateUtil.getNowTime().getTime());
                fbgValueInfo.setDistance(singleNormalRange.getDistance());
                fbgValueInfo.setCategory(singleNormalRange.getCategory());
                fbgValueInfoMapper.updateByExample(fbgValueInfo,fbgValueInfoExample);
            }else{
                //对于不已经存在的传感器，新增一条记录
                FbgValueInfo fbgValueInfo = new FbgValueInfo();
                fbgValueInfo.setPropertyName(singleNormalRange.getName());
                fbgValueInfo.setMin(singleNormalRange.getMinValue());
                fbgValueInfo.setMax(singleNormalRange.getMaxValue());
                fbgValueInfo.setCreateTime(DateUtil.getNowTime().getTime());
                fbgValueInfo.setId(Long.parseLong(singleNormalRange.getName().substring(3)));
                fbgValueInfo.setDistance(singleNormalRange.getDistance());
                fbgValueInfo.setCategory(singleNormalRange.getCategory());
                fbgValueInfoMapper.insert(fbgValueInfo);
            }
        }
        return lists.size();
    }

    public int  getTotal() {
        return fbgValueInfoMapper.total();
    }

    public List<FbgValueInfo> getVibrationDistance() {
        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        FbgValueInfoExample.Criteria criteria = fbgValueInfoExample.createCriteria();
        criteria.andCategoryEqualTo(3);

        fbgValueInfoExample.setOrderByClause("id ASC");

        return fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
    }

    public List<FbgValueInfo> getStrainDistance() {
        FbgValueInfoExample fbgValueInfoExample = new FbgValueInfoExample();
        FbgValueInfoExample.Criteria criteria = fbgValueInfoExample.createCriteria();
        criteria.andCategoryEqualTo(1);

        fbgValueInfoExample.setOrderByClause("id ASC");

        return fbgValueInfoMapper.selectByExample(fbgValueInfoExample);
    }
}
