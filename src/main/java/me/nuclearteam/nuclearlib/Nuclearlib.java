package me.nuclearteam.nuclearlib;

import org.bukkit.plugin.java.JavaPlugin;

public final class Nuclearlib extends JavaPlugin {

    private static Nuclearlib instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

    public static Nuclearlib getInstance() {
        return instance;
    }

}
