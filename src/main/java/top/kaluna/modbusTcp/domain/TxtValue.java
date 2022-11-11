package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TxtValue {
    private Integer id;

    private String propertyName;

    private Date createTime;

    private Integer channel;

    private Integer arrayNum;

    private BigDecimal value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getArrayNum() {
        return arrayNum;
    }

    public void setArrayNum(Integer arrayNum) {
        this.arrayNum = arrayNum;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", propertyName=").append(propertyName);
        sb.append(", createTime=").append(createTime);
        sb.append(", channel=").append(channel);
        sb.append(", arrayNum=").append(arrayNum);
        sb.append(", value=").append(value);
        sb.append("]");
        return sb.toString();
    }
}