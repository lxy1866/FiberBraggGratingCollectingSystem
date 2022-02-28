package top.kaluna.modbusTcp.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kaluna.modbusTcp.util.SnowFlake;

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
    /**
     * 每30秒更新电子书信息
     *  从5秒开始,每30秒执行一次
     */
    @Scheduled(cron = "5/30 * * * * ?")
    public void cron(){
        //增加日志流水号
        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));
        LOG.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        //docService.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束，耗时：{}毫秒",System.currentTimeMillis() - start);
    }
}
