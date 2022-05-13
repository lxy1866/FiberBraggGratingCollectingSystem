package top.kaluna.pipelineMonitor.domain;

import java.math.BigDecimal;

/**
 * @author Yuery
 * @date 2022/3/7/0007 - 17:17
 */
public class SingleNormalRange {
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String name;
    private Long distance;
    private int category;
    private int channel;
    private int arrayNum;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getArrayNum() {
        return arrayNum;
    }

    public void setArrayNum(int arrayNum) {
        this.arrayNum = arrayNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SingleNormalRange{");
        sb.append("minValue=").append(minValue);
        sb.append(", maxValue=").append(maxValue);
        sb.append(", name='").append(name).append('\'');
        sb.append(", distance=").append(distance);
        sb.append(", category=").append(category);
        sb.append(", channel=").append(channel);
        sb.append(", arrayNum=").append(arrayNum);
        sb.append('}');
        return sb.toString();
    }
}
