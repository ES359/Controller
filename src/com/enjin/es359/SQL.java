package com.enjin.es359;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ES359 on 9/21/14.
 */
public class SQL
{

    Inform i = new Inform();

    public Connection c;

    public SQL(String ip, String userName, String access, String db) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().getConsoleSender().sendMessage(i.connectionError());
        }
    }
}
