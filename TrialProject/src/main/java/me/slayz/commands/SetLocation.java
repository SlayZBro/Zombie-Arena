package me.slayz.commands;

import me.slayz.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLocation implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;


            if(p.isOp()){
                Location l = p.getLocation();

                Main instance = Main.getInstance();

                double x = l.getX(), y=l.getY(), z= l.getZ();
                String world = l.getWorld().getName();

                instance.getConfig().set("arena.x",x);
                instance.getConfig().set("arena.y",y);
                instance.getConfig().set("arena.z",z);
                instance.getConfig().set("arena.world",world);

                instance.saveConfig();

                p.sendMessage(ChatColor.YELLOW+"Arena location saved.");
            }
        }

        return true;
    }
}
