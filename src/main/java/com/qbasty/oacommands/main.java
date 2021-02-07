package com.qbasty.oacommands;

import com.google.gson.Gson;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class main extends JavaPlugin {

    public static File dataFolder;
    public static Gson gson = new Gson();
    public static main INSTANCE;

    @Override
    public void onEnable() {

        INSTANCE = this;
        saveDefaultConfig();
        dataFolder = this.getDataFolder();

        // Plugin startup logic
        this.getCommand("discord").setExecutor((CommandExecutor)new text(this));
        this.getCommand("donate").setExecutor((CommandExecutor)new text(this));
        this.getCommand("help").setExecutor((CommandExecutor)new text(this));
        this.getCommand("ping").setExecutor((CommandExecutor)new ping(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}