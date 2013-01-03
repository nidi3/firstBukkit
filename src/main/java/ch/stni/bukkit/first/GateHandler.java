package ch.stni.bukkit.first;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public interface GateHandler {
    void init(Plugin plugin, Block base, Direction direction);

    void destroy(Plugin plugin);

    void currentChanged(BlockRedstoneEvent event, Block base, Direction direction);
}
