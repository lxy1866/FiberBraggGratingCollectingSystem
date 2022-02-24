package top.kaluna.modbusTcp.req;

import java.util.Date;

/**
 * @author Yuery
 * @date 2022/2/24/0024 - 16:10
 */
public class DateRangeReq {

    public Date startTime;

    public Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
