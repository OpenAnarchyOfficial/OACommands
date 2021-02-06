package com.qbasty.oacommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class text implements CommandExecutor
{

    main plugin;
    public text(final main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("discord")) {
            if (this.plugin.getConfig().getString("DiscordLink") == null) {
                sender.sendMessage(ChatColor.GREEN + "Join our discord - link");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("DiscordLink")));
            }
        }
        if (cmd.getName().equalsIgnoreCase("donate")) {
            if (this.plugin.getConfig().getString("DonationLink") == null) {
                sender.sendMessage(ChatColor.GREEN + "Donate here - link");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("DonationLink")));
            }
        }
        if (cmd.getName().equalsIgnoreCase("help")) {
            if (this.plugin.getConfig().getString("other.help") == null) {
                sender.sendMessage(ChatColor.GREEN + "Help soon!");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("other.help")));
            }
        }
        return false;
    }
}
