package top.kaluna.pipelineMonitor.util;
import com.alibaba.excel.EasyExcel;
import top.kaluna.pipelineMonitor.domain.ExcelData;
import top.kaluna.pipelineMonitor.listener.ExcelDataListener;
import top.kaluna.pipelineMonitor.service.ExcelService;

import java.io.IOException;

public class ParseExcelUtil {
    public static void simpleRead(String filePath) throws IOException {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        //String fileName = FileUtil.getPath() + "demo" + File.separator + "log2022-5-9-16-49-3.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, ExcelData.class, new ExcelDataListener(new ExcelService())).sheet().headRowNumber(1).doRead();
    }
}
