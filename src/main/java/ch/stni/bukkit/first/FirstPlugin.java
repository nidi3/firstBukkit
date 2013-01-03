package ch.stni.bukkit.first;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 24.09.11
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class FirstPlugin extends JavaPlugin {
    private final static Logger log = Logger.getLogger("Minecraft");
    private final static Map<String, Commando> commandos = new HashMap<String, Commando>();
    private PlayerTasks playerTasks;
    private CurrentRegistries currentRegistries;

    static {
        commandos.put("up", new BuildUpCommando());
        commandos.put("forth", new BuildHorizontalCommando());
        //commandos.put("clear", new ClearCommando());
        commandos.put("d", new DebugCommando());
        commandos.put("sphere", new SphereCommando());
        commandos.put("cylinder", new CylinderCommando());
        commandos.put("xor", new XorCommando());
        commandos.put("seg", new SevenSegmentCommando());
        commandos.put("display", new DisplayCommando());
        commandos.put("ram", new RamCommando());
        commandos.put("foto", new FotoCommando());
    }


    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        try {
            currentRegistries.saveAll();
        } catch (IOException e) {
            log.log(Level.SEVERE, "could not save state", e);
        }
        log.info("ended " + getDescription().getName());
    }

    private String[] lines;

    public void onEnable() {
        playerTasks = new PlayerTasks(getServer());
        currentRegistries = new CurrentRegistries(this);
        currentRegistries.init(getServer().getWorlds());
        log.info("started " + getDescription().getName());

//        }, Event.Priority.Low, this);
//        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_BREAK, new BlockListener() {
//            @Override
//            public void onBlockBreak(BlockBreakEvent event) {
//                getCurrentRegistry(event.getBlock().getWorld()).unregisterBase(FirstPlugin.this, event.getBlock());
//                if (event.getBlock().getType() == Material.SIGN_POST) {
//                    log.info("sign br");
//                    Sign sign = (Sign) (event.getBlock().getState());
//                    lines = sign.getLines();
//                }
//            }
//        }, Event.Priority.Low, this);
//
        getServer().getPluginManager().registerEvent(Event.Type.SIGN_CHANGE, new BlockListener() {
            @Override
            public void onSignChange(SignChangeEvent event) {
                //log.info("sign ch " + Arrays.toString(event.getLines()));
                lines = event.getLines();
            }

        }, Event.Priority.Low, this);
//
//        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACE, new BlockListener() {
//            @Override
//            public void onBlockPlace(BlockPlaceEvent event) {
//                if (event.getBlockPlaced().getType() == Material.SIGN_POST && lines != null) {
//                    log.info("sign pl");
//                    Sign sign = (Sign) (event.getBlockPlaced().getState());
//                    for (int i = 0; i < lines.length; i++) {
//                        sign.setLine(i, lines[i]);
//                    }
//                }
//            }
//        }, Event.Priority.Low, this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                List<Player> players = getServer().getWorlds().get(0).getPlayers();
                if (!players.isEmpty()) {
                    new FotoCommando().execute(FirstPlugin.this, players.get(0), null);
                }
            }
        }, 2, 2);

        getServer().getPluginManager().registerEvent(Event.Type.REDSTONE_CHANGE, new BlockListener() {
            @Override
            public void onBlockRedstoneChange(BlockRedstoneEvent event) {
                getCurrentRegistry(event.getBlock().getWorld()).notifyGate(event);
            }
        }, Event.Priority.Low, this);
    }

    public CurrentRegistry getCurrentRegistry(World world) {
        return currentRegistries.getRegistry(world);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        final Player player = (Player) sender;
        try {
            log.info(sender + " " + command.getName() + " " + label + " " + Arrays.toString(args));
            for (Map.Entry<String, Commando> commando : commandos.entrySet()) {
                if (commando.getKey().equals(command.getName())) {
                    Commando cmd = commando.getValue();
                    return cmd.execute(this, player, args);
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exeception", e);
        }
        if (command.getName().equals("clear")) {
            final ClearCommando commando = new ClearCommando();
            if (!commando.init(sender, args)) {
                return false;
            }

            int taskId = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                public void run() {
                    if (!commando.execute()) {
                        playerTasks.cancelTask(player);
                    }
                }
            }, 0, 1);
            return playerTasks.startTask(player, taskId) != -1;
        }
        if (command.getName().equals("stop")) {
            playerTasks.cancelTask(player);
        }
        return false;
    }
}
