package top.kaluna.pipelineMonitor.service;

import org.springframework.stereotype.Service;
import top.kaluna.pipelineMonitor.domain.HomePageLeftTop;
import top.kaluna.pipelineMonitor.domain.HomePageLeftTopExample;
import top.kaluna.pipelineMonitor.domain.HomePageMiddleBottom;
import top.kaluna.pipelineMonitor.domain.HomePageMiddleBottomExample;
import top.kaluna.pipelineMonitor.mapper.HomePageLeftTopMapper;
import top.kaluna.pipelineMonitor.mapper.HomePageMiddleBottomMapper;
import top.kaluna.pipelineMonitor.req.LeftTopAttributeReq;
import top.kaluna.pipelineMonitor.req.MiddleBottomAttributeReq;
import top.kaluna.pipelineMonitor.util.CopyUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/18/0018 - 21:34
 */
@Service
public class HomeService {

    @Resource
    private HomePageLeftTopMapper homePageLeftTopMapper;
    @Resource
    private HomePageMiddleBottomMapper homePageMiddleBottomMapper;
    public Boolean leftTopAttributeSave(LeftTopAttributeReq req){

        HomePageLeftTop homePageLeftTop = CopyUtil.copy(req, HomePageLeftTop.class);
        homePageLeftTop.setEachArrayNum(req.getEachArrayNum().toString());

        HomePageLeftTopExample homePageLeftTopExample = new HomePageLeftTopExample();
        HomePageLeftTopExample.Criteria criteria = homePageLeftTopExample.createCriteria();
        criteria.andIdEqualTo(1L);

        final List<HomePageLeftTop> homePageLeftTops = homePageLeftTopMapper.selectByExample(homePageLeftTopExample);

        if(homePageLeftTops.size()!=0){
            homePageLeftTopMapper.updateByExample(homePageLeftTop,homePageLeftTopExample);
        }else{
            homePageLeftTopMapper.insert(homePageLeftTop);
        }
        return true;
    }
    public Boolean middleBottomAttributeSave(MiddleBottomAttributeReq req){

        HomePageMiddleBottom homePageMiddleBottom = CopyUtil.copy(req, HomePageMiddleBottom.class);
        homePageMiddleBottom.setArrayNode(req.getArrayNode().toString());

        HomePageMiddleBottomExample homePageMiddleBottomExample = new HomePageMiddleBottomExample();
        HomePageMiddleBottomExample.Criteria criteria = homePageMiddleBottomExample.createCriteria();
        criteria.andIdEqualTo(1L);
        final List<HomePageMiddleBottom> homePageMiddleBottoms = homePageMiddleBottomMapper.selectByExample(homePageMiddleBottomExample);
        if(homePageMiddleBottoms.size()!=0){
            homePageMiddleBottomMapper.updateByExample(homePageMiddleBottom,homePageMiddleBottomExample);
        }else{
            homePageMiddleBottomMapper.insert(homePageMiddleBottom);
        }
        return true;
    }
}
