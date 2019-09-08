package nosql.servive;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import programming.rental.model.CarPojo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class CarsServiceTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldFindCarsCollection() {
        try (CarsService carsService = new CarsService()) {

            MongoCollection<Document> carsCollection = carsService.getCollection("cars", "cars");

            assertThat(carsCollection).isNotNull();
        }
    }

    @Test
    void shouldAddCar() {
        try (CarsService carsService = new CarsService()) {

            MongoCollection<Document> carsCollection = carsService.getCollection("cars", "cars");
            long before = 0; //carsCollection.countDocuments();
            carsCollection.deleteMany(new Document());
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("id", 1);
            attributes.put("producer", "Opel");
            attributes.put("model", "Astra");
            attributes.put("colour", "Blue");
            attributes.put("price", 10.0);
            attributes.put("automatic", true);
            attributes.put("attributes", Collections.singleton("metalic, PETROL"));

            Document document = new Document(attributes);

            carsCollection.insertOne(document);

            assertThat(carsCollection.countDocuments()).isEqualTo(before + 1);
        }
    }

    @Test
    void shouldAddCarViaPojo() {
        try (CarsService carsService = new CarsService()) {

            MongoCollection<CarPojo> carsCollection = carsService.getCollection();
            long before = 0;//carsCollection.countDocuments();
            carsCollection.deleteMany(new Document());
            CarPojo carPojo = new CarPojo();
            carPojo.setCarId(3);
            carPojo.setProducer("Opel");
            carPojo.setModel("Astra");

            carsCollection.insertOne(carPojo);

            carPojo = new CarPojo();
            carPojo.setCarId(4);
            carPojo.setProducer("Opel");
            carPojo.setModel("Astra");

            carsCollection.insertOne(carPojo);

            assertThat(carsCollection.countDocuments()).isEqualTo(before + 2);
        }
    }

    @Test
    void shouldLoadManyCar() {

        List<CarPojo> cars = loadCars();
        assertThat(cars.size()).isEqualTo(8);

        try (CarsService carsService = new CarsService()) {

            MongoCollection<CarPojo> carsCollection = carsService.getCollection();
            carsCollection.deleteMany(new Document());
            carsCollection.insertMany(cars);

            assertThat(carsCollection.countDocuments()).isEqualTo(8);

            Document filterCar = new Document("carId", 4);
            FindIterable<CarPojo> foundCar = carsCollection.find(filterCar);

            assertThat(foundCar).isNotEmpty();

        }

    }

    private List<CarPojo> loadCars() {
        try (InputStream fileStream = getClass().getClassLoader().getResourceAsStream("cars.json")) {

            return mapper.readValue(fileStream, new TypeReference<List<CarPojo>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}