package ch.stni.bukkit.first;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class XorGate implements Gate {
    public boolean[] apply(boolean[] inputs) {
        return new boolean[]{inputs[0] ^ inputs[1]};
    }
}
