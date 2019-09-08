package programming.rental.model;


import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CarPojoTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldDeserlizeFromJson() throws IOException {

        String jsonCar = "{\"id\":1,\"producer\":\"Opel\",\"model\":\"Astra\",\"colour\":\"Blue\",\"price\":10.0,\"automatic\":true,\"attributes\":[\"metalic, PETROL\"]}";

        CarPojo car = mapper.readValue(jsonCar, CarPojo.class);

        assertThat(car.getCarId()).isEqualTo(1);
    }

    @Test
    void shouldSerializeToJson() throws JsonProcessingException {

        CarPojo car = new CarPojo();
        car.setCarId(2);
        car.setProducer("Unknown");

        String json = mapper.writeValueAsString(car);

        assertThatJson(json).node("id").isEqualTo(2);
        assertThatJson(json).node("producer").isEqualTo("Unknown");
        assertThatJson(json).node("model").isNull();
        assertThatJson(json).node("colour").isNull();
        assertThatJson(json).node("price").isNull();
        assertThatJson(json).node("automatic").isBoolean().isFalse();
        assertThatJson(json).node("attributes").isArray().isEmpty();

    }
}
