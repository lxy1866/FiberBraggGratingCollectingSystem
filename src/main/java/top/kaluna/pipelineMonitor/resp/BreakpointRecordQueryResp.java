package top.kaluna.pipelineMonitor.resp;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 22:34
 */
public class BreakpointRecordQueryResp {
    private Long id;

    private Integer  arrayNum;

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

    public int getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BreakpointRecordQueryResp{");
        sb.append("id=").append(id);
        sb.append(", arrayNum=").append(arrayNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", state=").append(state);
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", channel=").append(channel);
        sb.append('}');
        return sb.toString();
    }
}
