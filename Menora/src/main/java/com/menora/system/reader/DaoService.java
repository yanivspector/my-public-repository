package com.menora.system.reader;

import models.h2.EventTable;
import models.h2.ProductTable;
import models.xml.Product;

import java.util.List;
import java.util.Map;

public interface DaoService {

  ProductTable getProductTable(Product productXml);

  void updateCompanyDB();

  List<EventTable>  getAllEventsByCompanyName(String companyName);

  List<ProductTable> getAllProductsByInsuredIdAndCompanyName(String insuredId,String companyName);

  Map<String, Map<String, List<ProductTable>>> getAllProductsByInsuredIdGroupedByCompany();

  List<String> getAllCompaniesFromDB();

  void deleteAllRecordsBelongsToCompanyname(String companyName);
}
