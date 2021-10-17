package com.toze.broadcast.commands;

import com.toze.broadcast.KBroadcastHandler;
import com.toze.broadcast.api.BroadcastHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastReloadCommand implements CommandExecutor {

    private final BroadcastHandler broadcastHandler;

    public BroadcastReloadCommand(BroadcastHandler broadcastHandler) {
        this.broadcastHandler = broadcastHandler;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        broadcastHandler.reload();
        commandSender.sendMessage("§aConfiguration reloaded!");
        return false;
    }
}
