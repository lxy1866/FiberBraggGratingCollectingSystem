package top.kaluna.modbusTcp.job;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rabbitmq.client.AMQP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.config.TxtFileConfig;
import top.kaluna.modbusTcp.config.TxtPositionFileConfig;
import top.kaluna.modbusTcp.domain.FbgValue;
import top.kaluna.modbusTcp.domain.FbgValueInfo;
import top.kaluna.modbusTcp.mapper.FbgValueMapper;
import top.kaluna.modbusTcp.service.*;
import top.kaluna.modbusTcp.util.DateUtil;
import top.kaluna.modbusTcp.util.RandomUtil;
import top.kaluna.modbusTcp.util.SnowFlake;
import org.springframework.transaction.annotation.Transactional;
import top.kaluna.modbusTcp.util.TxtResolver;
import top.kaluna.modbusTcp.websocket.WebSocketVibrationWaveServer;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
    private WsService wsService;
    @Resource
    private FbgValueMapper fbgValueMapper;
    @Resource
    private BreakpointRecordService breakpointRecordService;

    @Resource
    private TxtFileConfig txtFileConfig;

    @Resource
    private TxtPositionFileConfig txtPositionFileConfig;
    @Resource
    private FbgValueInfoService fbgValueInfoService;
    @Resource
    private TxtValueService txtValueService;
    @Resource
    private FbgValueService fbgValueService;
    @Resource
    private PositionValueService positionValueService;
    @Resource
    private WebSocketVibrationWaveServer webSocketVibrationWaveServer;

    /**
     *
     * 每30分钟更新断点记录表
     * 从0秒开始,每30分钟执行一次
     * 从physical_value表查询前半个小时tag！=0的记录到breakpoint_record记录表中
     */
//    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(cron = "0 0/30 * * * ?" )
//    //@Scheduled(cron = "0/5 * * * * ? ")
//    public void cron(){
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("更新断点记录表开始");
//        long start = System.currentTimeMillis();
//        breakpointRecordService.insertBreakpointInfoByScanFbgValue();
//        LOG.info("更新断点记录表结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
//    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(cron = "0/10 * * * * ? " )
//    public void cronSendFbgValues(){
//        long datetime = new Date().getTime();
//        List<FbgValue> temperatureFbgValues = new ArrayList<>();
//        List<FbgValue> vibrationFbgValues = new ArrayList<>();
//        List<FbgValue> strainFbgValues = new ArrayList<>();
//        temperatureFbgValues.add(new FbgValue((long) 1, RandomUtil.From1TO10(),  1, datetime,1));
//        temperatureFbgValues.add(new FbgValue((long) 2, RandomUtil.From1TO10(),  1, datetime,2));
//        temperatureFbgValues.add(new FbgValue((long) 3, RandomUtil.From1TO10(),  1, datetime,6));
//
//        // id physicalValueId 物理值 断裂位置 创建时间 通道号
//        vibrationFbgValues.add(new FbgValue((long) 4, RandomUtil.From100TO1000(),  1, datetime,4));
//
//        for(int i = 0; i < 6; i++){
//            strainFbgValues.add(new FbgValue((long) i+5, RandomUtil.From100TO1000(), i+1, datetime,3));
//        }
//        for(int i = 0; i < 7; i++){
//            strainFbgValues.add(new FbgValue((long) i+11, RandomUtil.From100TO1000(), i+1, datetime,5));
//        }
//        List<FbgValue> list  = new ArrayList<>();
//        list.addAll(temperatureFbgValues);
//        list.addAll(vibrationFbgValues);
//        list.addAll(strainFbgValues);
//        String jsonString = JSONObject.toJSONString(list, SerializerFeature.MapSortField);
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        String logId = MDC.get("LOG_ID");
//        LOG.info("推送新消息");
//        wsService.sendInfo(jsonString,logId);
//        fbgValueMapper.multipleInsert(list);
////        String astr = JSONObject.toJSONString(list, SerializerFeature.MapSortField);
////        //System.out.println(astr);
////        //增加日志流水号
////        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
////        String logId = MDC.get("LOG_ID");
////        LOG.info("推送新消息");
////        long start = System.currentTimeMillis();
////        wsService.sendInfo(astr,logId);
////        LOG.info("推送新消息结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
    //定时计算最大最小值并插入min_max_value_for_temperature
