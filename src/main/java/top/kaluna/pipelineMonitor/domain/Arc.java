package top.kaluna.pipelineMonitor.domain;

import java.math.BigDecimal;

public class Arc {
    private Long id;

    private BigDecimal length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", length=").append(length);
        sb.append("]");
        return sb.toString();
    }
}