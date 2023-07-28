package de.keksgauner;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.plotsquared.core.events.PlayerLeavePlotEvent;
import com.plotsquared.core.plot.Plot;
import com.sun.tools.javac.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Set;
import java.util.UUID;

public class UpdateScoreboard {

    @Subscribe
    public void onPlayerEnterPlot(PlayerEnterPlotEvent event) {
        Player player = Bukkit.getPlayer(event.getPlotPlayer().getUUID());
        if(player == null) return;

        Plot plot = event.getPlot();
        int plotX = event.getPlot().getId().getX();
        int plotY = event.getPlot().getId().getY();

        if (plot.getOwners().isEmpty())
            new CreateScoreboard(player.getScoreboard()).setTeamPrefix("plot_owner", "&aNoch zu haben");
        else {
            String owner = getName(plot) + "";
            if (owner.equalsIgnoreCase(player.getName()))
                new CreateScoreboard(player.getScoreboard()).setTeamPrefix("plot_owner", "&3Dein Plot");
            else
                new CreateScoreboard(player.getScoreboard()).setTeamPrefix("plot_owner", "&6" + owner);
        }

        new CreateScoreboard(player.getScoreboard()).setTeamPrefix("plot_plotID", "&9" + plotX + "&7;&9" + plotY);
    }
    @Subscribe
    public void onPlayerLeavePlot(PlayerLeavePlotEvent event) {
        Player player = Bukkit.getPlayer(event.getPlotPlayer().getUUID());
        if(player == null) return;
        new CreateScoreboard(player.getScoreboard()).setTeamPrefix("plot_owner", "&7Road");
        new CreateScoreboard(player.getScoreboard()).setTeamPrefix("plot_plotID", "");

    }

    public static String getName(Plot plot) {
        Set<UUID> uuid = plot.getOwners();
        UUID firstUuid = uuid.iterator().next();

        return Bukkit.getOfflinePlayer(firstUuid).getName();
    }

    static BukkitTask bukkitTask;

    public static void start() {
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    new CreateScoreboard(player.getScoreboard()).setTeamPrefix("money", PlaceholderAPI.setPlaceholders(player, "%cmi_user_balance_formated%"));
                }
            }
        }.runTaskTimer(ScoreboardInfo.instance, 20 * 5, 0);
    }
}
