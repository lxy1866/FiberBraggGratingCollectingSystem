package top.kaluna.modbusTcp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NormalRange {
    private Long id;

    /**
     * 所有光纤光栅传感阵列的最小最大值
     */
    List<List<BigDecimal>> values = new ArrayList<>();

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<List<BigDecimal>> getValues() {
        return values;
    }

    public void setValues(List<List<BigDecimal>> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NormalRange{");
        sb.append("id=").append(id);
        sb.append(", values=").append(values);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}

