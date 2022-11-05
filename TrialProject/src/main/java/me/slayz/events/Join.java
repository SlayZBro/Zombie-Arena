package me.slayz.events;

import me.slayz.Main;
import me.slayz.player.PlayerHandler;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e){
        if(!Main.sql.isPlayerExists(e.getPlayer())){
            Main.sql.createPlayer(e.getPlayer());
        }

        for(PlayerHandler p : PlayerHandler.players){
            if(p.getPlayer() != e.getPlayer()) {
                for (Zombie z : p.getZombieslist()) {
                    e.getPlayer().hideEntity(Main.getInstance(), z);
                }
            }
            e.getPlayer().hidePlayer(Main.getInstance(),p.getPlayer());
        }
    }
}
