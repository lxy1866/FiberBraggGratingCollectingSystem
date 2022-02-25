package top.kaluna.modbusTcp.req;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuery
 * @date 2022/2/25/0025 - 16:16
 */
public class PhysicalValueSaveReq extends PageReq {
    private Long id;
    @NotNull(message="[val1]不能为空")
    private BigDecimal val1;
    @NotNull(message="[val2]不能为空")
    private BigDecimal val2;
    @NotNull(message="[val3]不能为空")
    private BigDecimal val3;
    @NotNull(message="[val4]不能为空")
    private BigDecimal val4;
    @NotNull(message="[val5]不能为空")
    private BigDecimal val5;
    @NotNull(message="[val6]不能为空")
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
}
