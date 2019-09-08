package programming.rental.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CarPojo {
    @JsonProperty("id")
    private long carId;
    private String producer;
    private String model;
    private String colour;
    private BigDecimal price;
    private boolean automatic;
    private Set<String> attributes = new HashSet<>();

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public void setAttributes(Set<String> attributes) {
        this.attributes = attributes;
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
