package me.twostinkysocks.customenchants;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CustomEnchants extends JavaPlugin implements CommandExecutor {

    public static CustomEnchants instance = null;

    @Override
    public void onEnable() {
        instance = this;
        File configFile = new File(getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        getServer().getPluginManager().registerEvents(new FeatherFalling(), this);
        getServer().getPluginManager().registerEvents(new Speed(), this);
        getServer().getPluginManager().registerEvents(new Mending(), this);
        getServer().getPluginManager().registerEvents(new Thorns(), this);
        getServer().getPluginManager().registerEvents(new Knockback(), this);
        getServer().getPluginManager().registerEvents(new Respiration(), this);
        getServer().getPluginManager().registerEvents(new Punch(), this);
        getServer().getPluginCommand("reloadcustomenchants").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equals("reloadcustomenchants")) {
            reloadConfig();
            sender.sendMessage(ChatColor.AQUA + "Reloaded!");
        }
        return true;
    }




}
