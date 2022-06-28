package syferdev.personalwarps.models;

import org.bukkit.Location;
import org.bukkit.World;

public class WarpLocation {
    public String worldId;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;

    public WarpLocation(World world, Location location) {
        this.worldId = world.getUID().toString();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z, this.yaw, this.pitch);
    }
}
