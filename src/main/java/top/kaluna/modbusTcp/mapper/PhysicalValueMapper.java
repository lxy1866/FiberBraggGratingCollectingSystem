package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.PhysicalValue;
import top.kaluna.modbusTcp.domain.PhysicalValueExample;

public interface PhysicalValueMapper {
    long countByExample(PhysicalValueExample example);

    int deleteByExample(PhysicalValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhysicalValue record);

    int insertSelective(PhysicalValue record);

    List<PhysicalValue> selectByExample(PhysicalValueExample example);

    PhysicalValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhysicalValue record, @Param("example") PhysicalValueExample example);

    int updateByExample(@Param("record") PhysicalValue record, @Param("example") PhysicalValueExample example);

    int updateByPrimaryKeySelective(PhysicalValue record);

    int updateByPrimaryKey(PhysicalValue record);
}