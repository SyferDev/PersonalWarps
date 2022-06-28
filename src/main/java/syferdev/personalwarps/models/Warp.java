package syferdev.personalwarps.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Warp {
    public String name;
    public String owner;

    public WarpLocation location;

    public Warp(String name, String owner, WarpLocation location) {
        this.name = name;
        this.owner = owner;
        this.location = location;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }
}


