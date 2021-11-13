package models.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
  String Type;
  String Price;
  String StartDate;
  String EndDate;

  public String getType() {
    return Type;
  }

  public void setType(String Type) {
    this.Type = Type;
  }

  public String getPrice() {
    return Price;
  }

  public void setPrice(String price) {
    this.Price = Price;
  }


  public String getStartDate() {
    return StartDate;
  }

  public void setStartDate(String StartDate) {
    StartDate = StartDate;
  }

  public String getEndDate() {
    return EndDate;
  }

  public void setEndDate(String EndDate) {
    EndDate = EndDate;
  }


  @Override
  public String toString() {
    return "Product{" +
            "Type='" + Type + '\'' +
            ", Price='" + Price + '\'' +
            ", startDate=" + StartDate +
            ", endDate=" + EndDate +
            '}';
  }
}
