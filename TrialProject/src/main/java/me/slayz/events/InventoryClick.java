package me.slayz.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void click(InventoryClickEvent e){
        if(e.getCurrentItem().hasItemMeta()){
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.YELLOW+"")){
                e.setCancelled(true);
            }
        }
    }
}
