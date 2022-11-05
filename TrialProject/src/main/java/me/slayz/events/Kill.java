package me.slayz.events;

import me.slayz.Main;
import me.slayz.player.Utilities;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Kill implements Listener {

    @EventHandler
    public void kill(EntityDeathEvent e){
        Entity entity = e.getEntity();
        if(entity instanceof Zombie && ((Zombie) entity).getKiller() instanceof Player){
            Player p = ((Zombie) entity).getKiller();
            if(Utilities.isInArena(p)){
                Main.sql.updateKills(p,Main.sql.getKills(p)+1);
            }
        }
    }
}
