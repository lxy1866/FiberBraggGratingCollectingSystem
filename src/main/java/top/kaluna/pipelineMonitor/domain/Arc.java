package top.kaluna.pipelineMonitor.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Arc {
    private Long id;

    private BigDecimal length;

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