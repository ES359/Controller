package com.enjin.es359;

import controller.commands.*;
import controller.events.BlockListener;
import controller.events.CPMenuEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 9/10/14.
 */
public class Permissions{



    public void sendPermissionInfo(CommandSender p) {

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                "" +
            "&6&lCommand Permissions&c:\n" +
                "&n&3====================\n" +
                "&3&oController.cmd.alert &c&l- &aAccess to /alert <msg>\n"
                +"&3&oController.cmd.chat &c&l- &aAccess to /chat <enabled || disabled>\n"
                +"&3&oController.cmd.setkickmsg &c&l- &aAccess to /setkickmsg <msg>\n" +
                  "&3&oController.cmd.setlistmotd &c&l- &aAccess to /setlistmotd <msg>\n"
                +"&3&oController.cmd.setmotd &c&l- &aAccess to /setmotd <motd>\n"
                + "&3&oController.cmd.Resrict &c&l- &aAccess to /restrict <username>\n"
                +"&3&oController.cmd.cp &c&l- &aAccess to /cp\n"
                +"&3&oController.cmd.usercp &c&l- &aAccess to /usercp <username>\n"
                + "&3&oController.event.restriction.water &c&l- &aAllows placement of &9Water\n."
                +"&3&oController.event.restriction.lava &c&l- &aAllows placement of &6Lava.\n"
                +"&3&oController.event.restrictionbypass &c&l- &aBypass the block restriction list."
                +"&6&oType /permissions <&apage number&6&o> to see more."
        ));
    }

    public void senderPermissionInfo2(CommandSender p)
    {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""
              +"&bController.cp.admin.chat &c&l- &aAccess to enable/disable chat.\n"
              +"&bController.cp.admin.motd &c&l- &aPermission for display MOTD.\n"
              +"&bController.cp.admin.whitelistmsg &c&l- &aAccess to whitelist message.\n"
              +"&bController.cp.admin.whitelist &c&l- &aAccess to whitelist. \n"
              +"&bController.cp.day &c&l- &aAllows setting server time day.\n"
              +"&bController.cp.night &c&l- &aAllows setting server time night.\n"
              +"&bController.cp.sun &c&l- &aChanges weather to sun.\n"
              +"&bController.cp.rain &c&l- &aChanges weather to rain.\n"
              +"&bController.cp.admin.stop &c&l- &4Stops the server. &6&lOnly give to trusted users!\n"
              +"&bController.cp.admin.reload &c&l- &cReloads the Server.\n"
              +"&bController.cp.admin.config &c&l- &6Reloads the &aConfig.\n"
              +"&6&oType /permissions <&apage number&6&o> to see more.\n"
      ));
    }

    public void sendPermissionInfo3(CommandSender p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',""
               +"&bController.event.whitelistmsg &c&l- &aAdmin Broadcast message.\n"
               +"&bController.cp.events.disconnect &c&l- &Allows personal disconnect from server."
               +"&eSQL.execute &c&l- &aAllows user to execute query's. &4Only give to user you TRUST!"
               +"&b&l████████&6&l████████&b&l████████\n"

        ));
    }

/*
    public void setAllPermissions() {
        AlertCommand.setAlertPerm("Controller.cmd.alert");
        ChatCommand.setChatPerm("Controller.cmd.chat");
        SetkickMsgCommand.setKickMsgPerm("Controller.cmd.setkickmsg");
        SetListMotdCommand.setListMotdPerm("Controller.cmd.setlistmotd");
        SetMotdCommand.setMotdPerm("Controller.cmd.setlistmotd");//Controller.cmd.setMotd
        UserCPCommand.setUsercpPermission("Controller.cmd.usercp");

        BlockListener.setBlockPlacePerm("Controller.event.restrictionbypass");
        BlockListener.setLavaPlacePerm("Controller.event.restriction.lava");
        BlockListener.setWaterPlacePerm("Controller.event.restriction.water");


        CPMenuEvent.setChatBypassPerm("Controller.event.chatbypass");
        CPMenuEvent.setChatItemPerm("Controller.cp.admin.chat");
        CPMenuEvent.setViewMotdPerm("Controller.cp.admin.motd");
        CPMenuEvent.setViewWhitelistPerm("Controller.cp.admin.whitelistmsg");
        CPMenuEvent.setEnableWhitelistPerm("Controller.cp.admin.whitelist");
        CPMenuEvent.setDayPerm("Controller.cp.day");
        CPMenuEvent.setNightPerm("Controller.cp.night");
        CPMenuEvent.setSunPerm("Controller.cp.sun");
        CPMenuEvent.setRainPerm("Controller.cp.rain");
        CPMenuEvent.setServerStopPerm("Controller.cp.admin.stop");
        CPMenuEvent.setServerReloadPerm("Controller.cp.admin.reload");
        CPMenuEvent.setConfigReload("Controller.cp.admin.config");
        //Controller.cmd.cp
        //Controller.cmd.whitelistmsg
        //Controller.cmd.motd

    }
    */

}
