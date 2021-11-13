package com.menora.system.reader;

import models.h2.ProductTable;
import models.xml.Request;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EventReaderController {


  @Autowired
  XmlParserService xmlRequestParserService;

  @Autowired
  BeanFactory beanFactory;


  public DaoService getDaoService(){
    return beanFactory.getBean(DaoService.class);
  }

  @PostConstruct
  void initEventReaderController() throws IOException {
  }


  public void initDB() throws Exception {
    xmlRequestParserService.updateDB();
  }

  @GetMapping("/updateDB")
  public void updateDB() throws Exception {
    initDB();
  }

  //When the Mapping back an Object it is automatically converted to 'Json' by the Dispatcher Servlet
  @GetMapping("/showDB")
  public Request queryToDB() throws IOException {
    return xmlRequestParserService.getActualRequest();
  }

  @GetMapping("/showAllProductsByInsuredId")
  public Map<String, Map<String, List<ProductTable>>> showAllProductsByInsuredId() throws IOException {
    return getDaoService().getAllProductsByInsuredIdGroupedByCompany();

  }




}
