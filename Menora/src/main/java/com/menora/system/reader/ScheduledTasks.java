package com.menora.system.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@PropertySource("classpath:env/file.properties")
public class ScheduledTasks {

  @Autowired
  EventReaderController eventReaderController;

  @Value("${cronTime}")
  public String cronTime;

  private static final Logger log = LoggerFactory.getLogger(DaoServiceImpl.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(cron ="${cronTime}")
  public void reportCurrentTime() throws Exception {
    log.info("The time is now {}", dateFormat.format(new Date()));
    eventReaderController.initDB();
  }
}
