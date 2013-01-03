package ch.stni.bukkit.first;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 24.09.11
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public interface Commando {
    boolean execute(FirstPlugin plugin, Player player, String[] params);
}
