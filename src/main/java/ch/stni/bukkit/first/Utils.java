package ch.stni.bukkit.first;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 00:04
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    private final static Logger log = Logger.getLogger("Minecraft");

    public static boolean useItem(Player player, Material material) {
        int index = player.getInventory().first(material);
        if (index < 0) {
            return false;
        }
        ItemStack stack = player.getInventory().getItem(index);
        stack.setAmount(stack.getAmount() - 1);
        if (stack.getAmount() > 0) {
            player.getInventory().setItem(index, stack);
        } else {
            player.getInventory().setItem(index, null);
        }
        return true;
    }

    public static boolean addItem(Player player, Material material) {
        HashMap<Integer, ? extends ItemStack> itemStacks = player.getInventory().all(material);
        for (Map.Entry<Integer, ? extends ItemStack> entry : itemStacks.entrySet()) {
            if (entry.getValue().getAmount() < material.getMaxStackSize()) {
                entry.getValue().setAmount(entry.getValue().getAmount() + 1);
                log.info("added: " + entry.getKey() + " " + entry.getValue());
                player.getInventory().setItem(entry.getKey(), entry.getValue());
                return true;
            }
        }
        int empty = player.getInventory().firstEmpty();
        if (empty < 0) {
            return false;
        }
        player.getInventory().setItem(empty, new ItemStack(material, 1));
        return true;
    }

    public static Direction playerDirection(Player player) {
        float yaw = player.getLocation().getYaw();
        return direction(yaw);
    }

    public static Direction direction(float yaw) {
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw > 360) {
            yaw -= 360;
        }
        if (yaw < 45 || yaw >= 315) {
            return Direction.WEST;
        } else if (yaw < 135) {
            return Direction.NORTH;
        } else if (yaw < 225) {
            return Direction.EAST;
        }
        return Direction.SOUTH;
    }

    public static boolean teleportSafely(Player player, Location location) {
        World world = player.getWorld();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        for (int h = -2; h < 3; h++) {
            if (!isFree(world.getBlockAt(x, y + h - 1, z).getType()) &&
                    isFree(world.getBlockAt(x, y + h + 0, z).getType()) &&
                    isFree(world.getBlockAt(x, y + h + 1, z).getType())) {
                Location newLoc = new Location(world, location.getX(), location.getY() + h, location.getZ(), location.getYaw(), location.getPitch());
                player.teleport(newLoc);
                return true;
            }
        }
        return false;
    }

    public static boolean isFree(Material material) {
        //TODO etc
        return material == Material.AIR || material == Material.TORCH || material == Material.BONE || material == Material.BOW;
    }

    public static int inputAsInt(boolean[] input) {
        int res = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i]) res += 1 << i;
        }
        return res;
    }

    public static boolean[] outputFromInt(boolean[] output, int value) {
        for (int i = 0; i < output.length; i++) {
            output[i] = (value & (1 << i)) != 0;
        }
        return output;
    }

}
