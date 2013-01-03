package ch.stni.bukkit.first;

import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class DebugCommando implements Commando {
    private final static Logger log = Logger.getLogger("Minecraft");

    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        Block targetBlock = player.getTargetBlock(null, 10);
        log.info("material: " + targetBlock.getType());
        log.info("data: " + targetBlock.getData());
        return true;
    }
}
