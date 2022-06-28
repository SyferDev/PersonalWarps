package syferdev.personalwarps.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import syferdev.personalwarps.utils.WarpStorage;

import java.util.List;

public class CreateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                WarpStorage.createWarp(player, args[0]);

                player.sendMessage(ChatColor.YELLOW + "Warp has been created!");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Missing arguments");
        }
        return false;
    }
}
