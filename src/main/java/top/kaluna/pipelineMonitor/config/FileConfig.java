package top.kaluna.pipelineMonitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yuery
 * @date 2022/5/20/0020 - 17:46
 */

@ConfigurationProperties(prefix = "save.file")
@Configuration
@Data
public class FileConfig {

    /**
     * 拉取到服务器上的文件保存路径
     */
    private String path;

    private String name1;

    private String name2;
}
