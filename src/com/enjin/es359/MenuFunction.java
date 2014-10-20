package com.enjin.es359;

import org.bukkit.entity.Player;

/**
 * Created by ES359 on 10/16/14.
 */
public interface MenuFunction {

    public void tp(Player target, Player sender);

    public void tphere(Player target, Player sender);

    public void kill(Player target, Player sender);

    public void cr(Player target, Player sender);

    public void su(Player target, Player sender);

    public void ad(Player target, Player sender);

    public void heal(Player target, Player sender);

    public void enableFly(Player target, Player sender);

    public void disableFly(Player target, Player sender);

    public void pday(Player target, Player sender);

    public void pnight(Player target, Player sender);

    public void psun(Player target, Player sender);

    public void pRain(Player target, Player sender);

    public void close(Player p) ;

}
