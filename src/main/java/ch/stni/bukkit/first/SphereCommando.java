package ch.stni.bukkit.first;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class SphereCommando extends AbstractFieldCommando implements Commando {
    private final static Logger log = Logger.getLogger("Minecraft");

    @Override
    public int size() {
        return 30;
    }

    @Override
    public boolean fill(int x, int y, int z) {
        return (x - size() / 2) * (x - size() / 2) + (y) * (y) + (z - size() / 2) * (z - size() / 2) < size() / 2 * size() / 2;
    }


//
//            int x0 = player.getLocation().getBlockX();
//            int y0 = player.getLocation().getBlockZ();
//            Material mat = player.getItemInHand().getType();
//            int h0 = player.getLocation().getBlockY();
//            for (int h = h0; h < h0 + r0; h++) {
//                int r = (int) Math.round(Math.sqrt(r0 * r0 - (h - h0) * (h - h0)));
//                int f = 1 - r;
//                int ddF_x = 1;
//                int ddF_y = -2 * r;
//                int x = 0;
//                int y = r;
//                player.getWorld().getBlockAt(x0, h, y0 + r).setType(mat);
//                player.getWorld().getBlockAt(x0, h, y0 - r).setType(mat);
//                player.getWorld().getBlockAt(x0 + r, h, y0).setType(mat);
//                player.getWorld().getBlockAt(x0 - r, h, y0).setType(mat);
//
//                while (x < y) {
//                    if (f >= 0) {
//                        y--;
//                        ddF_y += 2;
//                        f += ddF_y;
//                    }
//                    x++;
//                    ddF_x += 2;
//                    f += ddF_x;
//                    player.getWorld().getBlockAt(x0 + x, h, y0 + y).setType(mat);
//                    player.getWorld().getBlockAt(x0 - x, h, y0 + y).setType(mat);
//                    player.getWorld().getBlockAt(x0 + x, h, y0 - y).setType(mat);
//                    player.getWorld().getBlockAt(x0 - x, h, y0 - y).setType(mat);
//                    player.getWorld().getBlockAt(x0 + y, h, y0 + x).setType(mat);
//                    player.getWorld().getBlockAt(x0 - y, h, y0 + x).setType(mat);
//                    player.getWorld().getBlockAt(x0 + y, h, y0 - x).setType(mat);
//                    player.getWorld().getBlockAt(x0 - y, h, y0 - x).setType(mat);
//                }
//            }


}
