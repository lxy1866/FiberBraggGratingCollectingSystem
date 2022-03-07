package top.kaluna.modbusTcp.resp;

import java.math.BigDecimal;

/**
 * @author Yuery
 * @date 2022/3/7/0007 - 0:39
 */
public class NormalRangeQueryResp {
    private Long id;

    private BigDecimal val1MinValue;

    private BigDecimal val2MinValue;

    private BigDecimal val3MinValue;

    private BigDecimal val4MinValue;

    private BigDecimal val5MinValue;

    private BigDecimal val6MinValue;

    private BigDecimal val1MaxValue;

    private BigDecimal val2MaxValue;

    private BigDecimal val3MaxValue;

    private BigDecimal val4MaxValue;

    private BigDecimal val5MaxValue;

    private BigDecimal val6MaxValue;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getVal1MinValue() {
        return val1MinValue;
    }

    public void setVal1MinValue(BigDecimal val1MinValue) {
        this.val1MinValue = val1MinValue;
    }

    public BigDecimal getVal2MinValue() {
        return val2MinValue;
    }

    public void setVal2MinValue(BigDecimal val2MinValue) {
        this.val2MinValue = val2MinValue;
    }

    public BigDecimal getVal3MinValue() {
        return val3MinValue;
    }

    public void setVal3MinValue(BigDecimal val3MinValue) {
        this.val3MinValue = val3MinValue;
    }

    public BigDecimal getVal4MinValue() {
        return val4MinValue;
    }

    public void setVal4MinValue(BigDecimal val4MinValue) {
        this.val4MinValue = val4MinValue;
    }

    public BigDecimal getVal5MinValue() {
        return val5MinValue;
    }

    public void setVal5MinValue(BigDecimal val5MinValue) {
        this.val5MinValue = val5MinValue;
    }

    public BigDecimal getVal6MinValue() {
        return val6MinValue;
    }

    public void setVal6MinValue(BigDecimal val6MinValue) {
        this.val6MinValue = val6MinValue;
    }

    public BigDecimal getVal1MaxValue() {
        return val1MaxValue;
    }

    public void setVal1MaxValue(BigDecimal val1MaxValue) {
        this.val1MaxValue = val1MaxValue;
    }

    public BigDecimal getVal2MaxValue() {
        return val2MaxValue;
    }

    public void setVal2MaxValue(BigDecimal val2MaxValue) {
        this.val2MaxValue = val2MaxValue;
    }

    public BigDecimal getVal3MaxValue() {
        return val3MaxValue;
    }

    public void setVal3MaxValue(BigDecimal val3MaxValue) {
        this.val3MaxValue = val3MaxValue;
    }

    public BigDecimal getVal4MaxValue() {
        return val4MaxValue;
    }

    public void setVal4MaxValue(BigDecimal val4MaxValue) {
        this.val4MaxValue = val4MaxValue;
    }

    public BigDecimal getVal5MaxValue() {
        return val5MaxValue;
    }

    public void setVal5MaxValue(BigDecimal val5MaxValue) {
        this.val5MaxValue = val5MaxValue;
    }

    public BigDecimal getVal6MaxValue() {
        return val6MaxValue;
    }

    public void setVal6MaxValue(BigDecimal val6MaxValue) {
        this.val6MaxValue = val6MaxValue;
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
        sb.append(", val1MinValue=").append(val1MinValue);
        sb.append(", val2MinValue=").append(val2MinValue);
        sb.append(", val3MinValue=").append(val3MinValue);
        sb.append(", val4MinValue=").append(val4MinValue);
        sb.append(", val5MinValue=").append(val5MinValue);
        sb.append(", val6MinValue=").append(val6MinValue);
        sb.append(", val1MaxValue=").append(val1MaxValue);
        sb.append(", val2MaxValue=").append(val2MaxValue);
        sb.append(", val3MaxValue=").append(val3MaxValue);
        sb.append(", val4MaxValue=").append(val4MaxValue);
        sb.append(", val5MaxValue=").append(val5MaxValue);
        sb.append(", val6MaxValue=").append(val6MaxValue);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
