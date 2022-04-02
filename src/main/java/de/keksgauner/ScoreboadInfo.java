package de.keksgauner;

import com.plotsquared.core.PlotAPI;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ScoreboadInfo extends JavaPlugin {

    public static ScoreboadInfo THIS;

    @Override
    public void onEnable() {
        ScoreboadInfo.THIS = this;
        if (Bukkit.getPluginManager().getPlugin("PlotSquared") != null) {
            Bukkit.getPluginManager().registerEvents(new SetScoreboad(), ScoreboadInfo.THIS);
            PlotAPI plotAPI = new PlotAPI();
            plotAPI.registerListener(new UpdateScoreboad());
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
