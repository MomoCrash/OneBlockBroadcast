package com.toze.broadcast.listeners;

import com.toze.broadcast.BroadcastHandler;
import com.toze.broadcast.OneBlockBroadcast;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;

import java.util.List;
import java.util.Random;

public class BroadcastListener implements Listener {

    private final Random random = new Random();
    private int messagesCount;

    /**
     * Default constructor for BroadcastListener
     */
    public BroadcastListener() {

        this.messagesCount = 0;
        final BroadcastHandler broadcastHandler = OneBlockBroadcast.getInstance().getBroadcastHandler();

        Bukkit.getScheduler().runTaskTimerAsynchronously(OneBlockBroadcast.getInstance(), new Runnable() {

            int timer = 0;

            @Override
            public void run() {
                timer++;
                if (timer < broadcastHandler.getInterval()) return;

                timer = 0;
                if (messagesCount >= broadcastHandler.getMinimumMessages()) {
                    final List<List<String>> messages = broadcastHandler.getMessages();
                    final List<String> messagesToSend = messages.get(random.nextInt(messages.size()));

                    for (String s : messagesToSend) {
                        Bukkit.getServer().broadcastMessage(s);
                    }

                    messagesCount = 0;
                }

            }

        }, 20, 20);

    }

    @EventHandler
    public void onMessage(AsyncChatEvent event) {
        this.messagesCount++;
    }

}
