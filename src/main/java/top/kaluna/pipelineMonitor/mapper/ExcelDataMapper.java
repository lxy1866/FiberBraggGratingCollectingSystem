package top.kaluna.pipelineMonitor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.pipelineMonitor.domain.ExcelData;
import top.kaluna.pipelineMonitor.domain.ExcelDataExample;

public interface ExcelDataMapper {
    long countByExample(ExcelDataExample example);

    int deleteByExample(ExcelDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExcelData record);

    int insertSelective(ExcelData record);

    List<ExcelData> selectByExample(ExcelDataExample example);

    ExcelData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExcelData record, @Param("example") ExcelDataExample example);

    int updateByExample(@Param("record") ExcelData record, @Param("example") ExcelDataExample example);

    int updateByPrimaryKeySelective(ExcelData record);

    int updateByPrimaryKey(ExcelData record);

    void insertExcelDataList(List<ExcelData> list);
}