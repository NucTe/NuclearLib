package me.nuclearteam.nuclearlib;

import me.nuclearteam.nuclearlib.Register.PluginRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class Nuclearlib extends JavaPlugin {

    private static Nuclearlib instance;
    private PluginRegistry pluginRegistry;

    @Override
    public void onEnable() {
        instance = this;
        pluginRegistry = new PluginRegistry();

        // Register this library as a plugin
        pluginRegistry.registerPlugin(this);

        // Other initialization logic
    }

    @Override
    public void onDisable() {
        // Unregister this library as a plugin
        pluginRegistry.unregisterPlugin(this);

        // Other library shutdown logic
    }

    public static Nuclearlib getInstance() {
        return instance;
    }

    public PluginRegistry getPluginRegistry() {
        return pluginRegistry;
    }
}
