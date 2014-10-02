package controller.commands;


import com.enjin.es359.Info;
import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class UserCPCommand implements CommandExecutor, Listener{



    SettingsManager sm = SettingsManager.getControllerInstance();
   // public Controller c;
    Inform inform = new Inform();
    Player target;

    /**
     * Need to upgrade how we do permissions.
     * Permission class, that returns all permissions.
     * @param sender
     * @param cmd
     * @param commandLabel
     * @param args
     * @return
     */

    public UserCPCommand(Plugin p) {
        Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }


    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(inform.ConsoleError());
            return true;
        }

       Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("usercp")) {
//            p.sendMessage(ChatColor.RED + "DEBUG MESSAGE TRYING TO TEST COMMAND");


            if(!p.hasPermission("Controller.cmd.usercp")) {
                p.sendMessage(inform.permissionError());
                return true;
            }else {

                if(args.length == 0) {
                    p.sendMessage(inform.ArgumentsError());
                    return true;
                }else if(args.length == 1) {

                  //  p.sendMessage(ChatColor.RED + "DEBUG MESSAGE TRYING TO TEST COMMAND");

                    target = Bukkit.getServer().getPlayer(args[0]);

                    if(target == null ) {
                        p.sendMessage(ChatColor.RED + "That player couldn't be targeted. Is the user = online?");
                        return true;
                    }else {
                        adminMenu(p);
                    }
                }
            }
        }
		
		return true;
	}

    private ItemStack IP, Location, Operator,Whitelist,UUID;
    public ItemStack tp,tphere,kill,cr,su,ad,heal,fly,pw,pt;
    public ItemStack Health;
    public ItemStack Name;
    public void adminMenu(Player p) {

        Inventory a = Bukkit.getServer().createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', "&c&oAdmin &6User&cCP &b> " + p.getName()));

        UUID = SettingsManager.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Player, " + target.getName() + "'s UUID: " + ChatColor.RED + target.getUniqueId());
        IP = SettingsManager.createItem(Material.DAYLIGHT_DETECTOR, ChatColor.GOLD + "Player, " + target.getName() + "'s IP address: " +ChatColor.BOLD + "" + ChatColor.GREEN + target.getAddress());
        Location = SettingsManager.createItem(Material.COMPASS, ChatColor.GOLD +"Player, " + target.getName() + "'s Location: " + ChatColor.GRAY +  + target.getLocation().getBlockX() +", " + ChatColor.BLUE +target.getLocation().getBlockY() + "" + ChatColor.GREEN + ", " + target.getLocation().getBlockZ());
        Operator = SettingsManager.createItem(Material.BEACON, ChatColor.DARK_RED + "Player, " + target.getName() + "'s Operator Status: " + ChatColor.RED + target.isOp());
        Whitelist = SettingsManager.createItem(Material.PAPER, ChatColor.YELLOW + "Player, " + target.getName() + "'s Whitelist Status: " + ChatColor.GOLD + target.isWhitelisted());
        Health = SettingsManager.createItem(Material.POTION, ChatColor.AQUA + "Player, " + target.getName() + "'s Health: " + ChatColor.DARK_AQUA + target.getHealthScale());
        Name = SettingsManager.createItem(Material.BOOK, ChatColor.DARK_GREEN + "Player, " + target.getName() + "'s Name: " + ChatColor.AQUA + target.getName());

        tp = SettingsManager.createItem(Material.COMPASS, ChatColor.GOLD + "Teleport to the target Player.");
        tphere = SettingsManager.createItem(Material.LEASH, ChatColor.translateAlternateColorCodes('&', "&eTeleport the target player to you."));
        kill = SettingsManager.createItem(Material.DIAMOND_SWORD, ChatColor.RED + "Kills the target Player");
        cr = SettingsManager.createItem(Material.DIAMOND, ChatColor.AQUA + "Changes the targets gamemode to Creative.");
        su = SettingsManager.createItem(Material.GRASS, ChatColor.DARK_GREEN + "Changes the targets gamemode to Survival.");
        ad = SettingsManager.createItem(Material.COMMAND, ChatColor.GOLD + "Changes the targets Gamemode to Adventure.");
        heal = SettingsManager.createItem(Material.GOLDEN_APPLE, ChatColor.DARK_GREEN + "Heals the target Player");
        fly = SettingsManager.createItem(Material.BEACON, ChatColor.translateAlternateColorCodes('&', "&bEnables the players &6Fly &cMode."));
        pw = SettingsManager.createItem(Material.DAYLIGHT_DETECTOR, ChatColor.translateAlternateColorCodes('&', "&bSets the &e&oPlayer weather to &cclear &6or &9rainy"));
        pt = SettingsManager.createItem(Material.WATCH, ChatColor.translateAlternateColorCodes('&', "&bSets the &e&oPlayer time to &cday &6or &9night"));

        a.setItem(9, tp);
        a.setItem(10,tphere);
        a.setItem(11, cr);
        a.setItem(12, su);
        a.setItem(13, ad);
        a.setItem(14, heal);
        a.setItem(15, fly);
        a.setItem(16, pw);
        a.setItem(17, pt);
        a.setItem(18, kill);

        a.setItem(0, UUID);
        a.setItem(1, IP);
        a.setItem(2, Location);
        a.setItem(3, Operator);
        a.setItem(4, Whitelist);
        a.setItem(5, Health);
        a.setItem(6, Name);
        a.setItem(35, SettingsManager.closeMenuItem());


        p.openInventory(a);
    }

    /**
     *
     */

    private void tp() {
        //Add permission checks here later.

        Location l = target.getLocation();
        pclick.teleport(l);
        pclick.sendMessage(inform.tp() +target.getName());
    }

