package models.h2;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_table")
public class ProductTable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer Id;

  String type;
  int price;
  String startDate;
  String endDate;


  public  ProductTable(){}

  public ProductTable( String type, int price, String startDate, String endDate) {
    this.type = type;
    this.price = price;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Integer getId() {
    return Id;
  }

  public String getType() {
    return type;
  }

  public int getPrice() {
    return price;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }


}
