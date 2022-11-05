package me.slayz.player;

import me.slayz.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerHandler {

    public static List<PlayerHandler> players = new ArrayList<>();
    private Player player;
    private Location location;
    private final ItemStack[] contents,armor;

    private double health;
    private int hunger;

    private BukkitTask task;
    private List<Zombie> zombieslist = new ArrayList<>();


    public PlayerHandler(Player player){
        this.player = player;
        this.location = player.getLocation();
        this.contents = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();
        this.health = player.getHealth();
        this.hunger = player.getFoodLevel();

    }

    public void startArena(){
        Location l = getArenaLocation();
        if(l==null){
            player.sendMessage(ChatColor.RED+"Location hasn't been set in config, try to use /setlocation");
            return;
        }

        if(Utilities.isInArena(player)){
            player.sendMessage(ChatColor.RED+"You are already in the arena.");
            return;
        }

        player.getInventory().clear();

        ItemStack[] content = {new ItemStack(Material.IRON_SWORD), new ItemStack(Material.GOLDEN_APPLE,2)};
        ItemStack[] arrmor = {new ItemStack(Material.IRON_BOOTS),new ItemStack(Material.IRON_LEGGINGS)
                ,new ItemStack(Material.IRON_CHESTPLATE),new ItemStack(Material.IRON_HELMET)};

        player.getInventory().setContents(content);
        player.getInventory().setArmorContents(arrmor);


        player.teleport(l);
        player.setHealth(20);
        player.setFoodLevel(20);

        players.add(this);

        for(Player p : Bukkit.getOnlinePlayers()){
            p.hidePlayer(Main.getInstance(), player);
        }

        spawnZombies(player);

        Main.sql.updateSessions(player, Main.sql.getSessions(player)+1);

    }

    public void leaveArena(){
        if(!Utilities.isInArena(player)){
            player.sendMessage(ChatColor.RED+"You are not in the arena.");
            return;
        }

        player.teleport(location);
        player.setHealth(health);
        player.setFoodLevel(hunger);


        player.getInventory().setContents(this.contents);
        player.getInventory().setArmorContents(this.armor);


        players.remove(this);

        for(Player p : Bukkit.getOnlinePlayers()){
            p.showPlayer(Main.getInstance(), player);
        }

        task.cancel();
        for(Zombie z : zombieslist){
            z.remove();
        }

    }



    private Location getArenaLocation(){
        Main m = Main.getInstance();
        if(m.getConfig().contains("arena",false)) {
            double x = m.getConfig().getDouble("arena.x");
            double y = m.getConfig().getDouble("arena.y");
            double z = m.getConfig().getDouble("arena.z");
            String world = m.getConfig().getString("arena.world");


            return new Location(Bukkit.getWorld(world), x, y, z);
        }
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    private void spawnZombies(Player p){

        task = new BukkitRunnable() {

            int radius = 5;
            Random r = new Random();
            @Override
            public void run() {
                Location l = p.getLocation();

                int zombies = r.nextInt(3,10);
                for(int i=0; i<zombies; i++){
                    int x = r.nextInt(l.getBlockX()-radius,l.getBlockX()+radius);
                    int z = r.nextInt(l.getBlockZ()-radius,l.getBlockZ()+radius);

                    Location newLocation = l.getWorld().getHighestBlockAt(x,z).getLocation().add(0,1,0);

                    Zombie zombie = (Zombie) newLocation.getWorld().spawnEntity(newLocation, EntityType.ZOMBIE);
                    zombie.setGlowing(true);
                    zombie.setTarget(p);

                    for(Player on : Bukkit.getServer().getOnlinePlayers()) {
                        if(on != player)
                            on.hideEntity(Main.getInstance(),zombie);
                    }

                    zombieslist.add(zombie);
                }

            }
        }.runTaskTimer(Main.getInstance(), 0, 10*20);

    }

    public List<Zombie> getZombieslist() {
        return zombieslist;
    }
}
