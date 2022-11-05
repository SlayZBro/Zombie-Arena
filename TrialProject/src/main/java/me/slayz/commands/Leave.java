package me.slayz.commands;

import me.slayz.player.PlayerHandler;
import me.slayz.player.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;


            PlayerHandler player = Utilities.getPlayerHandler(p);
            if(player != null) {
                player.leaveArena();
                return true;
            }

            p.sendMessage(ChatColor.RED+"You are not in the arena");
        }

        return true;
    }
}
