package top.kaluna.modbusTcp.req;

import java.math.BigDecimal;
import java.util.Date;

public class PhysicalValueReq {
    private Long id;

    private BigDecimal val1;

    private BigDecimal val2;

    private BigDecimal val3;

    private BigDecimal val4;

    private BigDecimal val5;

    private BigDecimal val6;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getVal1() {
        return val1;
    }

    public void setVal1(BigDecimal val1) {
        this.val1 = val1;
    }

    public BigDecimal getVal2() {
        return val2;
    }

    public void setVal2(BigDecimal val2) {
        this.val2 = val2;
    }

    public BigDecimal getVal3() {
        return val3;
    }

    public void setVal3(BigDecimal val3) {
        this.val3 = val3;
    }

    public BigDecimal getVal4() {
        return val4;
    }

    public void setVal4(BigDecimal val4) {
        this.val4 = val4;
    }

    public BigDecimal getVal5() {
        return val5;
    }

    public void setVal5(BigDecimal val5) {
        this.val5 = val5;
    }

    public BigDecimal getVal6() {
        return val6;
    }

    public void setVal6(BigDecimal val6) {
        this.val6 = val6;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", val1=").append(val1);
        sb.append(", val2=").append(val2);
        sb.append(", val3=").append(val3);
        sb.append(", val4=").append(val4);
        sb.append(", val5=").append(val5);
        sb.append(", val6=").append(val6);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}