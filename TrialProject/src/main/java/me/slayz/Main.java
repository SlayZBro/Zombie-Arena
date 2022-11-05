package me.slayz;

import me.slayz.commands.Leave;
import me.slayz.commands.SetLocation;
import me.slayz.commands.Start;
import me.slayz.commands.Stats;
import me.slayz.events.Death;
import me.slayz.events.InventoryClick;
import me.slayz.events.Join;
import me.slayz.events.Kill;
import me.slayz.sql.MySQLProperties;
import me.slayz.sql.MySQLSetup;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    public static MySQLProperties sql;
    private MySQLSetup m;

    @Override
    public void onEnable() {
        setInstance(this);



        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Plugin started!");
        getConfig().options().copyDefaults(true);
        saveConfig();

        m = new MySQLSetup();

        m.setupSQL();

        sql = new MySQLProperties(m);

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        m.closeConnection();
    }

    void registerCommands(){
        getCommand("leave").setExecutor(new Leave());
        getCommand("start").setExecutor(new Start());
        getCommand("stats").setExecutor(new Stats());
        getCommand("setlocation").setExecutor(new SetLocation());
    }

    void registerEvents(){
        getServer().getPluginManager().registerEvents(new Death(),this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Kill(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    }

    private void setInstance(Main main){
        instance = main;
    }

    public static Main getInstance(){
        return instance;
    }
}
