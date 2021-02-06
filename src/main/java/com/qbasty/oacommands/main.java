package com.qbasty.oacommands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
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