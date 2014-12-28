package controller.commands;


import com.enjin.es359.Functions;
import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;

import controller.events.DisconnectPlayer;
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

public class UserCPCommand extends Inform implements CommandExecutor, Listener{



    SettingsManager sm = SettingsManager.getControllerInstance();

  Functions f = Functions.getInstance();

   // public Controller c;
    Inform inform = new Inform();
    Player target;


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

            if(!p.hasPermission("Controller.cmd.cp")) {
                p.sendMessage(permissionError());
            }else {

                if(args.length == 0) {
                    p.sendMessage(returnArgumentError());
                }else if(args.length == 1) {
                     target = Bukkit.getServer().getPlayer(args[0]);

                    if(target == null) {
                        p.sendMessage(playerNotFound());
                        return true;
                    }else {
                        adminMenu(p);
                        return true;
                    }
                }
            }
        }
		return true;
	}

    private ItemStack IP, Location, Operator,Whitelist,UUID;
    public ItemStack tp,tphere,kill,cr,su,ad,heal,fly,pw,pt,disconnect;
    public ItemStack Health;
    public ItemStack Name;
    public void adminMenu(Player p) {

        Inventory a = Bukkit.getServer().createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', "&cCP &b> " + target.getName()));

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
        disconnect = SettingsManager.createItem(Material.ACTIVATOR_RAIL, ChatColor.translateAlternateColorCodes('&', "&6Disconnects &cyou from the server."));



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
        a.setItem(19,disconnect);

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

    private DisconnectPlayer dp = new DisconnectPlayer();

    Player pclick;
    @EventHandler
    public void invListener(InventoryClickEvent event) {

        pclick = (Player)event.getWhoClicked();


        if(event.getCurrentItem().equals(disconnect)) {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
                event.setCancelled(true);
                dp.disconnect(pclick);
            }
        }

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

                f.tp(target, pclick);

            }
         }


        if(event.getCurrentItem().equals(tphere)) {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
                event.setCancelled(true);

                f.tphere(target,pclick);
            }
        }


        if(event.getCurrentItem().equals(cr))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                f.cr(target, pclick);
                return;
            }
            return;
        }
        if(event.getCurrentItem().equals(su))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                f.su(target, pclick);
                return;
            }
            return;
        }
        if(event.getCurrentItem().equals(ad))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                f.ad(target, pclick

                );
                return;
            }
            return;
        }

        if(event.getCurrentItem().equals(heal))
        {
            if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
            {
                event.setCancelled(true);
                f.heal(target, pclick);
            }
        }

        if(event.getCurrentItem().equals(fly)){
            if(event.isShiftClick()) {
                event.setCancelled(true);
            }else if(event.isRightClick()) {
                event.setCancelled(true);
                f.enableFly(target, pclick);
                f.close(pclick);
                return;
            }else if(event.isLeftClick()) {
                event.setCancelled(true);
                f.disableFly(target, pclick);
                f.close(pclick);
            }
        }

        if(event.getCurrentItem().equals(pt)) {
            if(event.isShiftClick()) {
                event.setCancelled(true);
            }else {
                if(event.isRightClick()) {
                    event.setCancelled(true);
                    f.pday(target, pclick);
                    f.close(pclick);
                   // pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &ePlayer &6time in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oDay!")));
                    return;
                }else if(event.isLeftClick()) {
                    event.setCancelled(true);
                    f.pnight(target, pclick);
                    f.close(pclick);
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
                   f.pSun(target, pclick);
                    f.close(pclick);
                    //pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &aPlayer &6&lWeather in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oClear!")));
                    return;
                }else if(event.isLeftClick()) {
                    event.setCancelled(true);
                    f.pRain(target, pclick);
                    f.close(pclick);
                    //pclick.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lThe &aPlayer &6&lWeather in the &bWorld: " + pclick.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &7&oRainy!")));
                    return;
                }
            }
        }

           if(event.getCurrentItem().equals(kill)) {
               if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
                   event.setCancelled(true);
                   f.kill(target, pclick);
               }
           }
       }
}

