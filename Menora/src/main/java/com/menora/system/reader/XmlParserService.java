package com.menora.system.reader;

import models.xml.Request;

import java.io.IOException;


public interface XmlParserService {

   void updateDB() throws Exception;

   Request readRequestFile(String filePath);

   Request getActualRequest();

}


