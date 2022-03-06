package top.kaluna.modbusTcp.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.service.BreakpointRecordService;
import top.kaluna.modbusTcp.util.SnowFlake;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

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
        breakpointRecordService.insertBreakpointInfoByScanPhysicalValue();
        LOG.info("更新断点记录表结束，耗时：{}毫秒",System.currentTimeMillis() - start);
    }
}
