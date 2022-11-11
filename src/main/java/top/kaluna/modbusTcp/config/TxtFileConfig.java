package top.kaluna.modbusTcp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "txt.file")
@Configuration
@Data
public class TxtFileConfig {
    private String path;
}
