package de.keksgauner;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.plotsquared.core.events.PlayerLeavePlotEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class UpdateScoreboad {

    @Subscribe
    public void onPlayerEnterPlot(PlayerEnterPlotEvent event) {
        Player player = Bukkit.getPlayer(event.getPlotPlayer().getUUID());
        if(player == null) return;

        Plot plot = event.getPlot();
        int plotX = event.getPlot().getId().getX();
        int plotY = event.getPlot().getId().getY();

        if (plot.getOwners().isEmpty())
            new CreateScoreboad(player.getScoreboard()).setTeamPrefix("plot_owner", "&aNoch zu haben");
        else {
            String owner = getName(plot) + "";
            if (owner.equalsIgnoreCase(player.getName()))
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("plot_owner", "&3Dein Plot");
            else
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("plot_owner", "&6" + owner);
        }

        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("plot_plotID", "&9" + plotX + "&7;&9" + plotY);
    }
    @Subscribe
    public void onPlayerLeavePlot(PlayerLeavePlotEvent event) {
        Player player = Bukkit.getPlayer(event.getPlotPlayer().getUUID());
        if(player == null) return;
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("plot_owner", "&7Road");
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("plot_plotID", "");

    }

    public static String getName(Plot plot) {
        Set<UUID> uuid = plot.getOwners();
        UUID firstUuid = uuid.iterator().next();

        return Bukkit.getOfflinePlayer(firstUuid).getName();
    }
}
