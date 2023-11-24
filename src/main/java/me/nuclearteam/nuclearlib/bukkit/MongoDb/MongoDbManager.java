package me.nuclearteam.nuclearlib.bukkit.MongoDb;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MongoDbManager {
    private final JavaPlugin plugin;
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> coll;
    private String collection;

    private static final Logger logger = Logger.getLogger(MongoDbManager.class.getName());

    public MongoDbManager(JavaPlugin plugin, String collection) {
        this.plugin = plugin;
        this.collection = collection;
        this.plugin.saveDefaultConfig();
    }

    public void connect(String connectionString) {
        try {
            FileConfiguration config = plugin.getConfig();
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase(config.getString("mongodb.database"));
            coll = database.getCollection(collection);
        } catch (Exception e) {
            // Handle connection errors (log or throw an exception)
            e.printStackTrace();
            logger.severe("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public void setCollection(String newCollection) {
        this.collection = newCollection;
    }

    public void insertDocument(Document document) {
        coll.insertOne(document);
    }

    public FindIterable<Document> findDocuments(String key, String value) {
        return coll.find(Filters.eq(key, value));
    }

    public void insertDocumentWithList(String key, List<String> values) {
        Document document = new Document("key", key)
                .append("listField", values);
        coll.insertOne(document);
    }

    public void appendToList(String key, String value) {
        Bson filter = Filters.eq("key", key);
        Bson update = new Document("$push", new Document("listField", value));
        coll.updateOne(filter, update);
    }

    public List<String> readList(String key) {
        Bson filter = Filters.eq("key", key);
        FindIterable<Document> result = coll.find(filter);

        List<String> list = new ArrayList<>();
        for (Document document : result) {
            List<String> values = document.getList("listField", String.class);
            if (values != null) {
                list.addAll(values);
            }
        }

        return list;
    }

    public void updateDocument(String key, String fieldToUpdate, String newValue) {
        Bson filter = Filters.eq("key", key);
        Bson update = new Document("$set", new Document(fieldToUpdate, newValue));
        coll.updateOne(filter, update);
    }

    public void deleteDocument(String key, String value) {
        Bson filter = Filters.eq("key", key);
        coll.deleteOne(filter);
    }

    public void createIndex(String field) {
        coll.createIndex(new Document(field, 1));
    }

    public List<Document> runAggregationPipeline(List<Bson> pipeline) {
        return coll.aggregate(pipeline).into(new ArrayList<>());
    }

    public void insertDocuments(List<Document> documents) {
        coll.insertMany(documents);
    }


}

