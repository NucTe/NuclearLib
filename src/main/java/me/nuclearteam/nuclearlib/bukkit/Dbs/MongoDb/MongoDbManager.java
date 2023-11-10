package me.nuclearteam.nuclearlib.bukkit.Dbs.MongoDb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDbManager {
    /*
    * Starts the connection to your mongodb database
    * **/
    private static String DATABASE_NAME;

    public MongoDbManager(String DATABASE_NAME){
        this.DATABASE_NAME = DATABASE_NAME;
    }


    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect(String connectionString) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}

