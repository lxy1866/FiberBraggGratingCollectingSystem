package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.NormalRange;
import top.kaluna.modbusTcp.domain.NormalRangeExample;

public interface NormalRangeMapper {
    long countByExample(NormalRangeExample example);

    int deleteByExample(NormalRangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NormalRange record);

    int insertSelective(NormalRange record);

    List<NormalRange> selectByExample(NormalRangeExample example);

    NormalRange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NormalRange record, @Param("example") NormalRangeExample example);

    int updateByExample(@Param("record") NormalRange record, @Param("example") NormalRangeExample example);

    int updateByPrimaryKeySelective(NormalRange record);

    int updateByPrimaryKey(NormalRange record);

}