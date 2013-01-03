package ch.stni.bukkit.first;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Torch;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class ClearCommando {
    private final static Logger log = Logger.getLogger("Minecraft");
    private Player player;
    private int count = 1;
    private int height = 2;
    private int width = 1;
    private int dy = 0;
    private Block block;
    private Direction direction;
    private Direction sideDir;
    private Direction backDir;
    private Torch torch;
    private int h, w;

    public boolean init(CommandSender sender, String[] params) {
        if (!(sender instanceof Player)) {
            return false;
        }
        player = (Player) sender;
        try {
            if (params.length >= 1) {
                count = Integer.parseInt(params[0]);
            }
            if (params.length >= 2) {
                height = Integer.parseInt(params[1]);
            }
            if (params.length >= 3) {
                width = Integer.parseInt(params[2]);
            }
            if (params.length >= 4) {
                dy = Integer.parseInt(params[3]);
            }
            block = player.getTargetBlock(null, 10);
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();

            backDir = Utils.direction(yaw + 180);
            sideDir = Utils.direction(yaw + 90);
            Location newLoc = getPlayerLocation();
            newLoc.setYaw(yaw);
            newLoc.setPitch(pitch);
            if (!Utils.teleportSafely(player, newLoc)) {
                player.sendMessage("Cannot go to " + newLoc);
                return false;
            }

            direction = Utils.playerDirection(player).add(new Direction(0, dy, 0));
            torch = new Torch();
            torch.setFacingDirection(sideDir.asBlockFace());
        } catch (NumberFormatException e) {
            //just use default values
        }
        return true;
    }

    private Location getPlayerLocation() {
        return backDir.addToLocation(block.getLocation(), 2).add(.5, 0, .5);
    }

    public boolean execute() {
        if (Math.abs(player.getLocation().getX() - getPlayerLocation().getX()) > .5 ||
                Math.abs(player.getLocation().getY() - getPlayerLocation().getY()) > 4 ||
                Math.abs(player.getLocation().getZ() - getPlayerLocation().getZ()) > .5) {
            return false;
        }
        Block relative = block.getRelative(sideDir.getX() * w, h, sideDir.getZ() * w);
        Material mat = relative.getType();
        log.info("found: " + mat);
        if (mat != Material.AIR) {
            switch (mat) {
                case STONE:
                    mat = Material.COBBLESTONE;
                    break;
                case LAVA:
                case WATER:
                case STATIONARY_LAVA:
                case STATIONARY_WATER:
                    mat = null;
                    break;

            }
            if (mat != null) {
                Utils.addItem(player, mat);
            }
            relative.setType(Material.AIR);
        }
        if (++w < width) {
            return true;
        }
        w = 0;
        if (++h < height) {
            return true;
        }
        h = 0;

        if (count % 4 == 0) {
            Block torchBlock = block.getRelative(0, 1, 0);
            int highestBlock = player.getWorld().getHighestBlockYAt(torchBlock.getLocation());
            if (highestBlock > torchBlock.getLocation().getBlockY()) {
                torchBlock.setType(torch.getItemType());
                torchBlock.setData(torch.getData());
            }
        }
        block = block.getRelative(direction.getX(), direction.getY(), direction.getZ());
        if (!Utils.teleportSafely(player, player.getLocation().add(direction.getX(), direction.getY(), direction.getZ()))) {
            return false;
        }
        return (--count > 0 && block.getLocation().getBlockY() > 2);
    }
}
