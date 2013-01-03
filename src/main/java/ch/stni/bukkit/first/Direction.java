package ch.stni.bukkit.first;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;


/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class Direction extends Position {
    public static final Direction UP = new Direction(0, 1, 0);
    public static final Direction DOWN = new Direction(0, -1, 0);
    public static final Direction NORTH = new Direction(-1, 0, 0);
    public static final Direction WEST = new Direction(0, 0, 1);
    public static final Direction EAST = new Direction(0, 0, -1);
    public static final Direction SOUTH = new Direction(1, 0, 0);

    public Direction(int x, int y, int z) {
        super(x, y, z);
    }

    public Direction add(Direction dir) {
        return new Direction(x + dir.x, y + dir.y, z + dir.z);
    }

    public BlockFace asBlockFace() {
        if (equals(NORTH)) {
            return BlockFace.NORTH;
        }
        if (equals(WEST)) {
            return BlockFace.WEST;
        }
        if (equals(EAST)) {
            return BlockFace.EAST;
        }
        if (equals(SOUTH)) {
            return BlockFace.SOUTH;
        }
        return null;
    }

    public Location addToLocation(Location location, double times) {
        Location newLoc = new Location(location.getWorld(), location.getX() + x * times, location.getY() + y * times, location.getZ() + z * times, location.getYaw(), location.getPitch());
        return newLoc;
    }

    public Block from(Block block, int times) {
        return block.getRelative(x * times, y * times, z * times);
    }

    public Position from(Position position, int times) {
        return new Position(position.x + x * times, position.y + y * times, position.z + z * times);
    }

    public Direction right() {
        if (equals(NORTH)) {
            return EAST;
        }
        if (equals(EAST)) {
            return SOUTH;
        }
        if (equals(SOUTH)) {
            return WEST;
        }
        if (equals(WEST)) {
            return NORTH;
        }
        throw new IllegalArgumentException();
    }

    public static Direction readWithCommas(String in) {
        String[] parts = in.split(",");
        return new Direction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
