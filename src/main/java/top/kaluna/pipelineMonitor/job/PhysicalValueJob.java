package top.kaluna.pipelineMonitor.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaluna.pipelineMonitor.config.FileConfig;
import top.kaluna.pipelineMonitor.service.AvgService;
import top.kaluna.pipelineMonitor.service.BreakpointRecordService;
import top.kaluna.pipelineMonitor.service.FileUploadAndDownloadService;
import top.kaluna.pipelineMonitor.util.ParseCsvUtil;
import top.kaluna.pipelineMonitor.util.SnowFlake;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/2/28/0028 - 11:11
 */
@Component
public class PhysicalValueJob {
    private final Logger LOG = LoggerFactory.getLogger(PhysicalValueJob.class);
    @Resource
    private SnowFlake snowFlake;
    @Resource
    private BreakpointRecordService breakpointRecordService;
    @Resource
    private FileUploadAndDownloadService fileUploadAndDownloadService;
    @Resource
    private AvgService avgService;
    @Resource
    private FileConfig fileConfig;
    /**
     * 每30分钟更新断点记录表
     * 从0秒开始,每30分钟执行一次
     * 从physical_value表查询前半个小时tag！=0的记录到breakpoint_record记录表中
     */
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0/30 * * * ?" )
    //@Scheduled(cron = "0/5 * * * * ? ")
    public void cron(){
        //增加日志流水号
        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
        LOG.info("更新断点记录表开始");
        long start = System.currentTimeMillis();
        breakpointRecordService.insertBreakpointInfoByScanFbgValue();
        LOG.info("更新断点记录表结束，耗时：{}毫秒",System.currentTimeMillis() - start);
    }
//    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(cron = "0/3 * * * * ? " )
//    public void cronSendFbgValues(){
//        //创建五个FbgValue 初始化val值
//        List<FbgValue> vibrationfbgValues = new ArrayList<>();
//        List<FbgValue> strainfbgValues = new ArrayList<>();
//        for(int i = 0; i < 5; i++){
//            vibrationfbgValues.add(new FbgValue(new Long(Long.toString(i)), new Long(Long.toString(i+22)), RandomUtil.From100TO1000(), (byte) 0, DateUtil.getNowTime().getTime()));
//        }
//        for(int i = 0; i < 20; i++){
//            strainfbgValues.add(new FbgValue(new Long(Long.toString(i)), new Long(Long.toString(i+2)), RandomUtil.From100TO1000(), (byte) 0, DateUtil.getNowTime().getTime()));
//        }
//        List<List<FbgValue>> list  = new ArrayList<>();
//        list.add(vibrationfbgValues);
//        list.add(strainfbgValues);
//        System.out.println(list.toString());
//        String astr = JSONObject.toJSONString(list, SerializerFeature.MapSortField);
//        //System.out.println(astr);
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        String logId = MDC.get("LOG_ID");
//        LOG.info("推送新消息");
//        long start = System.currentTimeMillis();
//        wsService.sendInfo(astr,logId);
//        LOG.info("推送新消息结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }

    /**
     * 每天1点定时拉取昨天的oss文件并解析删除oss的文件和本地的文件 旧的
     *  这里要改成定时一周拉取一次解析一次 因为一条阵列的数据每周一个文件 固定周一1点的时候拉取上周的数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0 1 ? * MON" )
    public void cronForFileDownloadParsingDelete() throws IOException, ParseException {
        //增加日志流水号
        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
        LOG.info("拉取oss文件开始");
        long start1 = System.currentTimeMillis();
        final List<String> completeSaveFilePathList = fileUploadAndDownloadService.fileDownload();
        LOG.info("拉取oss文件结束，耗时：{}毫秒",System.currentTimeMillis() - start1);

        LOG.info("解析文件开始");
        long start2 = System.currentTimeMillis();
        //这里是通过解析excel文件后将数据插入excelData表的
//        completeSaveFilePathList.forEach((completeSaveFilePath)->{
//            try {
//                ParseExcelUtil.simpleRead(completeSaveFilePath);
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        });
        //这里是通过解析txt文件， 文件名判断后修改arraySn
//        completeSaveFilePathList.forEach((completeSaveFilePath)->{
//            try {
//                String[] completeSaveFilePathArray = completeSaveFilePath.split("/");
//                int arraySn = 0;
//                int sensorNums = 0;
//                if(fileConfig.getName2().equals(completeSaveFilePathArray[completeSaveFilePath.length() - 1])){
//                    arraySn = 2;
//                    sensorNums = 10;
//                    CustomParserTxtUtil.simpleRead(completeSaveFilePath,sensorNums, arraySn);
//                    avgService.getAvgAndInsert(sensorNums, arraySn);
//                }else if(fileConfig.getName1().equals(completeSaveFilePathArray[completeSaveFilePath.length() - 1])){
//                    arraySn = 1;
//                    sensorNums = 12;
//                    CustomParserTxtUtil.simpleRead(completeSaveFilePath,sensorNums, arraySn);
//                    //插入
//                    avgService.getAvgAndInsert(sensorNums, arraySn);
//                }
//            } catch (IOException | ParseException exception) {
//                exception.printStackTrace();
//            }
//        });
        //这里是通过解析csv
        completeSaveFilePathList.forEach((completeSaveFilePath)->{
            try {
                String[] completeSaveFilePathArray = completeSaveFilePath.split("/");
                int arraySn = 0;
                int sensorNums = 0;
                if(fileConfig.getName2().equals(completeSaveFilePathArray[completeSaveFilePath.length() - 1])){
                    arraySn = 2;
                    sensorNums = 10;
                    ParseCsvUtil.readCsvByCsvReader(completeSaveFilePath,sensorNums, arraySn);
                    avgService.getAvgAndInsert(sensorNums, arraySn);
                }else if(fileConfig.getName1().equals(completeSaveFilePathArray[completeSaveFilePath.length() - 1])){
                    arraySn = 1;
                    sensorNums = 12;
                    ParseCsvUtil.readCsvByCsvReader(completeSaveFilePath,sensorNums, arraySn);
                    avgService.getAvgAndInsert(sensorNums, arraySn);
                }
            } catch (ParseException | IOException exception) {
                exception.printStackTrace();
            }
        });
        LOG.info("解析文件结束，耗时：{}毫秒",System.currentTimeMillis() - start2);
        LOG.info("删除oss上的文件和本地文件开始");
        long start3 = System.currentTimeMillis();
        fileUploadAndDownloadService.deleteFileFromOss();
        fileUploadAndDownloadService.deleteFileFromLocal();
        LOG.info("删除oss上的文件和本地文件结束，耗时：{}毫秒",System.currentTimeMillis() - start3);
    }
}
