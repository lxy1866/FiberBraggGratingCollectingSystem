package top.kaluna.pipelineMonitor.req;

/**
 * @author Yuery
 * @date 2022/3/5/0005 - 22:16
 */
public class BreakpointRecordQueryReq extends PageReq{
    private Byte state;

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
