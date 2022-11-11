package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MinMaxValueForTemperature {
    private Integer id;

    private Date createTime;

    private BigDecimal min;

    private BigDecimal max;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", min=").append(min);
        sb.append(", max=").append(max);
        sb.append("]");
        return sb.toString();
    }
}