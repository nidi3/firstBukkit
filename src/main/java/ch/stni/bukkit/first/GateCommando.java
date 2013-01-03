package ch.stni.bukkit.first;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.material.RedstoneTorch;
import org.bukkit.plugin.Plugin;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class GateCommando implements Commando, GateHandler {
    private final static Logger log = Logger.getLogger("Minecraft");

    private final Material[][][] blocks;
    private final Position[] inputPos;
    private final int[] listeners;
    private final Position[] outputPos;
     final Gate gate;

    protected GateCommando(Position[] inputPos, int[] listeners, Material[][][] blocks, Position[] outputPos, Gate gate) {
        this.inputPos = inputPos;
        this.listeners = listeners;
        this.blocks = blocks;
        this.outputPos = outputPos;
        this.gate = gate;
    }

    protected static Position pos(int x, int y, int z) {
        return new Position(x, y, z);
    }

    @Override
    public void init(Plugin plugin, Block base, Direction direction) {

    }

    public void destroy(Plugin plugin) {
    }

    @Override
    public void currentChanged(BlockRedstoneEvent event, Block base, Direction direction) {
        boolean[] inputs = getInputs(event, base, direction);
        inputs = inputsToUse(inputs);
        //log.info("in  " + Arrays.toString(inputs));
        boolean[] outputs = gate.apply(inputs);
        //log.info("out " + Arrays.toString(outputs));
        int i = 0;
        for (Position output : outputPos) {
            //log.info("outblock " + getBlockAt(output, direction, base));
            setOutput(output, direction, base, outputs[i++]);
        }
    }

    protected boolean[] getInputs(BlockRedstoneEvent event, Block base, Direction direction) {
        boolean[] inputs = new boolean[inputPos.length];
        int i = 0;
        for (Position input : inputPos) {
            Block block = getBlockAt(input, direction, base);
            //log.info("test "+block);
            inputs[i++] = (event != null && event.getBlock().equals(block))
                    ? event.getNewCurrent() > 0
                    : block.isBlockIndirectlyPowered();
        }
        return inputs;
    }

    protected void setOutput(Position output, Direction direction, Block base, boolean value) {
        getBlockAt(output, direction, base).setData((byte) (value ? 8 : 0));
    }

    @Override
    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        Direction forth = getForth(player);
        Direction right = getRight(player);
        RedstoneTorch torch = getTorch(player);

        Block first = getFirstBlock(player);
        for (int f = 0; f < blocks.length; f++) {
            for (int h = 0; h < blocks[f].length; h++) {
                for (int r = 0; r < blocks[f][h].length; r++) {
                    Block current = forth.from(Direction.UP.from(right.from(first, r), h), f);
                    Material material = blocks[f][h][r];
                    byte data = 0;
                    if (material == Material.REDSTONE_TORCH_OFF) {
                        material = torch.getItemType();
                        data = torch.getData();
                    }
                    log.info(material + " " + data);
                    current.setType(material);
                    current.setData(data);
                    if (material == Material.SIGN_POST) {
                        Sign sign = (Sign) (current.getState());
                        for (int i = 0; i < 4; i++) {
                            //sign.setLine(i, "00 00 00 00");
                        }
                    }
                }
            }
        }
        for (int listener : listeners) {
            plugin.getCurrentRegistry(player.getWorld()).register(getBlockAt(inputPos[listener], forth, first), this, first, forth);
        }
        init(plugin,first,forth);
        return true;
    }

    protected boolean[] inputsToUse(boolean[] inputs) {
        return inputs;
    }

    protected RedstoneTorch getTorch(Player player) {
        RedstoneTorch torch = new RedstoneTorch();
        torch.setFacingDirection(getForth(player).asBlockFace());
        return torch;
    }

    protected Block getFirstBlock(Player player) {
        return player.getTargetBlock(null, 10).getRelative(0, 1, 0);
    }

    protected Direction getRight(Player player) {
        return Utils.direction(player.getLocation().getYaw() + 90);
    }

    protected Direction getForth(Player player) {
        return Utils.direction(player.getLocation().getYaw());
    }

    private Block getBlockAt(Position position, Direction forth, Block first) {
        return forth.from(Direction.UP.from(forth.right().from(first, position.getX()), position.getY()), position.getZ());
    }
}
