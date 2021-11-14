package com.menora.system.reader;


import models.h2.EventTable;
import models.h2.ProductTable;
import models.xml.Event;
import models.xml.Product;
import models.xml.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DaoServiceImpl implements DaoService {
  private static final Logger log = LoggerFactory.getLogger(DaoServiceImpl.class);

  @Autowired
  BeanFactory beanFactory;  //test

  ProductRepository productRepository;
  EventRepository eventRepository;
  XmlParserService xmlParserService;

  private XmlParserService getXmlParserService(){
    return beanFactory.getBean(XmlParserService.class);
  }


  private EventRepository getEventRepository(){
    return beanFactory.getBean(EventRepository.class);
  }

  private ProductRepository getProductRepository(){
    return beanFactory.getBean(ProductRepository.class);
  }




  @Override
  public void updateCompanyDB() {
    Request actualRequest = getXmlParserService().getActualRequest();
    List<Event> actualRequestEvents = actualRequest.getEvents();
    String companyNameToInsert = actualRequest.getRequestDetails().getSourceCompany();
    if(getAllCompaniesFromDB().stream().filter(existcompany -> existcompany.equals(companyNameToInsert)).count()>0){
      log.info("Company "+companyNameToInsert + " already exist on DB - Updating the DB with latest data for the this company");
      deleteAllRecordsBelongsToCompanyname(companyNameToInsert);
    }
    actualRequestEvents.stream().forEach(eventXml -> {
      String insuredId = eventXml.getInsuredId();
      String type = eventXml.getType();
      List<ProductTable> products = getProducts(eventXml.getProductList());
            EventTable eventTable = new EventTable(type, insuredId,companyNameToInsert,products);
      getEventRepository().save(eventTable);//Save to DB
    });

    String companyName = actualRequest.getRequestDetails().getSourceCompany();
    log.info("\n*  *  * DB belongs to "+ companyName + " has been updated * * * ");
  }

  @Override
  public List<EventTable> getAllEventsByCompanyName(String companyName) {
    Iterable<EventTable> iterable = getEventRepository().findAll();
    List<EventTable> events = new ArrayList<EventTable>();
    iterable.forEach(events::add);
    return events.stream().filter(event -> event.getCompanyName().equals(companyName))
            .collect(Collectors.toList());

  }

  @Override
  public List<ProductTable> getAllProductsByInsuredIdAndCompanyName(String insuredId, String companyName) {
    List<EventTable> allEventsByCompanyName = getAllEventsByCompanyName(companyName);
    List<ProductTable> products = allEventsByCompanyName.stream().filter(event -> event.getInsuredId().equals(insuredId))
            .map(event -> event.getProducts())
            .flatMap(List::stream)
            .collect(Collectors.toList());
    return products;
  }

  private List<String> getAllInsuredIdsByCompanyName(String companyName){
    List<EventTable> allEventsByCompanyName = getAllEventsByCompanyName(companyName);
    return allEventsByCompanyName.stream().map(event -> event.getInsuredId()).collect(Collectors.toList());
  }

  @Override
  public Map<String, Map<String, List<ProductTable>>> getAllProductsByInsuredIdGroupedByCompany() {
    Map<String, Map<String, List<ProductTable>>> companiesMap = new HashMap<>();
    Map<String, List<ProductTable>> productMap = new HashMap<>();
    Iterable<EventTable> iterable = getEventRepository().findAll();
    List<EventTable> events = new ArrayList<EventTable>();
    iterable.forEach(events::add);
    List<String> companies = events.stream().map(event -> event.getCompanyName())
            .collect(Collectors.toList());
    companies.stream().distinct().forEach(companyName -> {
      List<String> allInsuredIdsByCompanyName = getAllInsuredIdsByCompanyName(companyName);
      allInsuredIdsByCompanyName.forEach(insuredId -> productMap.put(insuredId,getAllProductsByInsuredIdAndCompanyName(insuredId,companyName)));
      companiesMap.put(companyName,productMap);
    });
    return companiesMap;
  }

  @Override
  public List<String> getAllCompaniesFromDB() {
    Iterable<EventTable> iterable = getEventRepository().findAll();
    List<EventTable> events = new ArrayList<EventTable>();
    iterable.forEach(events::add);
    return events.stream().map(event -> event.getCompanyName())
            .collect(Collectors.toList());
  }

  @Override
  public void deleteAllRecordsBelongsToCompanyname(String companyName) {
    Iterable<EventTable> iterable = getEventRepository().findAll();
    List<EventTable> events = new ArrayList<EventTable>();
    iterable.forEach(events::add);
    events.stream().filter(event -> event.getCompanyName().equals(companyName))
            .forEach(event -> getEventRepository().delete(event));
    log.info("* * *  All events belong to  "+ companyName + " have been deleted ");
  }

  private List<ProductTable> getProducts(List<Product> productList) {
    List<ProductTable> productTableList = productList.stream()
            .map(product -> getProductTable(product))
            .collect(Collectors.toList());
    return productTableList;
  }

  @Override
  public ProductTable getProductTable(Product productXml) {
    String type = productXml.getType();
    Integer price = Integer.valueOf(productXml.getPrice());
    String startDate = productXml.getStartDate();
    String  endDate = productXml.getEndDate();
    return new ProductTable(type,price,startDate,endDate);

//    getProductRepository().save(productTable);//Save to DB
  }

  private Date convertStringToDate(String stringDate){
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

}
