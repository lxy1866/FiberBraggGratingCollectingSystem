package top.kaluna.modbusTcp.req;


/**
 * @author Yuery
 * @date 2022/2/24/0024 - 16:10
 */
public class DateRangeReq extends PageReq{

    public Long startTime;

    public Long endTime;

    public int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

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
        return "DateRangeReq{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", category=" + category +
                '}';
    }
}
