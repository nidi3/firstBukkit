package ch.stni.bukkit.first;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 02:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class BuildStraightCommando implements Commando {
    protected abstract Direction getDirection(Player player);

    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        try {
            int count = 1;
            int width = 1;
            if (params.length >= 1) {
                count = Integer.parseInt(params[0]);
            }
            if (params.length >= 2) {
                width = Integer.parseInt(params[1]);
            }
            if (player.getItemInHand() == null) {
                return true;
            }
            Material material = player.getItemInHand().getType();
            if (!material.isBlock()) {
                return true;
            }
            Block block = player.getTargetBlock(null, 10);
            Direction direction = getDirection(player);
            Direction sideDir = Utils.direction(player.getLocation().getYaw() + 90);
            do {
                for (int w = 0; w < width; w++) {
                    Block relative = block.getRelative(sideDir.getX() * w, 0, sideDir.getZ() * w);
                    if (relative.getType() == Material.AIR || relative.getType() == Material.WATER || relative.getType() == Material.LAVA || relative.getType() == Material.STATIONARY_WATER || relative.getType() == Material.STATIONARY_LAVA) {
                        if (!Utils.useItem(player, material)) {
                            return true;
                        }
                        relative.setType(material);
                    }
                }
                block = block.getRelative(direction.getX(), direction.getY(), direction.getZ());
            } while (--count > 0);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
