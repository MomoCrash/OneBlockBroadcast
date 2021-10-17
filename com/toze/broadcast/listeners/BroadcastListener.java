package com.toze.broadcast.listeners;

import com.toze.broadcast.KBroadcastHandler;
import com.toze.broadcast.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Random;

public class BroadcastListener implements Listener {

    private int messagesCount;

    public BroadcastListener(KBroadcastHandler broadcastHandler, Main main) {
        this.messagesCount = 0;
        Bukkit.getScheduler().runTaskTimerAsynchronously(main, new Runnable() {

            Random random = new Random();
            int timer = 0;

            @Override
            public void run() {
                timer++;
                if (timer < broadcastHandler.getInterval()) return;

                timer = 0;
                if (messagesCount >= broadcastHandler.getMinimumMessages()) {
                    broadcastHandler.getMessages().get(random.nextInt(broadcastHandler.getMessages().size())).forEach(line -> Bukkit.broadcastMessage(line));
                    messagesCount = 0;
                }

            }

        }, 20, 20);

    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        this.messagesCount++;
    }

}
