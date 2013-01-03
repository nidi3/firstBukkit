package ch.stni.bukkit.first;


import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 24.09.11
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class BuildUpCommando extends BuildStraightCommando {
    @Override
    protected Direction getDirection(Player player) {
        return Direction.UP;
    }

}
