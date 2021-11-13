package Utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public interface XmlReaderService {
  String readXmlFile(File filePath) throws IOException;
}
