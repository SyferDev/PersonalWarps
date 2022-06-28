package syferdev.personalwarps.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import syferdev.personalwarps.PersonalWarps;
import syferdev.personalwarps.models.Warp;
import syferdev.personalwarps.models.WarpLocation;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpStorage {

    private static ArrayList<Warp> warps = new ArrayList<>();

    public static Warp createWarp(Player p, String warpName) {
        WarpLocation warpLocation = new WarpLocation(p.getWorld(), p.getLocation());
        Warp warp = new Warp(warpName, p.getUniqueId().toString(), warpLocation);
        warps.add(warp);

        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return warp;
    }

    public static Warp getWarp(String warpName, Player player) {
        for (Warp warp : warps) {
            if (warp.getOwner().equals(player.getUniqueId().toString()) && warp.getName().equals(warpName)) {
                return warp;
            }
        }

        return null;
    }

    public static List<Warp> getPlayerWarps(Player player) {
        ArrayList<Warp> w = new ArrayList<>();

        for (Warp warp : warps) {
            if (warp.getOwner().equals(player.getUniqueId().toString())) {
                w.add(warp);
            }
        }

        return w;
    }

    public static boolean deleteWarp(String warpName, Player player) {
        for (Warp warp : warps) {
            if (warp.getOwner().equals(player.getUniqueId().toString()) && warp.getName().equals(warpName)) {
                warps.remove(warp);
            }
        }
        try {
            save();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean doesWarpExist(String warpName, Player p) {
        Warp w = getWarp(warpName, p);

        return w != null;
    }

    public static void save() throws IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(PersonalWarps.getPlugin().getDataFolder().getAbsolutePath() + "/warps.json");
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(warps, writer);
        writer.flush();
        writer.close();
    }

    public static void loadWarps() throws IOException {
        Gson gson = new Gson();
        File file = new File(PersonalWarps.getPlugin().getDataFolder().getAbsolutePath() + "/warps.json");
        if (file.exists()) {
            Reader reader = new FileReader(file);

            Warp[] w = gson.fromJson(reader, Warp[].class);

            if (w != null && w.length > 0)
                warps = new ArrayList<>(Arrays.asList(w));
        }
    }

}
