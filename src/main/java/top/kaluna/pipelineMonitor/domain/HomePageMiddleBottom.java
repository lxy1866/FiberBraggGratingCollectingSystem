package top.kaluna.pipelineMonitor.domain;

public class HomePageMiddleBottom {
    private Long id;

    private Long curveTotal;

    private String arrayNode;

    private String fileFormat;

    private String colName;

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

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
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
        sb.append(", colName=").append(colName);
        sb.append("]");
        return sb.toString();
    }
}