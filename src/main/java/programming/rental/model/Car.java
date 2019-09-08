package programming.rental.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(builder = CarBuilder.class)
public class Car {
    private final long id;
    private final String producer;
    private final String model;
    private final String colour;
    private final BigDecimal price;
    private final boolean automatic;
    private final Set<String> attributes = new HashSet<>();

    Car(long id, String producer, String model, String colour, BigDecimal price, boolean automatic) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.colour = colour;
        this.price = price;
        this.automatic = automatic;
    }

    public long getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    void addAttributes(Set<String> attributes) {
        if (attributes != null) {
            this.attributes.addAll(attributes);
        }
    }

    public Set<String> getAttributes() {
        return Collections.unmodifiableSet(attributes);
    }
}