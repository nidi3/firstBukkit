package ch.stni.bukkit.first;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 27.09.11
 * Time: 01:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFieldCommando implements Commando {
    public abstract int size();

    public abstract boolean fill(int x, int y, int z);

    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        byte[][][] set = new byte[size()][size()][size()];
        for (int x = 0; x < size(); x++) {
            for (int y = 0; y < size(); y++) {
                for (int z = 0; z < size(); z++) {
                    if (fill(x, y, z)) {
                        set[x][y][z] = 1;
                    }
                }
            }
        }
        for (int x = 0; x < size(); x++) {
            for (int y = 0; y < size(); y++) {
                for (int z = 0; z < size(); z++) {
                    if (set[x][y][z] > 0) {
                        int nSet = 0;
                        if (x > 0 && set[x - 1][y][z] > 0) nSet++;
                        if (x < size() - 1 && set[x + 1][y][z] > 0) nSet++;
                        if (y > 0 && set[x][y - 1][z] > 0) nSet++;
                        if (y < size() - 1 && set[x][y + 1][z] > 0) nSet++;
                        if (z > 0 && set[x][y][z - 1] > 0) nSet++;
                        if (z < size() - 1 && set[x][y][z + 1] > 0) nSet++;
                        if (nSet == 6 || (y == 0 && nSet == 5)) {
                            set[x][y][z] = 2;
                        }
                    }
                }
            }
        }

        Material mat = player.getItemInHand().getType();
        for (int x = 0; x < size(); x++) {
            for (int y = 0; y < size(); y++) {
                for (int z = 0; z < size(); z++) {
                    if (set[x][y][z] == 1) {
                        player.getWorld().getBlockAt(player.getLocation().getBlockX() + x - size() / 2, player.getLocation().getBlockY() + y, player.getLocation().getBlockZ() + z - size() / 2).setType(mat);
                    }
                }
            }
        }
        return true;
    }
}
