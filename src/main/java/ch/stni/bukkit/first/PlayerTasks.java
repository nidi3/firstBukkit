package ch.stni.bukkit.first;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 26.09.11
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTasks {
    private final static Logger log = Logger.getLogger("Minecraft");

    private final Map<String, Integer> tasks = new HashMap<String, Integer>();
    private final Server server;

    public PlayerTasks(Server server) {
        this.server = server;
    }

    public int startTask(Player player, int taskId) {
        log.info("Task started: " + taskId);
        if (taskId != -1) {
            Integer task = tasks.get(player.getName());
            if (task != null) {
                log.info("Task cancelled: " + task);
                server.getScheduler().cancelTask(task);
            }
            tasks.put(player.getName(), taskId);
        }
        return taskId;
    }

    public Integer getTaskId(Player player) {
        return tasks.get(player.getName());
    }

    public void cancelTask(Player player) {
        Integer taskId = getTaskId(player);
        log.info("Task ended: " + taskId);
        if (taskId != null) {
            server.getScheduler().cancelTask(taskId);
            tasks.remove(player.getName());
        }
    }

}