//    @Scheduled(cron = "0 59 * * * ?" )
//    public void cronGenerateFakeFbgValue() throws ParseException {
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("计算这个小时的温度最大值和最小值，求出来存储到min_max_value_for_temperature中开始");
//        long start = System.currentTimeMillis();
//        //暂时不这样计算了 最小值和最大值都是固定的
//        fbgValueService.computeAndInsertTheMaxMinTemperatureInThisHour();
//        LOG.info("计算这个小时的温度最大值和最小值，求出来存储到min_max_value_for_temperature中结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
//    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(cron = "0 0/5 * * * *" )
//    //@Scheduled(cron = "0 0 0/1 * * *" )
//    public void cronResolveTxtValue(){
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("定时解析txt文件开始");
//        long start = System.currentTimeMillis();
//        TxtResolver txtResolver = new TxtResolver();
//        String filePath = "";
//        //filePath = txtFileConfig.getPath() + "/" + date.getMonth() + "." + date.getDay() + "/" + "Data_" + date.getYear() + (date.getMonth()+1 <= 9 ? "0"+date.getMonth() : date.getMonth()) + (date.getDay()-1 <= 9 ? "0"+date.getDay() : date.getDay()) + "_000000.txt";
//        filePath = "D:/browserDownload/舟山数据源/9.2/Data_20220902_000000.txt";
//        txtResolver.readTxtFile(filePath);
//        //将这个小时的温度最大值和最小值求出来存储到min_max_value_for_temperature中
//        LOG.info("定时解析txt文件结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
//    @Transactional(rollbackFor = Exception.class)
//    //@Scheduled(cron = "0 0 0/1 * * * " )
//    @Scheduled(cron = "0 0/5 * * * * " )
//    public void cronResolveTxtPositionFile(){
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("定时解析position txt文件开始");
//        long start = System.currentTimeMillis();
//        TxtResolver txtResolver = new TxtResolver();
//        Date date = new Date();
//        String filePath = "";
//        //filePath = txtPositionFileConfig.getPath() + "/" + date.getMonth() + "." + date.getDay() + "/" + "Data_" + date.getYear() + (date.getMonth()+1 <= 9 ? "0"+date.getMonth() : date.getMonth()) + (date.getDay()-1 <= 9 ? "0"+date.getDay() : date.getDay()) + "_000000.txt";
//        filePath = "D:\\browserDownload\\fbgProjectRelevantData\\data20220920\\22-08-29-15-24-12\\22-08-29-15-24-12.txt";
//        txtResolver.readTxtPositionFile(txtPositionFileConfig.getPath());
//        //将这个小时的温度最大值和最小值求出来存储到min_max_value_for_temperature中
//        LOG.info("定时解析position txt文件结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
//    @Scheduled(cron = "0 59 * * * ?" )
//    public void cronGenerateFakeFbgValue() throws ParseException {
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("每小时的59分生成所有传感器的假数据开始");
//        long start = System.currentTimeMillis();
//        TxtResolver txtResolver = new TxtResolver();
//        txtResolver.generateFakeFbgValue(fbgValueInfoService, txtValueService);
//        LOG.info("每小时的59分生成所有传感器的假数据结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//        LOG.info("计算这个小时的温度最大值和最小值，求出来存储到min_max_value_for_temperature中开始");
//        //暂时不这样计算了 最小值和最大值都是固定的
//        //txtResolver.computeAndInsertTheMaxMinTemperatureInThisHour(txtValueService);
//        txtResolver.generateFakeTemperatureValue(txtValueService);
//        LOG.info("计算这个小时的温度最大值和最小值，求出来存储到min_max_value_for_temperature中结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
//    @Scheduled(cron = "0 59 * * * ?" )
//    public void cronGenerateFakeMemsValue() throws ParseException {
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("每小时的59分生成所有传感器的假数据开始");
//        long start = System.currentTimeMillis();
//        TxtResolver txtResolver = new TxtResolver();
//        txtResolver.generateFakeMemsValue(positionValueService);
//        LOG.info("每小时的59分生成所有MEMS的假数据结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
//    @Scheduled(cron = "0/5 * * * * ?" )
//    public void cronGenerateFakeVibrationWave() throws ParseException {
//        //增加日志流水号
//        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
//        LOG.info("每五秒生成500个的振动波长假数据开始");
//        long start = System.currentTimeMillis();
//        List list = new ArrayList();
//        for(int i = 1; i < 500; i++){
//            float v = (float) (Math.random() * (1000 - 500));
//            list.add(v);
//        }
//        String logId =  MDC.get("LOG_ID");
//        wsService.sendVibrationWaveInfo(JSONObject.toJSONString(list, SerializerFeature.MapSortField), logId);
//        LOG.info("每五秒生成500个的振动波长假数据结束，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
}
