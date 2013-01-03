package ch.stni.bukkit.first;

import static org.bukkit.Material.REDSTONE_ORE;
import static org.bukkit.Material.REDSTONE_TORCH_OFF;
import static org.bukkit.Material.STONE;

import org.bukkit.Material;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class SevenSegmentCommando extends GateCommando {
    public SevenSegmentCommando() {
        super(
                new Position[]{pos(0, 0, -1), pos(2, 0, -1), pos(4, 0, -1), pos(6, 0, -1), pos(8, 0, -1), pos(10, 0, -1), pos(12, 0, -1)},
                new int[]{0, 1, 2, 3, 4, 5, 6},
                new Material[][][]{{
                        {REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE, STONE, REDSTONE_ORE}},
                        {
                                {STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE, STONE},
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
                                {STONE, STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, REDSTONE_TORCH_OFF, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, REDSTONE_TORCH_OFF, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE},
                                {STONE, STONE, STONE, STONE, STONE, REDSTONE_TORCH_OFF, REDSTONE_TORCH_OFF, REDSTONE_TORCH_OFF, STONE, STONE, STONE, STONE, STONE},
                        }
                },
                new Position[]{pos(1, 0, 2)},
                new XorGate());
    }
}