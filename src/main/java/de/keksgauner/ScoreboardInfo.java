package de.keksgauner;

import com.plotsquared.core.PlotAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ScoreboardInfo extends JavaPlugin {

    public static ScoreboardInfo instance;
    public static FileConfiguration cfg;

    @Override
    public void onEnable() {
        synchronized (this) {
            instance = this;
        }

        setupConfig();

        if (Bukkit.getPluginManager().getPlugin("PlotSquared") != null) {
            Bukkit.getPluginManager().registerEvents(new SetScoreboard(), instance);
            PlotAPI plotAPI = new PlotAPI();
            plotAPI.registerListener(new UpdateScoreboard());
        }

        UpdateScoreboard.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupConfig() {
        cfg = this.getConfig();

        cfg.addDefault("scoreboard.title", "&8&l> &x&c&5&1&0&1&0&lShark&7&l-&4&lBay &8&l<");

        cfg.options().copyDefaults(true);
        saveConfig();
    }
}
