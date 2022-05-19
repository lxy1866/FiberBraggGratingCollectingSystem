package top.kaluna.pipelineMonitor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.HomePageLeftTop;
import top.kaluna.pipelineMonitor.domain.HomePageLeftTopExample;

public interface HomePageLeftTopMapper {
    long countByExample(HomePageLeftTopExample example);

    int deleteByExample(HomePageLeftTopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HomePageLeftTop record);

    int insertSelective(HomePageLeftTop record);

    List<HomePageLeftTop> selectByExample(HomePageLeftTopExample example);

    HomePageLeftTop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HomePageLeftTop record, @Param("example") HomePageLeftTopExample example);

    int updateByExample(@Param("record") HomePageLeftTop record, @Param("example") HomePageLeftTopExample example);

    int updateByPrimaryKeySelective(HomePageLeftTop record);

    int updateByPrimaryKey(HomePageLeftTop record);
}