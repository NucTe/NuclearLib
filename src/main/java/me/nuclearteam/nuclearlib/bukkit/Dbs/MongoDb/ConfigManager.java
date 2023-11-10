package me.nuclearteam.nuclearlib.bukkit.Dbs.MongoDb;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private final JavaPlugin plugin;

    public ConfigManager(JavaPlugin plugin) {
        /*
          gets the config.yml
          you will have to add to you config file
              mongodb:
                   host: ""
                   port: 25565
                   username: ""
                   password: ""
          **/
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
    }

    public String getMongoDBConnectionString() {
        FileConfiguration config = plugin.getConfig();
        String host = config.getString("mongodb.host");
        int port = config.getInt("mongodb.port");
        String username = config.getString("mongodb.username");
        String password = config.getString("mongodb.password");

        return String.format("mongodb://%s:%s@%s:%d", username, password, host, port);
    }
}

