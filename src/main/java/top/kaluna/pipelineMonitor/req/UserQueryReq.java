package top.kaluna.pipelineMonitor.req;

/**
 * @author Yuery
 * @date 2022/2/25/0025 - 21:59
 */
public class UserQueryReq extends PageReq{


    private String loginName;

    public String getLoginName(){
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
