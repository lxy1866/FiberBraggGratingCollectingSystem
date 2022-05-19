package top.kaluna.pipelineMonitor.domain;

public class HomePageLeftTop {
    private Long id;

    private String frequency;

    private Long arrayTotal;

    private String eachArrayNum;

    private Long groupNumAm;

    private Long groupNumPm;

    private String fileFormat;

    private Long colNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Long getArrayTotal() {
        return arrayTotal;
    }

    public void setArrayTotal(Long arrayTotal) {
        this.arrayTotal = arrayTotal;
    }

    public String getEachArrayNum() {
        return eachArrayNum;
    }

    public void setEachArrayNum(String eachArrayNum) {
        this.eachArrayNum = eachArrayNum;
    }

    public Long getGroupNumAm() {
        return groupNumAm;
    }

    public void setGroupNumAm(Long groupNumAm) {
        this.groupNumAm = groupNumAm;
    }

    public Long getGroupNumPm() {
        return groupNumPm;
    }

    public void setGroupNumPm(Long groupNumPm) {
        this.groupNumPm = groupNumPm;
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
        sb.append(", frequency=").append(frequency);
        sb.append(", arrayTotal=").append(arrayTotal);
        sb.append(", eachArrayNum=").append(eachArrayNum);
        sb.append(", groupNumAm=").append(groupNumAm);
        sb.append(", groupNumPm=").append(groupNumPm);
        sb.append(", fileFormat=").append(fileFormat);
        sb.append(", colNum=").append(colNum);
        sb.append("]");
        return sb.toString();
    }
}