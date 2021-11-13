package com.menora.system.reader;


import models.xml.Request;
import org.apache.tomcat.jni.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Lazy
@ComponentScan({"com.menora.system.reader", "utils"})
@PropertySource("classpath:env/file.properties")
public class XmlParserServiceImpl implements XmlParserService {
  private static final Logger log = LoggerFactory.getLogger(XmlParserServiceImpl.class);

  @Autowired
  BeanFactory beanFactory;



  private Request actualRequest;

  @Value("${request.folder.path}")
  public String requestFolderPath;


  private DaoService getDaoService(){
    return beanFactory.getBean(DaoService.class);
  }


  public Request readRequestFile(String filePath){
    return JAXB.unmarshal(new File(filePath), Request.class);
  }

  public List<String> getRequestListFiles(String folderPath) throws Exception {
    File folder = new File(folderPath);
    File[] files = folder.listFiles();
    if(files == null){
      throw new Exception("Can not read file Request.xml under path "+ folderPath);
    }

    return Arrays.asList(files).stream()
            .filter(file -> file.isFile() && file.getName().contains("Request.xml"))
            .map(file -> file.getAbsolutePath())
            .collect(Collectors.toList());

  }

  public void updateDB() throws Exception {
    List<String> requestListFiles = getRequestListFiles(this.requestFolderPath);
    Request actualRequest = readRequestFile(requestListFiles.get(0));
    setActualRequest(actualRequest);
    //Update H2 DB
    updateH2_DB();

//    log.info("* * *  XML  Content ****");
//    System.out.println(actualRequest.getEvents().get(0).getId());

  }

  private void updateH2_DB() {
    getDaoService().updateCompanyDB();
  }

  @Override
  public Request getActualRequest() {
    return actualRequest;
  }

  //This method should be call only by DB's scheduler every hour via updateDB() method
  public void setActualRequest(Request actualRequest) {
    this.actualRequest = actualRequest;
  }


  @PostConstruct
  void initXmlServiceParser() {
    log.info("* * * Init Xml Parser * * *");
  }


}
