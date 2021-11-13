package Utils;



import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


import java.time.LocalDate;

public class LocalDateSerializer extends JsonSerializer<LocalDate>
{
  public void serialize(LocalDate arg0,
                        JsonGenerator arg1,
                        SerializerProvider arg2)
          throws java.io.IOException, JsonProcessingException {
    arg1.writeString(arg0.toString());
  }
}
