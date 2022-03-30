package top.kaluna.modbusTcp.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.Arc;
import top.kaluna.modbusTcp.domain.ArcExample;

public interface ArcMapper {
    long countByExample(ArcExample example);

    int deleteByExample(ArcExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Arc record);

    int insertSelective(Arc record);

    List<Arc> selectByExample(ArcExample example);

    Arc selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Arc record, @Param("example") ArcExample example);

    int updateByExample(@Param("record") Arc record, @Param("example") ArcExample example);

    int updateByPrimaryKeySelective(Arc record);

    int updateByPrimaryKey(Arc record);

    BigDecimal arcLength();
}