package com.toze.broadcast;

import com.toze.broadcast.commands.BroadcastReloadCommand;
import com.toze.broadcast.listeners.BroadcastListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        KBroadcastHandler broadcastHandler = new KBroadcastHandler(this);

        Bukkit.getPluginManager().registerEvents(new BroadcastListener(broadcastHandler, this), this);

        getCommand("broadcastreload").setExecutor(new BroadcastReloadCommand(broadcastHandler));

    }

}
