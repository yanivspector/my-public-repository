package models.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Request {

  @XmlElement(name = "RequestDetails")
  RequestDetails requestDetails = new RequestDetails();

  @XmlElement(name = "Event")
  List<Event> events = new ArrayList<Event>();

  public RequestDetails getRequestDetails() {
    return requestDetails;
  }

  public void setRequestDetails(RequestDetails requestDetails) {
    this.requestDetails = requestDetails;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  @Override
  public String toString() {
    return "Request{" +
            "requestDetails=" + requestDetails +
            ", events=" + events +
            '}';
  }
}
