package syferdev.personalwarps.completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import syferdev.personalwarps.models.Warp;
import syferdev.personalwarps.utils.WarpStorage;

import java.util.ArrayList;
import java.util.List;

public class WarpTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1) {
            if (sender instanceof Player) {
                Player p = (Player)sender;

                List<Warp> warps = WarpStorage.getPlayerWarps(p);

                List<String> warpsList = new ArrayList<>();

                for (Warp warp : warps) {
                    warpsList.add(warp.getName());
                }

                return warpsList;
            }
        }

        return null;
    }
}
