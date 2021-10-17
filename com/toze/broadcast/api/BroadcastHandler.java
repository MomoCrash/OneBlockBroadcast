package com.toze.broadcast.api;

import java.util.List;

public interface BroadcastHandler {

    public int getInterval();
    public int getMinimumMessages();
    public List<List<String>> getMessages();
    public void reload();

}
