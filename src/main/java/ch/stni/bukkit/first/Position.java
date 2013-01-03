package ch.stni.bukkit.first;


import java.io.IOException;
import java.io.Writer;

import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 30.09.11
 * Time: 01:22
 * To change this template use File | Settings | File Templates.
 */
public class Position {
    final int x;
    final int y;
    final int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Position positionOf(Block block) {
        return new Position(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;
        if (z != position.z) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Block getBlockAt(World world) {
        return world.getBlockAt(x, y, z);
    }

    public void writeWithCommas(Writer out) throws IOException {
        out.write(x + "," + y + "," + z);
    }

    public static Position readWithCommas(String in) {
        String[] parts = in.split(",");
        return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
