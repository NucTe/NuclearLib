package me.nuclearteam.nuclearlib.Bungee;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    private final Plugin plugin;

    public FileWriter(Plugin plugin) {
        this.plugin = plugin;
    }

    public void writeToFileAsync(String fileName, String content) {
        plugin.getProxy().getScheduler().runAsync(plugin, () -> {
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
