package top.kaluna.pipelineMonitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.kaluna.pipelineMonitor.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface VariantPointMapper {

    int twelveInConstructionBatchInsert(@Param("list") ArrayList<VariantPoint> list);

    int twentytwoInConstructionBatchInsert(@Param("list") ArrayList<VariantPoint> list);

    int twelveOneInOperationBatchInsert(@Param("list") ArrayList<VariantPoint> list);

    int twelveTwoInOperationBatchInsert(@Param("list") ArrayList<VariantPoint> list);

    int twentytwoInOperationBatchInsert(@Param("list") ArrayList<VariantPoint> list);

    int truncateTwelveInConstruction();

    int truncateTwentytwoInConstruction();

    int truncateTwelveOneInOperation();

    int truncateTwelveTwoInOperation();

    int truncateTwentytwoInOperation();

    int existTable(String tableName);

    int dropTable(@Param("tableName")String tableName);

    int createTempTable(@Param("tableName")String tableName);

    int createJoinTable(@Param("tableName")String tableName);

    List<QueryResulyRow> selectByExample(QueryResultExample example);

    List<QueryResulyRow> selectByPage(Integer pageNum, Integer pageSize, Date startTime, Date endTime);

    @Select("select count(*) from allpoint_value where date between #{startTime} and #{endTime}")
    Integer selectTotal(Date startTime, Date endTime);

    @Select("select * from allpoint_value where date between #{startTime} and #{endTime}")
    List<QueryResulyRow> selectByTime(Date startTime, Date endTime);

    @Update("CREATE TABLE twelve_construction(`date`datetime,`twelve_construction_value`decimal(20,2))")
    int createTwelveConstructionTable();

    @Update("CREATE TABLE twentytwo_construction(`date`datetime,`twentytwo_construction_value`decimal(20,2))")
    int createTwentytwoConstructionTable();

    @Update("CREATE TABLE twelve_one_operation(`date`datetime,`twelve_one_operation_value`decimal(20,2))")
    int createTwelveOneOperationTable();

    @Update("CREATE TABLE twelve_two_operation(`date`datetime,`twelve_two_operation_value`decimal(20,2))")
    int createTwelveTwoOperationTable();

    @Update("CREATE TABLE twentytwo_operation(`date`datetime,`twentytwo_operation_value`decimal(20,2))")
    int createTwentytwoOperationTable();
}
