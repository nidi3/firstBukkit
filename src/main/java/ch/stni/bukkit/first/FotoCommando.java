package ch.stni.bukkit.first;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 10.10.11
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class FotoCommando implements Commando {
    private final static Logger log = Logger.getLogger("Minecraft");

    @Override
    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        //-57,67,-287
        for (int x = -9; x <= 9; x++) {
            for (int z = -16; z <= 16; z++) {
                Location loc = player.getLocation();
                loc.setYaw(loc.getYaw() + (z + 2) * 3);
                loc.setPitch(loc.getPitch() + x * 3);
                BlockIterator iter = new BlockIterator(player.getWorld(), loc.toVector(), loc.getDirection(), player.getEyeHeight(), 80);
                Block source = null;
                while (iter.hasNext()) {
                    source = iter.next();
                    if (source.getType() != Material.AIR) {
                        break;
                    }
                }
                Block target = player.getWorld().getBlockAt(-57, 77 - x, -287 - z);
                minimalTransformer(player, source, target);
            }
        }
        return true;
    }

    private void woolTransformer(Player player, Block source, Block target) {
        Material mat = source.getType();
        byte data = source.getData();
        switch (mat) {
            case AIR:
            case GLASS:
                mat = Material.WOOL;
                data = 3;
                break;
            case STATIONARY_WATER:
            case WATER:
            case LAPIS_ORE:
                mat = Material.WOOL;
                data = 11;
                break;
            case DIRT:
            case SIGN:
            case SIGN_POST:
            case LOG:
            case DEAD_BUSH:
            case WORKBENCH:
            case CHEST:
            case BROWN_MUSHROOM:
                mat = Material.WOOL;
                data = 12;
                break;
            case GRASS:
            case LONG_GRASS:
                mat = Material.WOOL;
                double distance = player.getLocation().distance(source.getLocation());
                double dy = source.getY() - (player.getLocation().getY() + player.getEyeHeight());
                data = (Math.sin(dy / distance) < -Math.PI / 8) ? (byte) 5 : 12;
                //log.info("d: " + distance + " dy: " + dy + " a: " + Math.sin(dy / distance));
                break;
            case CACTUS:
            case SUGAR_CANE_BLOCK:
            case SEEDS:
                mat = Material.WOOL;
                data = 5;
                break;
            case STONE:
            case COBBLESTONE:
            case LEVER:
            case FURNACE:
                mat = Material.WOOL;
                data = 8;
                break;
            case SAND:
            case SANDSTONE:
            case YELLOW_FLOWER:
            case GOLDEN_APPLE:
            case GOLD_ORE:
                mat = Material.WOOL;
                data = 4;
                break;
            case GRAVEL:
                mat = Material.WOOL;
                data = 6;
                break;
            case TORCH:
            case IRON_ORE:
                mat = Material.WOOL;
                data = 1;
                break;
            case COAL_ORE:
                mat = Material.WOOL;
                data = 7;
                break;
            case LAVA:
            case STATIONARY_LAVA:
            case REDSTONE_TORCH_OFF:
            case REDSTONE_TORCH_ON:
            case REDSTONE_ORE:
            case REDSTONE_WIRE:
            case DIODE_BLOCK_OFF:
            case DIODE_BLOCK_ON:
            case RED_ROSE:
                mat = Material.WOOL;
                data = 14;
                break;
            case BEDROCK:
                mat = Material.WOOL;
                data = (source.getLocation().getY() > 0) ? (byte) 15 : 3;
                break;
            case LEAVES:
                mat = Material.WOOL;
                data = 13;
                break;
            case WOOL:
                break;
            default:
                log.info("" + mat);
        }
        target.setType(mat);
        target.setData(data);
    }

    private void minimalTransformer(Player player, Block source, Block target) {
        Material mat = source.getType();
        byte data = source.getData();
        switch (mat) {
            case AIR:
                mat = Material.WOOL;
                data = 3;
                break;
            case STATIONARY_WATER:
            case WATER:
                mat = Material.WOOL;
                data = 11;
                break;
            case SIGN:
            case SIGN_POST:
            case DEAD_BUSH:
            case BROWN_MUSHROOM:
                mat = Material.WOOL;
                data = 12;
                break;
            case LONG_GRASS:
            case CACTUS:
            case SUGAR_CANE_BLOCK:
                mat = Material.WOOL;
                data = 8;
                break;
//            case GRASS:
//            case LONG_GRASS:
//                double distance = player.getLocation().distance(source.getLocation());
//                double dy = source.getY() - (player.getLocation().getY() + player.getEyeHeight());
//                if (Math.sin(dy / distance) < -Math.PI / 8) {
//                    mat = Material.WOOL;
//                    data = 5;
//                }
//                break;
            case LEVER:
                mat = Material.WOOL;
                data = 8;
                break;
            case YELLOW_FLOWER:
            case GOLDEN_APPLE:
                mat = Material.WOOL;
                data = 4;
                break;
            case SEEDS:
            case SAPLING:
                mat = Material.WOOL;
                data = 5;
                break;
            case TORCH:
                mat = Material.WOOL;
                data = 1;
                break;
            case LAVA:
            case STATIONARY_LAVA:
            case REDSTONE_TORCH_OFF:
            case REDSTONE_TORCH_ON:
            case REDSTONE_WIRE:
            case DIODE_BLOCK_OFF:
            case DIODE_BLOCK_ON:
            case RED_ROSE:
                mat = Material.WOOL;
                data = 14;
                break;
            case BEDROCK:
                mat = Material.WOOL;
                data = (source.getLocation().getY() > 0) ? (byte) 15 : 3;
                break;
            case LEAVES:
                mat = Material.WOOL;
                data = 13;
                break;
            case WOOL:
            case LOG:
            case COBBLESTONE:
            case GRASS:
            case STONE:
            case DIRT:
            case LAPIS_ORE:
            case COAL_ORE:
            case GRAVEL:
            case SAND:
            case SANDSTONE:
            case REDSTONE_ORE:
            case IRON_ORE:
                break;
            default:
                log.info("" + mat);
        }
        target.setType(mat);
        target.setData(data);
    }
}
