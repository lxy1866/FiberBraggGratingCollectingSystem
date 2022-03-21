package top.kaluna.modbusTcp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.modbusTcp.domain.FbgValueInfo;
import top.kaluna.modbusTcp.domain.FbgValueInfoExample;

public interface FbgValueInfoMapper {
    long countByExample(FbgValueInfoExample example);

    int deleteByExample(FbgValueInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FbgValueInfo record);

    int insertSelective(FbgValueInfo record);

    List<FbgValueInfo> selectByExample(FbgValueInfoExample example);

    FbgValueInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FbgValueInfo record, @Param("example") FbgValueInfoExample example);

    int updateByExample(@Param("record") FbgValueInfo record, @Param("example") FbgValueInfoExample example);

    int updateByPrimaryKeySelective(FbgValueInfo record);

    int updateByPrimaryKey(FbgValueInfo record);
}