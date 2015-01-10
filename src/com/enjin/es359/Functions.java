package com.enjin.es359;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 10/11/14.
 */
public class Functions extends Inform {

    public Functions() {}

    static Functions finstance = new Functions();

    static public Functions getInstance() {
        return finstance;
    }

    SettingsManager sm = SettingsManager.getControllerInstance();

    Inform inform = new Inform();

    public void tp(Player target, Player sender) {
        Location l = target.getLocation();
        sender.teleport(l);
        sender.sendMessage(tpInform());
    }

    public void tphere(Player target, Player sender) {

        target.teleport(sender);
        sender.sendMessage(tphereInform());
        target.sendMessage(tphere2Inform() + target.getName());
    }

    public void kill(Player target, Player sender) {
        target.setHealth(0.0);
        target.sendMessage(killInform());
        sender.sendMessage(kill2Inform() +target.getName());
    }
    public void cr(Player target, Player sender) {
        target.setGameMode(GameMode.CREATIVE);
        target.sendMessage(playerCRInform());
        sender.sendMessage(adminCRInform() +target.getName());
    }
    public void su(Player target, Player sender) {
        target.setGameMode(GameMode.SURVIVAL);
        target.sendMessage(playerSUInform());
        sender.sendMessage(adminSUInform()+target.getName());
    }
    public void ad(Player target, Player sender) {
        target.setGameMode(GameMode.ADVENTURE);
        target.sendMessage(playerADInform());
        sender.sendMessage(adminADInform()+target.getName());
    }

    public void heal(Player target, Player sender) {
        target.setHealth(20.0);
        target.sendMessage(playerHealInform());
        sender.sendMessage(adminHealInform() +target.getName());
    }

    //+target.getName()
    public void enableFly(Player target, Player sender) {

        target.setAllowFlight(true);
        target.setFlying(true);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Set &bflight &7to &aEnabled &7for &3 ") + target.getName() +".");

    }
    public void disableFly(Player target,Player sender) {

        target.setAllowFlight(false);
        target.setFlying(false);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Set &bflight &7to &cDisabled &7for &3 ") + target.getName() + ".");
    }

    public void pday(Player target, Player sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix_Plugin+" &6&lThe &ePlayer &6time in the &bWorld: " + target.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oDay!")));

        target.setPlayerTime(1000, true);
    }

    public void pnight(Player target, Player sender) {
        target.setPlayerTime(13000, true);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin + " &6&lThe &ePlayer &6time in the &bWorld: " + target.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &9&oNight!")));
    }

    public void pSun(Player target, Player sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin + " &6&lThe &aPlayer &6&lWeather in the &bWorld: " + target.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oClear!")));

        target.setPlayerWeather(WeatherType.CLEAR);
    }

    public void pRain(Player target, Player sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin + " &6&lThe &aPlayer &6&lWeather in the &bWorld: " + target.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &7&oRainy!")));

        target.setPlayerWeather(WeatherType.DOWNFALL);
    }

    public void close(Player p) {
        p.closeInventory();
        return;
    }



}
