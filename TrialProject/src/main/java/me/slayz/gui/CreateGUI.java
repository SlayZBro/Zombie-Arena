package me.slayz.gui;

import me.slayz.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CreateGUI {

    private static ItemStack createItem(Inventory inv, Material mat, int ammount, int invslot, String displayName,
                                String... lorelist) {
        ItemStack item;
        List<String> lore = new ArrayList<>();
        item = new ItemStack(mat, ammount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        for (String a : lorelist)
            lore.add(a);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invslot - 1, item);

        return item;
    }

    public static void statsPage(Player p) {
        int kills = Main.sql.getKills(p);
        int deaths = Main.sql.getDeaths(p);
        int sessions = Main.sql.getSessions(p);



        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Stats");
        createItem(inv, Material.DIAMOND_SWORD, 1, 3, ChatColor.YELLOW + "KILLS", ChatColor.YELLOW+"You have "
                    + kills+" Kills");
        createItem(inv, Material.DIAMOND_HELMET, 1, 4, ChatColor.YELLOW + "DEATHS", ChatColor.YELLOW+"You have "
                + deaths+" Deaths");
        createItem(inv, Material.DIAMOND_CHESTPLATE, 1, 5, ChatColor.YELLOW + "SESSIONS", ChatColor.YELLOW+"You have "+
                sessions+" Sessions");

        deaths = Main.sql.getDeaths(p) == 0 ? 1 : Main.sql.getDeaths(p);
        sessions = Main.sql.getSessions(p) == 0 ? 1 : Main.sql.getSessions(p);
        createItem(inv, Material.BARRIER, 1, 6, ChatColor.YELLOW + "AVERAGE", ChatColor.YELLOW+"K/D: "+((double)kills/(double)deaths),
                ChatColor.YELLOW+"K/S: "+(((double)kills/(double)sessions)));

        p.openInventory(inv);
    }
}
