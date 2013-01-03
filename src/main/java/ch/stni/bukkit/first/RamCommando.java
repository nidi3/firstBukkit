package ch.stni.bukkit.first;

import static org.bukkit.Material.LEVER;
import static org.bukkit.Material.REDSTONE_ORE;
import static org.bukkit.Material.SIGN_POST;
import static org.bukkit.Material.STONE;

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
public class RamCommando extends GateCommando {
    private final static Logger log = Logger.getLogger("Minecraft");

    public RamCommando() {
        super(
                new Position[]{pos(-1, 0, 1), pos(15, 0, -1), pos(13, 0, -1), pos(11, 0, -1), pos(9, 0, -1), pos(7, 0, -1), pos(5, 0, -1), pos(3, 0, -1), pos(1, 0, -1)},
                new int[]{0},
                new Material[][][]{{
                        {STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE},
                        {SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST}
                },
                        {
                                {REDSTONE_ORE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
                                {SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST, SIGN_POST}
                        },
                        {
                                {STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER, STONE, LEVER},
                        }
                },
                new Position[]{pos(15, 0, 2), pos(13, 0, 2), pos(11, 0, 2), pos(9, 0, 2), pos(7, 0, 2), pos(5, 0, 2), pos(3, 0, 2), pos(1, 0, 2)},
                new RamGate());
    }

    @Override
    protected boolean[] inputsToUse(boolean[] inputs) {
        boolean[] res = new boolean[inputs.length - 1];
        System.arraycopy(inputs, 1, res, 0, res.length);
        return res;
    }

    @Override
    public boolean execute(FirstPlugin plugin, Player player, String[] params) {
        Block base = getFirstBlock(player);
        Direction direction = getForth(player);
        return super.execute(plugin, player, params);
    }

    @Override
    public void init(Plugin plugin, Block base, Direction direction) {
        ((RamGate) gate).base = base;
        ((RamGate) gate).direction = direction;
    }
}