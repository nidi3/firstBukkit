package ch.stni.bukkit.first;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 25.09.11
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class CylinderCommando extends AbstractFieldCommando implements Commando {
    private final static Logger log = Logger.getLogger("Minecraft");

    @Override
    public int size() {
        return 30;
    }

    @Override
    public boolean fill(int x, int y, int z) {
        return (x - size() / 2) * (x - size() / 2) + (z - size() / 2) * (z - size() / 2) < size() / 2 * size() / 3;
    }



}
