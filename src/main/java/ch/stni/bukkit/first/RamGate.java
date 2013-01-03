package ch.stni.bukkit.first;

import static org.bukkit.Material.SIGN_POST;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 29.09.11
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class RamGate implements Gate {
    private final static Logger log = Logger.getLogger("Minecraft");
    Block base;
    Direction direction;

    public boolean[] apply(boolean[] inputs) {
        int in = Utils.inputAsInt(inputs);
        //in=(int)(Math.random()*256);
      //  log.info("ram in " + in);
        in = getByteAt(in);
        boolean[] out = Utils.outputFromInt(new boolean[8], in);
       // log.info(System.nanoTime() / 1000000 + " ram out " + Integer.toHexString(in));
        return out;
    }

    public int getByteAt(int pos) {
        Block src = direction.from(direction.right().from(Direction.UP.from(base, 1), (pos % 256) / 16), pos / 256);
        if (src.getType() != SIGN_POST) {
            return 0;
        }
        Sign sign = (Sign) (src.getState());
        String line = sign.getLine((pos % 16) / 4);
        if (line == null) {
            return 0;
        }
        String[] bytes = line.trim().split(" ");
        if (bytes.length <= pos % 4) {
            return 0;
        }
        String bs = bytes[pos % 4];
        try {
            return bs.length() > 0 ? Integer.parseInt(bs, 16) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
