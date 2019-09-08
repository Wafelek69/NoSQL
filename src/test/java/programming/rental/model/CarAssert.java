package programming.rental.model;


import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class CarAssert extends AbstractAssert<CarAssert, Car> {
    private CarAssert(Car car) {
        super(car, CarAssert.class);
    }

    public static CarAssert assertThat(Car actual) {
        return new CarAssert(actual);
    }

    public CarAssert hasId(long id) {
        isNotNull();

        if (actual.getId() != id) {
            failWithMessage("Expected car's id to be <%s> but was <%s>", id, actual.getId());
        }

        return this;
    }

    public CarAssert hasProducer(String producer) {
        isNotNull();

        if (!Objects.equals(actual.getProducer(), producer)) {
            failWithMessage("Expected car's producer to be <%s> but was <%s>", producer, actual.getProducer());
        }

        return this;
    }

    public CarAssert hasModel(String model) {
        isNotNull();

        if (!Objects.equals(actual.getModel(), model)) {
            failWithMessage("Expected car's model to be <%s> but was <%s>", model, actual.getModel());
        }

        return this;
    }

    public CarAssert hasColour(String colour) {
        isNotNull();

        if (!Objects.equals(actual.getColour(), colour)) {
            failWithMessage("Expected car's colour to be <%s> but was <%s>", colour, actual.getColour());
        }

        return this;
    }

    public CarAssert hasPrice(BigDecimal price) {
        isNotNull();

        if (!Objects.equals(actual.getPrice(), price)) {
            failWithMessage("Expected car's price to be <%s> but was <%s>", price, actual.getPrice());
        }

        return this;
    }

    public CarAssert hasAttributes(String... attributes) {
        isNotNull();

        if (actual.getAttributes().containsAll(Arrays.asList(attributes))) {
            failWithMessage("Expected car's attributes to be <%s> but was <%s>", attributes, actual.getAttributes());
        }

        return this;
    }

    public CarAssert isManual() {
        isNotNull();

        if (actual.isAutomatic()) {
            failWithMessage("Expected car's automatic to be <%s> but was <%s>", false, actual.isAutomatic());
        }

        return this;

    }

    public CarAssert isAutomatic() {
        isNotNull();

        if (!actual.isAutomatic()) {
            failWithMessage("Expected car's automatic to be <%s> but was <%s>", true, actual.isAutomatic());
        }

        return this;

    }
}
