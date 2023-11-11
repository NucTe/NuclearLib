package me.nuclearteam.nuclearlib.Register;

import me.nuclearteam.nuclearlib.Nuclearlib;
import me.nuclearteam.nuclearlib.Register.PluginRegister;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class PluginRegistry {

    private final Set<JavaPlugin> registeredPlugins;

    public PluginRegistry() {
        this.registeredPlugins = new HashSet<>();
    }

    public void registerPlugin(JavaPlugin plugin) {
        registeredPlugins.add(plugin);
        Nuclearlib.getInstance().getServer().getPluginManager().callEvent(new PluginRegister(plugin));
    }

    public void unregisterPlugin(JavaPlugin plugin) {
        registeredPlugins.remove(plugin);
        Nuclearlib.getInstance().getLogger().info("Plugin unregistered: " + plugin.getName());
    }

    public Set<JavaPlugin> getRegisteredPlugins() {
        return new HashSet<>(registeredPlugins);
    }
}

