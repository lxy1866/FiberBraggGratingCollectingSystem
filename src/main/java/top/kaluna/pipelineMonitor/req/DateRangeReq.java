package top.kaluna.pipelineMonitor.req;


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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DateRangeReq{");
        sb.append("startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append('}');
        return sb.toString();
    }
}
