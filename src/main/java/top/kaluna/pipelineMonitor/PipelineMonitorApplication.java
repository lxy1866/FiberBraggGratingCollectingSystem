package top.kaluna.pipelineMonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("top.kaluna.modbusTcp.mapper")
@EnableRabbit
@EnableScheduling
@EnableOpenApi
public class PipelineMonitorApplication {

	private static final Logger LOG = LoggerFactory.getLogger(PipelineMonitorApplication.class);
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PipelineMonitorApplication.class);
		Environment environment = app.run(args).getEnvironment();
		LOG.info("启动成功！！");
		LOG.info("地址：\thttp://127.0.0.1:{}",environment.getProperty("server.port"));
	}
}
