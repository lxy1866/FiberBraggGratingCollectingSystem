package top.kaluna.pipelineMonitor.domain;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class AvgSensor {
    private Long id;

    private Integer arraySn;

    private String sensorNodeName;

    private Double avg;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private Date date;
    public AvgSensor(Integer arraySn, String sensorNodeName, Double avg, Date date){
        this.arraySn = arraySn;
        this.sensorNodeName = sensorNodeName;
        this.avg = avg;
        this.date = date;
    }
}