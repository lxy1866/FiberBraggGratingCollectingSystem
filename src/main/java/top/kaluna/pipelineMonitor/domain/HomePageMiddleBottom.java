package top.kaluna.pipelineMonitor.domain;

public class HomePageMiddleBottom {
    private Long id;

    private Long curveTotal;

    private String arrayNode;

    private String fileFormat;

    private Long colNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurveTotal() {
        return curveTotal;
    }

    public void setCurveTotal(Long curveTotal) {
        this.curveTotal = curveTotal;
    }

    public String getArrayNode() {
        return arrayNode;
    }

    public void setArrayNode(String arrayNode) {
        this.arrayNode = arrayNode;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public Long getColNum() {
        return colNum;
    }

    public void setColNum(Long colNum) {
        this.colNum = colNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", curveTotal=").append(curveTotal);
        sb.append(", arrayNode=").append(arrayNode);
        sb.append(", fileFormat=").append(fileFormat);
        sb.append(", colNum=").append(colNum);
        sb.append("]");
        return sb.toString();
    }
}