//Method complete.


    private void tphere() {

//Add permission checks here later.

        target.teleport(pclick);
        pclick.sendMessage(inform.tp() +target.getName());
        target.sendMessage(inform.tphere());
    }

    private void kill() {
        target.setHealth(0.0);
        target.sendMessage(inform.kill());
        pclick.sendMessage(inform.kill2() + target.getName());
    }

    private void cr() {

        //Add permission checks here later.

        target.setGameMode(GameMode.CREATIVE);
        target.sendMessage(inform.playerCR());
        pclick.sendMessage(inform.adminCR() + target.getName());
    }

    private void su() {
        target.setGameMode(GameMode.SURVIVAL);
        target.sendMessage(inform.playerSU());
        pclick.sendMessage(inform.adminSU() + target.getName());
    }

    private void ad() {
        target.setGameMode(GameMode.ADVENTURE);
        target.sendMessage(inform.playerAD());
        pclick.sendMessage(inform.adminAD() + target.getName());
    }

    private void heal() {
        target.setHealth(20.0);
        target.sendMessage(inform.playerHeal());
        pclick.sendMessage(inform.adminHeal() + target.getName());
    }

    private void enableFly() {
        target.setAllowFlight(true);
        target.setFlying(true);
        pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7You have set fly mode to &aTrue&7, for &c," +target.getName()));

    }

    private void disableFly() {
        target.setAllowFlight(false);
        target.setFlying(false);
        pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7You have set fly mode to &4False&7, for &c," +target.getName()));
    }

    private void pday() {
        pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &6&lThe &ePlayer &6time in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oDay!")));

        target.setPlayerTime(1000, true);
    }

    private void pnight() {
        target.setPlayerTime(13000, true);
        pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &6&lThe &ePlayer &6time in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &9&oNight!")));
    }

    private void pSun() {
        pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &6&lThe &aPlayer &6&lWeather in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oClear!")));

        target.setPlayerWeather(WeatherType.CLEAR);
    }

    private void pRain() {
        pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &6&lThe &aPlayer &6&lWeather in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &7&oRainy!")));

        target.setPlayerWeather(WeatherType.DOWNFALL);
    }


    public void close()
    {
        pclick.closeInventory();
        return;
    }



    Player pclick;
    @EventHandler
    public void invListener(InventoryClickEvent event) {

        pclick = (Player)event.getWhoClicked();

      if(event.getCurrentItem().equals(SettingsManager.closeMenuItem())) {
          if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
              event.setCancelled(true);

              SettingsManager.closeMenu(pclick);

          }
      }

        if(event.getCurrentItem().equals(IP) || event.getCurrentItem().equals(Health) || event.getCurrentItem().equals(Location) || event.getCurrentItem().equals(Operator) || event.getCurrentItem().equals(Whitelist) || event.getCurrentItem().equals(UUID) || event.getCurrentItem().equals(Name))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
            }
        }


        if(event.getCurrentItem().equals(tp)) {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {

                event.setCancelled(true);

                tp();

            }
         }


        if(event.getCurrentItem().equals(tphere)) {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
                event.setCancelled(true);

                tphere();
            }
        }


        if(event.getCurrentItem().equals(cr))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                cr();
                return;
            }
            return;
        }
        if(event.getCurrentItem().equals(su))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                su();
                return;
            }
            return;
        }
        if(event.getCurrentItem().equals(ad))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                ad();
                return;
            }
            return;
        }

        if(event.getCurrentItem().equals(heal))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                heal();
            }
        }

        if(event.getCurrentItem().equals(fly)){
            if(event.isShiftClick()) {
                event.setCancelled(true);
            }else if(event.isRightClick()) {
                event.setCancelled(true);
                enableFly();
                close();
                return;
            }else if(event.isLeftClick()) {
                event.setCancelled(true);
                disableFly();
                close();
            }
        }

        if(event.getCurrentItem().equals(pt)) {
            if(event.isShiftClick()) {
                event.setCancelled(true);
            }else {
                if(event.isRightClick()) {
                    event.setCancelled(true);
                    pday();
                    close();
                   // pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &ePlayer &6time in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oDay!")));
                    return;
                }else if(event.isLeftClick()) {
                    event.setCancelled(true);
                    pnight();
                    close();
                   // pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &ePlayer &6time in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &9&oNight!")));
                    return;
                }
            }
        }

        if(event.getCurrentItem().equals(pw)) {
            if(event.isShiftClick()) {
                event.setCancelled(true);
            }else {
                if(event.isRightClick()) {
                    event.setCancelled(true);
                    pSun();
                    close();
                    //pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &aPlayer &6&lWeather in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oClear!")));
                    return;
                }else if(event.isLeftClick()) {
                    event.setCancelled(true);
                    pRain();
                    close();
                    //pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &aPlayer &6&lWeather in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &7&oRainy!")));
                    return;
                }
            }
        }

           if(event.getCurrentItem().equals(kill)) {
               if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
                   event.setCancelled(true);
                   kill();
               }
           }
    }
}

