package com.qbasty.oacommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ping implements CommandExecutor
{
    main plugin;
    private Method getHandleMethod;
    private Field pingField;
    boolean updated;

    public ping(final main plugin) {
        this.plugin = plugin;
    }

    public int getPing(final Player player) {
        try {
            if (this.getHandleMethod == null) {
                (this.getHandleMethod = player.getClass().getDeclaredMethod("getHandle", (Class<?>[])new Class[0])).setAccessible(true);
            }
            final Object entityPlayer = this.getHandleMethod.invoke(player, new Object[0]);
            if (this.pingField == null) {
                (this.pingField = entityPlayer.getClass().getDeclaredField("ping")).setAccessible(true);
            }
            final int ping = this.pingField.getInt(entityPlayer);
            return (ping > 0) ? ping : 0;
        }
        catch (Exception e) {
            return 1;
        }
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    final Player player = (Player)sender;
                    String pingMex = this.plugin.getConfig().getString("PingMSG");
                    if (pingMex.contains("%ping%")) {
                        pingMex = pingMex.replace("%ping%", new StringBuilder().append(this.getPing(player)).toString());
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', pingMex));
                }
                else {
                    sender.sendMessage("§cYou have to be a player to use this command.");
                }
                return true;
            }
            if (args.length == 1) {
                if (Bukkit.getServer().getPlayer(args[0]) != null) {
                    final Player target = Bukkit.getServer().getPlayer(args[0]);
                    String pingMex = this.plugin.getConfig().getString("PingMSGOther");
                    if (pingMex.contains("%u%")) {
                        pingMex = pingMex.replace("%u%", target.getName());
                    }
                    if (pingMex.contains("%ping%")) {
                        pingMex = pingMex.replace("%ping%", new StringBuilder().append(this.getPing(target)).toString());
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pingMex));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("PingInvalid")));
                }
                return true;
            }
        }
        return false;
    }
}
