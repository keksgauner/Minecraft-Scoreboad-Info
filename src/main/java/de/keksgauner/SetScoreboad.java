package de.keksgauner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SetScoreboad implements Listener {

     @EventHandler
     public void onJoin(PlayerJoinEvent event) {
          new CreateScoreboad(event.getPlayer());
     }

}
