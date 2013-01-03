package ch.stni.bukkit.first;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class DisplayGate implements Gate {
    private boolean sign;
    public boolean[] apply(boolean[] inputs) {
        int in = Utils.inputAsInt(inputs);
        return Utils.outputFromInt(new boolean[8], ++in);
    }
}
