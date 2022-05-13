package top.kaluna.pipelineMonitor.resp;

/**
 * @author Yuery
 * @date 2022/3/24/0024 - 11:01
 */
public class CenterTopResp {

    private int total;
    private int onLine;
    private int abnormalTimes;
    private int onlineRate;
    private int thisYearAbnormalTimes;

    public int getThisYearAbnormalTimes() {
        return thisYearAbnormalTimes;
    }

    public void setThisYearAbnormalTimes(int thisYearAbnormalTimes) {
        this.thisYearAbnormalTimes = thisYearAbnormalTimes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOnLine() {
        return onLine;
    }

    public void setOnLine(int onLine) {
        this.onLine = onLine;
    }

    public int getAbnormalTimes() {
        return abnormalTimes;
    }

    public void setAbnormalTimes(int abnormalTimes) {
        this.abnormalTimes = abnormalTimes;
    }

    public int getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(int onlineRate) {
        this.onlineRate = onlineRate;
    }

}
