package top.kaluna.modbusTcp.resp;

import javax.validation.constraints.NotNull;

/**
 * @author Yuery
 * @date 2022/2/22/0022 - 12:27
 */
public class PhysicalValueQueryResp {
    @NotNull(message="[val1]不能为空")
    private float val1;
    @NotNull(message="[val2]不能为空")
    private float val2;
    @NotNull(message="[val3]不能为空")
    private float val3;
    @NotNull(message="[val4]不能为空")
    private float val4;
    @NotNull(message="[val5]不能为空")
    private float val5;
    @NotNull(message="[val6]不能为空")
    private float val6;

    public float getVal1() {
        return val1;
    }

    public void setVal1(float val1) {
        this.val1 = val1;
    }

    public float getVal2() {
        return val2;
    }

    public void setVal2(float val2) {
        this.val2 = val2;
    }

    public float getVal3() {
        return val3;
    }

    public void setVal3(float val3) {
        this.val3 = val3;
    }

    public float getVal4() {
        return val4;
    }

    public void setVal4(float val4) {
        this.val4 = val4;
    }

    public float getVal5() {
        return val5;
    }

    public void setVal5(float val5) {
        this.val5 = val5;
    }

    public float getVal6() {
        return val6;
    }

    public void setVal6(float val6) {
        this.val6 = val6;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PhysicalValue{");
        sb.append("val1=").append(val1);
        sb.append(", val2=").append(val2);
        sb.append(", val3=").append(val3);
        sb.append(", val4=").append(val4);
        sb.append(", val5=").append(val5);
        sb.append(", val6=").append(val6);
        sb.append('}');
        return sb.toString();
    }
}
