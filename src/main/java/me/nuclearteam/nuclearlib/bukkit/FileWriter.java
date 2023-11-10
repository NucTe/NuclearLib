package me.nuclearteam.nuclearlib.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    private final JavaPlugin plugin;

    public FileWriter(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void writeToFileAsync(String fileName, String content) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            File file = new File(plugin.getDataFolder(), fileName);

            try {
                // Ensure the parent directory exists
                file.getParentFile().mkdirs();

                // Write content to file asynchronously
                Files.write(file.toPath(), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
