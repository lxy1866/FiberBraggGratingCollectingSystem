package top.kaluna.modbusTcp.req;

import java.util.Date;

/**
 * @author Yuery
 * @date 2022/2/24/0024 - 16:10
 */
public class DateRangeReq extends PageReq{

    public Long startTime;

    public Long endTime;


    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
