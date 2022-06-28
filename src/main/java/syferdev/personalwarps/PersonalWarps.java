package syferdev.personalwarps;

import org.bukkit.plugin.java.JavaPlugin;
import syferdev.personalwarps.commands.CreateCommand;
import syferdev.personalwarps.commands.DeleteCommand;
import syferdev.personalwarps.commands.WarpCommand;
import syferdev.personalwarps.completers.WarpTabCompleter;
import syferdev.personalwarps.utils.WarpStorage;

import java.io.IOException;

public final class PersonalWarps extends JavaPlugin {

    private static PersonalWarps plugin;

    public static PersonalWarps getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        plugin = this;

        getCommand("pwarp").setExecutor(new WarpCommand());
        getCommand("pwarp").setTabCompleter(new WarpTabCompleter());
        getCommand("setpwarp").setExecutor(new CreateCommand());
        getCommand("delpwarp").setExecutor(new DeleteCommand());
        getCommand("delpwarp").setTabCompleter(new WarpTabCompleter());

        try {
            WarpStorage.loadWarps();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
