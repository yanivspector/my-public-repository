package com.menora.system.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EntityScan(basePackages = "models.h2")
@EnableJpaRepositories(basePackages = "com.menora.system.reader")
@EnableScheduling
public class EventReaderSystemApplication {
	private static final Logger log = LoggerFactory.getLogger(EventReaderSystemApplication.class);


	@Autowired
	EventReaderController eventReaderController;

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext applicationContext =
						SpringApplication.run(EventReaderSystemApplication.class, args);
		checkDBTableConnection(applicationContext);
		initDB(applicationContext);


	}

	private static void initDB(ConfigurableApplicationContext applicationContext) throws Exception {
		EventReaderController bean = applicationContext.getBean(EventReaderController.class);
		bean.initDB();
	}

	private static void checkDBTableConnection(ConfigurableApplicationContext applicationContext){
		try {
			applicationContext.getBean(EventRepository.class);
			applicationContext.getBean(ProductRepository.class);
		}
		catch (Exception ex){
			log.error(" * * * Connection problem with DB's Table , please check * * *");
		}
	}

}
