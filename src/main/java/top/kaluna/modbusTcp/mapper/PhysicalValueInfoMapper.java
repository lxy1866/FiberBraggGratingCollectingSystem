package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.PhysicalValueInfo;
import top.kaluna.modbusTcp.domain.PhysicalValueInfoExample;

public interface PhysicalValueInfoMapper {
    long countByExample(PhysicalValueInfoExample example);

    int deleteByExample(PhysicalValueInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhysicalValueInfo record);

    int insertSelective(PhysicalValueInfo record);

    List<PhysicalValueInfo> selectByExample(PhysicalValueInfoExample example);

    PhysicalValueInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhysicalValueInfo record, @Param("example") PhysicalValueInfoExample example);

    int updateByExample(@Param("record") PhysicalValueInfo record, @Param("example") PhysicalValueInfoExample example);

    int updateByPrimaryKeySelective(PhysicalValueInfo record);

    int updateByPrimaryKey(PhysicalValueInfo record);
}