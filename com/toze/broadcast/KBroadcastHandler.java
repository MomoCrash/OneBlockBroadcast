package com.toze.broadcast;

import com.google.gson.*;
import com.toze.broadcast.api.BroadcastHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KBroadcastHandler implements BroadcastHandler {

    private final JavaPlugin javaPlugin;
    private final File configFile;
    private final Gson gson;

    public int interval, mini_message;
    public List<List<String>> messages;

    public KBroadcastHandler(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.configFile = new File(javaPlugin.getDataFolder() + "/config.json");
        this.configFile.getParentFile().mkdirs();
        this.gson = new GsonBuilder().create();

        this.messages = new ArrayList<>();

        reload();
    }

    @Override
    public int getInterval() {
        return this.interval;
    }

    @Override
    public int getMinimumMessages() {
        return this.mini_message;
    }

    @Override
    public List<List<String>> getMessages() {
        return this.messages;
    }

    @Override
    public void reload() {

        if (!this.configFile.exists()) javaPlugin.saveResource("config.json", false);

        try {

            FileReader reader = new FileReader(this.configFile);
            JsonObject parser = (JsonObject) new JsonParser().parse(reader);
            this.interval = parser.get("interval").getAsInt();
            this.mini_message = parser.get("mini_message").getAsInt();
            JsonArray array = parser.get("messages").getAsJsonArray();
            this.messages.clear();
            array.forEach(list -> {
                JsonArray messages = list.getAsJsonArray();
                this.messages.add(IntStream.range(0, messages.size())
                        .mapToObj(messages::get)
                        .map(line -> ChatColor.translateAlternateColorCodes('&', line.toString().replace("\"", "")))
                        .collect(Collectors.toList()));
            });
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
