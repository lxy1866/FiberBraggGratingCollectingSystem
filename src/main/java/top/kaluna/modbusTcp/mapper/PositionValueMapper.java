package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.PositionValue;
import top.kaluna.modbusTcp.domain.PositionValueExample;

public interface PositionValueMapper {
    long countByExample(PositionValueExample example);

    int deleteByExample(PositionValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PositionValue record);

    int insertSelective(PositionValue record);

    List<PositionValue> selectByExample(PositionValueExample example);

    PositionValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PositionValue record, @Param("example") PositionValueExample example);

    int updateByExample(@Param("record") PositionValue record, @Param("example") PositionValueExample example);

    int updateByPrimaryKeySelective(PositionValue record);

    int updateByPrimaryKey(PositionValue record);

    int multipleInsert(List<PositionValue> saveList);
    List<PositionValue> queryLast15();
}