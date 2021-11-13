package Utils;


import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class XmlReaderServiceImpl implements XmlReaderService {

//  @Autowired
//  BeanFactory beanFactory;

  public String readXmlFile(File filePath) throws IOException {
    return inputStreamToString(new FileInputStream(filePath));
  }

  private  String inputStreamToString(InputStream is) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    br.close();
    return sb.toString();
  }

}
