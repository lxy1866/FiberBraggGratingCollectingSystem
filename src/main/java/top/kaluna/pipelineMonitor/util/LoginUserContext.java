package top.kaluna.pipelineMonitor.util;

import top.kaluna.pipelineMonitor.resp.UserLoginResp;

import java.io.Serializable;

/**
 * @author Yuery
 * @date 2022/2/27/0027 - 22:10
 */
public class LoginUserContext implements Serializable {
    private static ThreadLocal<UserLoginResp> user = new ThreadLocal<>();

    public static UserLoginResp getUser(){
        return user.get();
    }
    public static void setUser(UserLoginResp user){
        LoginUserContext.user.set(user);
    }
}
