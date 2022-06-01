package top.kaluna.pipelineMonitor.req;

import lombok.Data;

import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/18/0018 - 21:39
 */
@Data
public class LeftTopAttributeReq {

    private Long id;

    private String frequency;

    private Long arrayTotal;

    private List<Long> eachArrayNum;

    private Long groupNumAm;

    private Long groupNumPm;

    private String fileFormat;

    private String colName;

}
