package models.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class RequestDetails {
  String Id;
  String SourceCompany;

  public String getId() {
    return Id;
  }

  public void setId(String Id) {
    Id = Id;
  }

  public String getSourceCompany() {
    return SourceCompany;
  }

  public void setSourceCompany(String sourceCompany) {
    SourceCompany = sourceCompany;
  }

  @Override
  public String toString() {
    return "RequestDetails{" +
            "Id='" + Id + '\'' +
            ", SourceCompany='" + SourceCompany + '\'' +
            '}';
  }
}
