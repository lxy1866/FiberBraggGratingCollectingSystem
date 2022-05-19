package top.kaluna.pipelineMonitor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.HomePageMiddleBottom;
import top.kaluna.pipelineMonitor.domain.HomePageMiddleBottomExample;

public interface HomePageMiddleBottomMapper {
    long countByExample(HomePageMiddleBottomExample example);

    int deleteByExample(HomePageMiddleBottomExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HomePageMiddleBottom record);

    int insertSelective(HomePageMiddleBottom record);

    List<HomePageMiddleBottom> selectByExample(HomePageMiddleBottomExample example);

    HomePageMiddleBottom selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HomePageMiddleBottom record, @Param("example") HomePageMiddleBottomExample example);

    int updateByExample(@Param("record") HomePageMiddleBottom record, @Param("example") HomePageMiddleBottomExample example);

    int updateByPrimaryKeySelective(HomePageMiddleBottom record);

    int updateByPrimaryKey(HomePageMiddleBottom record);
}