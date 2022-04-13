package top.kaluna.modbusTcp.domain;

public class BreakpointRecord {
    private Long id;

    private Integer arrayNum;

    private Long createTime;

    private Byte state;

    private String detail;

    private Integer channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getArrayNum() {
        return arrayNum;
    }

    public void setArrayNum(Integer arrayNum) {
        this.arrayNum = arrayNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", arrayNum=").append(arrayNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", state=").append(state);
        sb.append(", detail=").append(detail);
        sb.append(", channel=").append(channel);
        sb.append("]");
        return sb.toString();
    }
}