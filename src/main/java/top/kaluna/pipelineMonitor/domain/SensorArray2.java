package top.kaluna.pipelineMonitor.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Yuery
 * @date 2022/6/1/0001 - 14:12
 */
@Data
@NoArgsConstructor
public class SensorArray2 {
    private Long id;

    private Integer arraySn;

    @ExcelProperty(index = 0)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private Date date;
    //节点1
    @ExcelProperty(index = 1)
    private Double sensorNode1x;
    @ExcelProperty(index = 2)
    private Double sensorNode1y;
    @ExcelProperty(index = 3)
    private Double sensorNode1z;
    //节点2
    @ExcelProperty(index = 4)
    private Double sensorNode2x;
    @ExcelProperty(index = 5)
    private Double sensorNode2y;
    @ExcelProperty(index = 6)
    private Double sensorNode2z;
    //节点3
    @ExcelProperty(index = 7)
    private Double sensorNode3x;
    @ExcelProperty(index = 8)
    private Double sensorNode3y;
    @ExcelProperty(index = 9)
    private Double sensorNode3z;
    //节点4
    @ExcelProperty(index = 10)
    private Double sensorNode4x;
    @ExcelProperty(index = 11)
    private Double sensorNode4y;
    @ExcelProperty(index = 12)
    private Double sensorNode4z;
    //节点5
    @ExcelProperty(index = 13)
    private Double sensorNode5x;
    @ExcelProperty(index = 14)
    private Double sensorNode5y;
    @ExcelProperty(index = 15)
    private Double sensorNode5z;
    //节点6
    @ExcelProperty(index = 16)
    private Double sensorNode6x;
    @ExcelProperty(index = 17)
    private Double sensorNode6y;
    @ExcelProperty(index = 18)
    private Double sensorNode6z;
    //节点7
    @ExcelProperty(index = 19)
    private Double sensorNode7x;
    @ExcelProperty(index = 20)
    private Double sensorNode7y;
    @ExcelProperty(index = 21)
    private Double sensorNode7z;
    //节点8
    @ExcelProperty(index = 22)
    private Double sensorNode8x;
    @ExcelProperty(index = 23)
    private Double sensorNode8y;
    @ExcelProperty(index = 24)
    private Double sensorNode8z;
    //节点9
    @ExcelProperty(index = 25)
    private Double sensorNode9x;
    @ExcelProperty(index = 26)
    private Double sensorNode9y;
    @ExcelProperty(index = 27)
    private Double sensorNode9z;
    //节点10
    @ExcelProperty(index = 28)
    private Double sensorNode10x;
    @ExcelProperty(index = 29)
    private Double sensorNode10y;
    @ExcelProperty(index = 30)
    private Double sensorNode10z;

    public SensorArray2(Integer arraySn, Date date, Double sensorNode1x, Double sensorNode1y, Double sensorNode1z, Double sensorNode2x, Double sensorNode2y, Double sensorNode2z, Double sensorNode3x, Double sensorNode3y, Double sensorNode3z, Double sensorNode4x, Double sensorNode4y, Double sensorNode4z, Double sensorNode5x, Double sensorNode5y, Double sensorNode5z, Double sensorNode6x, Double sensorNode6y, Double sensorNode6z, Double sensorNode7x, Double sensorNode7y, Double sensorNode7z, Double sensorNode8x, Double sensorNode8y, Double sensorNode8z, Double sensorNode9x, Double sensorNode9y, Double sensorNode9z, Double sensorNode10x, Double sensorNode10y, Double sensorNode10z) {
        this.arraySn = arraySn;
        this.date = date;
        this.sensorNode1x = sensorNode1x;
        this.sensorNode1y = sensorNode1y;
        this.sensorNode1z = sensorNode1z;
        this.sensorNode2x = sensorNode2x;
        this.sensorNode2y = sensorNode2y;
        this.sensorNode2z = sensorNode2z;
        this.sensorNode3x = sensorNode3x;
        this.sensorNode3y = sensorNode3y;
        this.sensorNode3z = sensorNode3z;
        this.sensorNode4x = sensorNode4x;
        this.sensorNode4y = sensorNode4y;
        this.sensorNode4z = sensorNode4z;
        this.sensorNode5x = sensorNode5x;
        this.sensorNode5y = sensorNode5y;
        this.sensorNode5z = sensorNode5z;
        this.sensorNode6x = sensorNode6x;
        this.sensorNode6y = sensorNode6y;
        this.sensorNode6z = sensorNode6z;
        this.sensorNode7x = sensorNode7x;
        this.sensorNode7y = sensorNode7y;
        this.sensorNode7z = sensorNode7z;
        this.sensorNode8x = sensorNode8x;
        this.sensorNode8y = sensorNode8y;
        this.sensorNode8z = sensorNode8z;
        this.sensorNode9x = sensorNode9x;
        this.sensorNode9y = sensorNode9y;
        this.sensorNode9z = sensorNode9z;
        this.sensorNode10x = sensorNode10x;
        this.sensorNode10y = sensorNode10y;
        this.sensorNode10z = sensorNode10z;
    }
}

