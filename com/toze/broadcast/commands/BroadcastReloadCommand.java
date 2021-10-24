package com.toze.broadcast.commands;

import com.toze.broadcast.OneBlockBroadcast;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
        OneBlockBroadcast.getInstance().getBroadcastHandler().reload();
        commandSender.sendMessage("§aConfiguration reloaded!");
        return false;
    }

}
