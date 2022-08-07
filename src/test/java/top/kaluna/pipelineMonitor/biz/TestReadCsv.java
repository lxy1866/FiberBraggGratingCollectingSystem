package top.kaluna.pipelineMonitor.biz;

import org.junit.jupiter.api.Test;
import top.kaluna.pipelineMonitor.util.ParseCsvUtil;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Yuery
 * @date 2022/6/1/0001 - 16:00
 */
public class TestReadCsv {

    @Test
    public void test() throws ParseException, IOException {
        //需要给这个文件的日期设置单元格格式为自定义yy/MM/d hh:mm:ss 不然无法读取数据， 其次数据库改变的话要给mybatis-config.xml修改数据库名
        ParseCsvUtil.readCsvByCsvReader("X:/SensorArray1.csv", 12,1);
    }
}
