package me.slayz.commands;

import me.slayz.Main;
import me.slayz.player.PlayerHandler;
import me.slayz.player.Utilities;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Start implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (!Utilities.isInArena(p)) {
                PlayerHandler playerHandler = new PlayerHandler(p);

                new BukkitRunnable() {
                    int time = 5;

                    @Override
                    public void run() {
                        if (this.time == 0) {
                            playerHandler.startArena();
                            this.cancel();
                        } else {
                            playerHandler.getPlayer().sendMessage(ChatColor.YELLOW + "You will be teleported in " + time);

                            this.time--;
                        }
                    }
                }.runTaskTimer(Main.getInstance(), 0, 20);
            }else{
                p.sendMessage(ChatColor.RED+"You are already in the arena.");
            }
        }

        return true;
    }


}
