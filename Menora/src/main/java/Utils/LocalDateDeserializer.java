package Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate>
{
  public LocalDate deserialize(JsonParser arg0, DeserializationContext arg1)
          throws java.io.IOException, JsonProcessingException {
    return LocalDate.parse(arg0.getText());
  }
}
