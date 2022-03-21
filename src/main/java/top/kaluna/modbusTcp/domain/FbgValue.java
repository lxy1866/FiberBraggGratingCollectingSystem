package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;

public class FbgValue {
    private Long id;

    private Long physicalValueInfoId;

    private BigDecimal value;

    private Byte tag;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Byte getTag() {
        return tag;
    }

    public void setTag(Byte tag) {
        this.tag = tag;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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
        sb.append(", tag=").append(tag);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}