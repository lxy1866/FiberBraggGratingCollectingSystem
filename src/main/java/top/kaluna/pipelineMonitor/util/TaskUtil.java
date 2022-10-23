package top.kaluna.pipelineMonitor.util;

import jxl.read.biff.BiffException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaluna.pipelineMonitor.domain.VariantPoint;
import top.kaluna.pipelineMonitor.mapper.VariantPointMapper;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;

import static top.kaluna.pipelineMonitor.util.FileUtil.getLastModified;
import static top.kaluna.pipelineMonitor.util.ReadExcelUtil.*;
import static top.kaluna.pipelineMonitor.util.ReadExcelUtil_xls.readExcel;

@Component
public class TaskUtil {

    @Scheduled(cron = "0 0 10 L * ?")
    public void ReadExcelToDB() throws IOException, IntrospectionException, InvocationTargetException, IllegalAccessException, BiffException, ParseException {
        String resouce = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resouce);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = sqlSessionFactory.openSession(true);
        VariantPointMapper variantPointMapper = session.getMapper(VariantPointMapper.class);

        ArrayList<VariantPoint> list1 = new ArrayList<>();
        list1 = readExcel(getLastModified("/root/ycyData/twelveconstruction").toString(),2,0,1);
        if(variantPointMapper.existTable("twelve_construction")==1){
            variantPointMapper.dropTable("twelve_construction");
            variantPointMapper.createTwelveConstructionTable();
            variantPointMapper.twelveInConstructionBatchInsert(list1);
        }else{
            variantPointMapper.createTwelveConstructionTable();
            variantPointMapper.twelveInConstructionBatchInsert(list1);
        }

        ArrayList<VariantPoint> list2 = new ArrayList<>();
        list2 = readExcel(getLastModified("/root/ycyData/twentytwoconstruction").toString(),2,0,1);
        if(variantPointMapper.existTable("twentytwo_construction")==1){
            variantPointMapper.dropTable("twentytwo_construction");
            variantPointMapper.createTwentytwoConstructionTable();
            variantPointMapper.twentytwoInConstructionBatchInsert(list2);
        }else{
            variantPointMapper.createTwentytwoConstructionTable();
            variantPointMapper.twentytwoInConstructionBatchInsert(list2);
        }

        ArrayList<VariantPoint> list3 = new ArrayList<>();
        list3 = readExcel(getLastModified("/root/ycyData/twelveoneoperation").toString(),3,0,37);
        if(variantPointMapper.existTable("twelve_one_operation")==1){
            variantPointMapper.dropTable("twelve_one_operation");
            variantPointMapper.createTwelveOneOperationTable();
            variantPointMapper.twelveOneInOperationBatchInsert(list3);
        }else{
            variantPointMapper.createTwelveOneOperationTable();
            variantPointMapper.twelveOneInOperationBatchInsert(list3);
        }

        ArrayList<VariantPoint> list4 = new ArrayList<>();
        list4 = readExcel(getLastModified("/root/ycyData/twelvetwooperation").toString(),3,0,37);
        if(variantPointMapper.existTable("twelve_two_operation")==1){
            variantPointMapper.dropTable("twelve_two_operation");
            variantPointMapper.createTwelveTwoOperationTable();
            variantPointMapper.twelveTwoInOperationBatchInsert(list4);
        }else{
            variantPointMapper.createTwelveTwoOperationTable();
            variantPointMapper.twelveTwoInOperationBatchInsert(list4);
        }

        ArrayList<VariantPoint> list5 = new ArrayList<>();
        list5 = readExcel(getLastModified("/root/ycyData/twentytwooperation").toString(),2,0,41);
        if(variantPointMapper.existTable("twentytwo_operation")==1){
            variantPointMapper.dropTable("twentytwo_operation");
            variantPointMapper.createTwentytwoOperationTable();
            variantPointMapper.twentytwoInOperationBatchInsert(list5);
        }else{
            variantPointMapper.createTwentytwoOperationTable();
            variantPointMapper.twentytwoInOperationBatchInsert(list5);
        }

        if(variantPointMapper.existTable("allpoint_value")==1 && variantPointMapper.existTable("temptb")==1){
            variantPointMapper.dropTable("allpoint_value");
            variantPointMapper.dropTable("temptb");
            variantPointMapper.createTempTable("temptb");
            variantPointMapper.createJoinTable("allpoint_value");
        }else if(variantPointMapper.existTable("allpoint_value")==1 && variantPointMapper.existTable("temptb")==0){
            variantPointMapper.dropTable("allpoint_value");
            variantPointMapper.createTempTable("temptb");
            variantPointMapper.createJoinTable("allpoint_value");
        }else if(variantPointMapper.existTable("allpoint_value")==0 && variantPointMapper.existTable("temptb")==1){
            variantPointMapper.dropTable("temptb");
            variantPointMapper.createTempTable("temptb");
            variantPointMapper.createJoinTable("allpoint_value");
        }else{
            variantPointMapper.createTempTable("temptb");
            variantPointMapper.createJoinTable("allpoint_value");
        }

        System.out.println("success");
    }
}
