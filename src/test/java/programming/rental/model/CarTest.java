package programming.rental.model;


import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

class CarTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldDeserlizeFromJson() throws IOException {

        String jsonCar = "{\"id\":1,\"producer\":\"Opel\",\"model\":\"Astra\",\"colour\":\"Blue\",\"price\":10.0,\"automatic\":true,\"attributes\":[\"metalic, PETROL\"]}";

        Car car = mapper.readValue(jsonCar, Car.class);

        CarAssert.assertThat(car)
                .hasId(1)
                .hasProducer("Opel")
                .hasModel("Astra")
                .hasColour("Blue")
                .isAutomatic()
                .hasPrice(BigDecimal.valueOf(10.0))
                .hasAttributes("metalic,PETROL");
    }

    @Test
    void shouldSerializeToJson() throws JsonProcessingException {

        Car car = new Car(2, "Uknown", "Unknown", "Black", BigDecimal.TEN, false);
        car.addAttributes(new HashSet<>(Arrays.asList("first", "Second")));

        String json = mapper.writeValueAsString(car);

        assertThatJson(json).node("id").isEqualTo(2);
        assertThatJson(json).node("producer").isEqualTo("Uknown");
        assertThatJson(json).node("model").isEqualTo("Unknown");
        assertThatJson(json).node("colour").isEqualTo("Black");
        assertThatJson(json).node("price").isNumber().isEqualTo(BigDecimal.TEN);
        assertThatJson(json).node("automatic").isBoolean().isFalse();
        assertThatJson(json).node("attributes").isArray().containsAnyOf("first");
    }
}
