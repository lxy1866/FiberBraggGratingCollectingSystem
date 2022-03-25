package top.kaluna.modbusTcp.resp;

import java.math.BigDecimal;

/**
 * @author Yuery
 * @date 2022/3/24/0024 - 21:46
 */
public class LastNHoursMinAndMaxResp {
    BigDecimal max;
    BigDecimal min;

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }
}
