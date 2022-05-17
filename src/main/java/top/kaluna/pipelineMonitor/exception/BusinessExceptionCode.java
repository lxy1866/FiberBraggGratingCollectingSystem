package top.kaluna.pipelineMonitor.exception;

/**
 * @author Yuery
 * @date 2022/2/25/0025 - 21:44
 */
public enum BusinessExceptionCode {
    /**
     * 登录名已存在
     */
    USER_LOGIN_NAME_EXIST("登录名已存在"),
    /**
     * 用户名不存在或密码错误
     */
    LOGIN_USER_ERROR("用户名不存在或密码错误"),

    /**
     * 文件相关
     */
    FILE_UPLOAD_FAIL("文件上传失败");

    private String desc;

    BusinessExceptionCode(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
}
