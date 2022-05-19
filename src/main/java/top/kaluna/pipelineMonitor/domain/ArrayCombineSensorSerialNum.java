package top.kaluna.pipelineMonitor.domain;

import lombok.Data;

/**
 * @author Yuery
 * @date 2022/5/18/0018 - 23:41
 */
@Data
public class ArrayCombineSensorSerialNum {

    /**
     * 阵列号
     */
    public Long key;
    /**
     * 阵列内部编号
     */
    public Long value;
}
