package programming.rental.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@JsonPOJOBuilder(buildMethodName = "createCar", withPrefix = "set")
public class CarBuilder {
    private long id;
    private String producer;
    private String model;
    private String colour;
    private BigDecimal price;
    private boolean automatic;
    private Set<String> attributes = new HashSet<>();

    public CarBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public CarBuilder setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public CarBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CarBuilder setAutomatic(boolean automatic) {
        this.automatic = automatic;
        return this;
    }

    public CarBuilder setAttributes(Set<String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Car createCar() {
        if (id == 0) {
            throw new IllegalArgumentException("Id must be set");
        }
        // since other attributes are required their should be validated as well
        if (StringUtils.isAnyBlank(producer, model, colour)) {
            throw new IllegalArgumentException("Required attributes must be set");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be set to value greater than 0");
        }

        Car car = new Car(id, producer, model, colour, price, automatic);
        car.addAttributes(attributes);
        return car;
    }
}