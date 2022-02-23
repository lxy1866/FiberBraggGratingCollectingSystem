package top.kaluna.modbusTcp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.kaluna.modbusTcp.mapper")
@EnableRabbit
public class ModbusTcpApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ModbusTcpApplication.class, args);
		//new MasterExample(1, 1).start();
	}

}
