package nosql.servive;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import programming.rental.model.CarPojo;


public class CarsService implements AutoCloseable {

    private MongoClient mongoClient;

    CarsService() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        mongoClient = MongoClients.create(settings);
        initCollection();
    }

    private void initCollection() {
        MongoCollection<Document> collection = mongoClient.getDatabase("cars").getCollection("cars");
        IndexOptions indexOptions = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("carId"), indexOptions);
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    MongoCollection<Document> getCollection(String dbName, String collectionName) {
        MongoDatabase db = mongoClient.getDatabase(dbName);
        return db.getCollection(collectionName);
    }

    MongoCollection<CarPojo> getCollection() {
        MongoDatabase db = mongoClient.getDatabase("cars");
        return db.getCollection("cars", CarPojo.class);
    }
}