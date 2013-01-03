package ch.stni.bukkit.first;

import static org.bukkit.Material.LEVER;
import static org.bukkit.Material.REDSTONE_ORE;
import static org.bukkit.Material.STONE;

import org.bukkit.Material;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class XorCommando extends GateCommando {
    public XorCommando() {
        super(
                new Position[]{pos(0, 0, -1), pos(2, 0, -1)},
                new int[]{0, 1},
                new Material[][][]{{{REDSTONE_ORE, STONE, REDSTONE_ORE}}, {{STONE, STONE, STONE}}, {{STONE, LEVER, STONE}}},
                new Position[]{pos(1, 0, 2)},
                new XorGate());
    }
}