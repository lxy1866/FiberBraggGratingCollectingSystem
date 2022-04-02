package top.kaluna.modbusTcp.job;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.domain.FbgValue;
import top.kaluna.modbusTcp.service.BreakpointRecordService;
import top.kaluna.modbusTcp.service.WsService;
import top.kaluna.modbusTcp.util.DateUtil;
import top.kaluna.modbusTcp.util.RandomUtil;
import top.kaluna.modbusTcp.util.SnowFlake;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
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
    private BreakpointRecordService breakpointRecordService;
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
}
