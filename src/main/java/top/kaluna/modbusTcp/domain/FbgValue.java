package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;

public class FbgValue {
    private Integer id;

    private Long physicalValueInfoId;

    private BigDecimal value;

    private Integer arrayNum;

    private Long createTime;

    private Integer channel;

    public FbgValue(Integer id, Long physicalValueInfoId, BigDecimal value, Integer arrayNum, Long createTime,Integer channel) {
        this.id = id;
        this.physicalValueInfoId = physicalValueInfoId;
        this.value = value;
        this.arrayNum = arrayNum;
        this.createTime = createTime;
        this.channel = channel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPhysicalValueInfoId() {
        return physicalValueInfoId;
    }

    public void setPhysicalValueInfoId(Long physicalValueInfoId) {
        this.physicalValueInfoId = physicalValueInfoId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getArrayNum() {
        return arrayNum;
    }

    public void setArrayNum(Integer arrayNum) {
        this.arrayNum = arrayNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", physicalValueInfoId=").append(physicalValueInfoId);
        sb.append(", value=").append(value);
        sb.append(", arrayNum=").append(arrayNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", channel=").append(channel);
        sb.append("]");
        return sb.toString();
    }
}