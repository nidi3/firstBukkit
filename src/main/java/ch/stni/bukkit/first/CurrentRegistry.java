package ch.stni.bukkit.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;


/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class CurrentRegistry {
    private final static Logger log = Logger.getLogger("Minecraft");

    private static class BlockInfo {
        GateHandler gateHandler;
        Position base;
        Direction direction;

        private BlockInfo(GateHandler gateHandler, Position base, Direction direction) {
            this.gateHandler = gateHandler;
            this.base = base;
            this.direction = direction;
        }

        public void write(Writer out) throws IOException {
            out.write(gateHandler.getClass().getName());
            out.write(";");
            base.writeWithCommas(out);
            out.write(";");
            direction.writeWithCommas(out);
        }

        public static BlockInfo read(String in) throws IOException {
            String[] parts = in.split(";");
            try {
                GateHandler handler = (GateHandler) Class.forName(parts[0]).newInstance();
                return new BlockInfo(handler, Position.readWithCommas(parts[1]), Direction.readWithCommas(parts[2]));
            } catch (InstantiationException e) {
                throw new IOException(e);
            } catch (IllegalAccessException e) {
                throw new IOException(e);
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            }
        }
    }

    private final Map<Integer, BlockInfo> registry = new HashMap<Integer, BlockInfo>();
    private final File dir;
    private final World world;

    public CurrentRegistry(World world, File dir) {
        this.world = world;
        this.dir = dir;
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private int blockHash(Block block) {
        int hash = block.getLocation().getBlockX() + 500;
        hash = hash * 1000 + block.getLocation().getBlockY();
        hash = hash * 1000 + block.getLocation().getBlockZ() + 500;
        return hash;
    }

    public void register(GateHandler gateHandler, Block base, Direction direction) {
        register(base, gateHandler, base, direction);
    }

    public void register(Block block, GateHandler gateHandler, Block base, Direction direction) {
        log.info("reg " + block + " with base " + base);
        registry.put(blockHash(block), new BlockInfo(gateHandler, Position.positionOf(base), direction));
    }

    public void unregisterBlock(Block block) {
        registry.remove(blockHash(block));
    }

    public void unregisterBase(Plugin plugin, Block base) {
        Position pos = Position.positionOf(base);
        List<Integer> keysToDelete = new ArrayList<Integer>();
        GateHandler handlerToDelete = null;
        for (Map.Entry<Integer, BlockInfo> entry : registry.entrySet()) {
            if (entry.getValue().base.equals(pos)) {
                keysToDelete.add(entry.getKey());
                handlerToDelete = entry.getValue().gateHandler;
            }
        }
        if (handlerToDelete != null) {
            log.info("unreg " + keysToDelete);
            handlerToDelete.destroy(plugin);
            for (Integer key : keysToDelete) {
                registry.remove(key);
            }
        }
    }

    public void notifyGate(BlockRedstoneEvent event) {
        BlockInfo blockInfo = registry.get(blockHash(event.getBlock()));
        if (blockInfo != null) {
            if (event.getNewCurrent() == 0 || event.getOldCurrent() == 0) {
//                log.info(System.nanoTime()/1000000 + " notify " + event.getBlock().getLocation() + " " +
//                        event.getBlock().getBlockPower() + " " +
//                        event.getBlock().isBlockIndirectlyPowered() + " " +
//                        event.getBlock().isBlockPowered() +
//                        " before " + event.getOldCurrent() + " after " + event.getNewCurrent());
                blockInfo.gateHandler.currentChanged(event, blockInfo.base.getBlockAt(event.getBlock().getWorld()), blockInfo.direction);
            }
        }
    }

    public void save(String name) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getFile(name)), "utf-8"));
        for (Map.Entry<Integer, BlockInfo> entry : registry.entrySet()) {
            out.write(entry.getKey() + ":");
            entry.getValue().write(out);
            out.newLine();
        }
        out.close();
    }

    private File getFile(String name) {
        return new File(dir, name + "-first.dat");
    }

    public void load(Plugin plugin, String name) throws IOException {
        if (!getFile(name).exists()) {
            return;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(getFile(name)), "utf-8"));
        while (in.ready()) {
            String line = in.readLine();
            String[] parts = line.split(":");
            int key = Integer.parseInt(parts[0]);
            BlockInfo bi = BlockInfo.read(parts[1]);
            log.info("init " + bi.gateHandler.getClass() + " base " + bi.base + " dir " + bi.direction);
            bi.gateHandler.init(plugin, bi.base.getBlockAt(world), bi.direction);
            registry.put(key, bi);
        }
        in.close();
    }

}
