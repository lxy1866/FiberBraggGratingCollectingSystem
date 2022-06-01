package top.kaluna.pipelineMonitor.req;

import lombok.Data;
import top.kaluna.pipelineMonitor.domain.ArrayCombineSensorSerialNum;

import java.util.HashMap;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/18/0018 - 21:40
 */
@Data
public class MiddleBottomAttributeReq {

    private Long id;

    private Long curveTotal;

    private List<ArrayCombineSensorSerialNum> arrayNode;

    private String fileFormat;

    private String colName;
}
