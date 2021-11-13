package models.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
  String Id;
  String Type;
  String InsuredId;

  @XmlElement(name = "Product")
  List<Product> productList = new ArrayList<>();

  public String getId() {
    return Id;
  }

  public void setId(String Id) {
    this.Id = Id;
  }

  public String getType() {
    return Type;
  }

  public void setType(String Type) {
    Type = Type;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public String getInsuredId() {
    return InsuredId;
  }

  public void setInsuredId(String insuredId) {
    InsuredId = insuredId;
  }

  @Override
  public String toString() {
    return "Event{" +
            "Id='" + Id + '\'' +
            ", Type='" + Type + '\'' +
            ", InsuredId='" + InsuredId + '\'' +
            ", productList=" + productList +
            '}';
  }
}
