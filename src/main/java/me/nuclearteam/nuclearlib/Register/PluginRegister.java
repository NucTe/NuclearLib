package me.nuclearteam.nuclearlib.Register;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginRegister extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final JavaPlugin plugin;

    public PluginRegister(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
