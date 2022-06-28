package syferdev.personalwarps.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import syferdev.personalwarps.utils.WarpStorage;

public class DeleteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (sender instanceof Player) {
                Player p = (Player)sender;

                if (WarpStorage.doesWarpExist(args[0], p)) {
                    p.sendMessage(ChatColor.YELLOW + "Warp " + ChatColor.RED + "" + ChatColor.BOLD + args[0] + ChatColor.RESET + ChatColor.YELLOW + " has been deleted");
                    return WarpStorage.deleteWarp(args[0], p);
                } else {
                    p.sendMessage(ChatColor.YELLOW + "Warp " + ChatColor.RED + "" + ChatColor.BOLD + args[0] + ChatColor.RESET + ChatColor.YELLOW + " does not exist");
                    return false;
                }
            }
        }
        return false;
    }
}
