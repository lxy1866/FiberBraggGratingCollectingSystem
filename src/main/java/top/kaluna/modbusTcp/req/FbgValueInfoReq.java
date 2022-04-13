package top.kaluna.modbusTcp.req;

import java.math.BigDecimal;

/**
 * @author Yuery
 * @date 2022/4/8/0008 - 11:32
 */
public class FbgValueInfoReq {
    private Long id;

    private String propertyName;

    private BigDecimal min;

    private BigDecimal max;

    private Long distance;

    private String createTime;

    private Integer category;

    private int channel;

    private int arrayNum;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FbgValueInfoReq{");
        sb.append("id=").append(id);
        sb.append(", propertyName='").append(propertyName).append('\'');
        sb.append(", min=").append(min);
        sb.append(", max=").append(max);
        sb.append(", distance=").append(distance);
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", category=").append(category);
        sb.append(", channel=").append(channel);
        sb.append(", arrayNum=").append(arrayNum);
        sb.append('}');
        return sb.toString();
    }
}
