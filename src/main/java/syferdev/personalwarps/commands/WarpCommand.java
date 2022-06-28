package syferdev.personalwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import syferdev.personalwarps.PersonalWarps;
import syferdev.personalwarps.models.Warp;
import syferdev.personalwarps.utils.WarpStorage;

import java.util.List;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                Warp warp = WarpStorage.getWarp(args[0], p);

                if (warp == null) {
                    p.sendMessage(ChatColor.YELLOW + "Warp " + ChatColor.RED + args[0] + ChatColor.YELLOW + " does not exist!");
                }

                int warpDelay = PersonalWarps.getPlugin().getConfig().getInt("warp.delay");

                // if OP, teleport instantly
                if (p.isOp()) {
                    p.sendMessage(ChatColor.YELLOW + "Warping!");
                    p.teleport(warp.location.toLocation(p.getWorld()));
                } else {
                    p.sendMessage(ChatColor.YELLOW + "Warping in " + ChatColor.RED + warpDelay + " seconds");
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(PersonalWarps.getPlugin(), () -> p.teleport(warp.location.toLocation(p.getWorld())), warpDelay * 20L);
                }

            } else {
                List<Warp> warps = WarpStorage.getPlayerWarps(p);

                if (warps.size() > 0)
                {
                    StringBuilder sb = new StringBuilder(ChatColor.YELLOW + "" + ChatColor.BOLD + "Personal Warps: " + ChatColor.RESET);
                    for (Warp warp : warps) {
                        sb.append(warp.getName() + ", ");
                    }

                    p.sendMessage(sb.toString());
                } else {
                    p.sendMessage(ChatColor.YELLOW + "You have no personal warps!");
                }
            }
        }
        return true;
    }
}
