package ch.stni.bukkit.first;

import static org.bukkit.Material.LEVER;
import static org.bukkit.Material.REDSTONE_ORE;
import static org.bukkit.Material.STONE;
import static org.bukkit.Material.WOOL;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class DisplayCommando extends GateCommando {
    private final static Logger log = Logger.getLogger("Minecraft");

    private int pos = 0;
    private int taskId;

    public DisplayCommando() {
        super(
                new Position[]{pos(0, 0, -1), pos(2, 0, -1), pos(4, 0, -1), pos(6, 0, -1)},
                new int[]{},
                new Material[][][]{{
                        {REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                        {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                },
                        {
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                                {WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL, WOOL},
                        }
                },
                new Position[]{},
                new XorGate());
    }

    @Override
    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        final Block first = getFirstBlock(player);
        if (super.execute(plugin, player, params)) {
            plugin.getCurrentRegistry(player.getWorld()).register(this, first, getForth(player));
            return true;
        }
        return false;
    }

    @Override
    public void destroy(Plugin plugin) {
        log.info("destroy display");
        plugin.getServer().getScheduler().cancelTask(taskId);
    }

    @Override
    public void init(Plugin plugin, final Block base, final Direction direction) {
        final Block displayStart = direction.from(base, 1);
        final Direction right = direction.right();
        log.info("init display");
        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                pos++;
                if (pos == 16) {
                    pos = 0;
                }
                setOutput(pos(16, 0, 0), direction, base, pos % 2 == 0);
                for (int i = 0; i < 4; i++) {
                    setOutput(pos(8 + i * 2, 0, 0), direction, base, (pos & (1 << i)) != 0);
                }
            //    log.info(System.nanoTime() / 1000000 + " dis out " + pos);
            }
        }, 0, 10);
        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                boolean[] inputs = getInputs(null, base, direction);
               // log.info(System.nanoTime() / 1000000 + " dis in " + Arrays.toString(inputs) + " for " + pos);
                int value = Utils.inputAsInt(inputs);
                Block current = Direction.UP.from(right.from(displayStart, pos / 4), pos % 4);
                current.setData((byte) value);
            }
        }, 5, 10);
//        new Thread(new Runnable() {
//            public void run() {
//                for (; ; ) {
//                    try {
//                        pos++;
//                        if (pos == 16) {
//                            pos = 0;
//                        }
//                        setOutput(pos(16, 0, 0), direction, base, pos % 2 == 0);
//                        for (int i = 0; i < 4; i++) {
//                            setOutput(pos(8 + i * 2, 0, 0), direction, base, (pos & (1 << i)) != 0);
//                        }
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        }
//                        boolean[] inputs = getInputs(null, base, direction);
//                        log.info(System.nanoTime() + " dis in " + Arrays.toString(inputs) + " for " + pos);
//                        int value = Utils.inputAsInt(inputs);
//                        Block current = Direction.UP.from(right.from(displayStart, pos / 4), pos % 4);
//                        current.setData((byte) value);
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }
}