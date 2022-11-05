package me.slayz.player;

import org.bukkit.entity.Player;

public class Utilities {

    public static boolean isInArena(Player target){
        for(PlayerHandler p : PlayerHandler.players){
            if(p.getPlayer() == target){
                return true;
            }
        }
        return false;
    }

    public static PlayerHandler getPlayerHandler(Player target){
        for(PlayerHandler p : PlayerHandler.players){
            if(p.getPlayer() == target){
                return p;
            }
        }
        return null;
    }
}
