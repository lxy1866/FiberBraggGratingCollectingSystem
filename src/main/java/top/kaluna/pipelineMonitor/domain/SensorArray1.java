package top.kaluna.pipelineMonitor.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Yuery
 * @date 2022/6/1/0001 - 12:37
 */
@Data
@NoArgsConstructor
public class SensorArray1 {
    private Long id;

    private Integer arraySn;

    @ExcelProperty(index = 0)
    private String sensorNodeName;

    @ExcelProperty(index = 1)
    private Double xValue;

    @ExcelProperty(index = 2)
    private Double yValue;

    @ExcelProperty(index = 3)
    private Double zValue;

    @ExcelProperty(index = 4)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private Date date;

    public SensorArray1(Integer arraySn, String s, Double valueOf, Double valueOf1, Double valueOf2, Date parse) {
        this.arraySn = arraySn;
        this.sensorNodeName = s;
        this.xValue = valueOf;
        this.yValue = valueOf1;
        this.zValue = valueOf2;
        this.date = parse;
    }
}
