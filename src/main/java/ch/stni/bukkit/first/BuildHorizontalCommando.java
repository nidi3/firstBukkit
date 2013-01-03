package ch.stni.bukkit.first;


import java.util.logging.Logger;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 24.09.11
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class BuildHorizontalCommando extends BuildStraightCommando {
    private final static Logger log = Logger.getLogger("Minecraft");

    @Override
    protected Direction getDirection(Player player) {
        return Utils.playerDirection(player);
    }

}
