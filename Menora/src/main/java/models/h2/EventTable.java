package models.h2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="EVENT_TABLE")
public class EventTable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String type;
  private String insuredId;
  private String companyName;


  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )

  @JoinColumn(name = "event_id")
  List<ProductTable> products = new ArrayList<>();

  public EventTable(){};

  public EventTable(String type, String insuredId , String companyName,  List<ProductTable> products){
    this.type = type;
    this.insuredId = insuredId;
    this.products = products;
    this.companyName = companyName;
  }

  public Integer getId() {
    return id;
  }


  public String getCompanyName() {
    return companyName;
  }


  public String getType() {
    return type;
  }

  public String getInsuredId() {
    return insuredId;
  }

  public List<ProductTable> getProducts() {
    return products;
  }

  public void setProducts(List<ProductTable> products) {
    this.products = products;
  }

}







