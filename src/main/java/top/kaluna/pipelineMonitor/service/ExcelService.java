package top.kaluna.pipelineMonitor.service;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.kaluna.pipelineMonitor.domain.ExcelData;
import top.kaluna.pipelineMonitor.mapper.ExcelDataMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/19/0019 - 11:54
 */

public class ExcelService {

    private static final String resource = "config/mybatis-config.xml";

    public ExcelService() throws IOException {

    }

    public void save(List<ExcelData> list) throws IOException {
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            ExcelDataMapper excelDataMapper = sqlSession.getMapper(ExcelDataMapper.class);
            //batchInsert
            excelDataMapper.insertExcelDataList(list);
            sqlSession.commit();
            sqlSession.clearCache();
        }
    }
}
