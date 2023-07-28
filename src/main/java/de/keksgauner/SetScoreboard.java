package de.keksgauner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class SetScoreboard implements Listener {

     @EventHandler
     public void onJoin(PlayerJoinEvent event) {
          new CreateScoreboard(event.getPlayer());
     }
}
