package top.kaluna.modbusTcp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "txt.position.file")
@Configuration
@Data
public class TxtPositionFileConfig {
    private String path;
}
