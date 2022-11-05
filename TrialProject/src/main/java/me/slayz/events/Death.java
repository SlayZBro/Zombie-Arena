package me.slayz.events;

import me.slayz.Main;
import me.slayz.player.PlayerHandler;
import me.slayz.player.Utilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    @EventHandler
    public void onDeath(EntityDamageEvent e){

        if(e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();
            if(p.getHealth() - e.getDamage() < 1 && Utilities.isInArena(p)) {
                PlayerHandler player = Utilities.getPlayerHandler(p);
                e.setCancelled(true);
                player.leaveArena();

                Main.sql.updateDeaths(p,Main.sql.getDeaths(p)+1);
            }
        }

    }

}
