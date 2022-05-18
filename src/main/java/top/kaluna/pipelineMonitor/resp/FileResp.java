package top.kaluna.pipelineMonitor.resp;

import lombok.Data;

/**
 * @author Yuery
 * @date 2022/5/17/0017 - 17:15
 */
@Data
public class FileResp {
    private String url;
    private String name;
    private String status;//: 'done', // 状态有：uploading done error removed
    private String response;//: '{"status": "success"}', // 服务端响应内容
}
