package ch.stni.bukkit.first.cpu6502;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 08.10.11
 * Time: 00:45
 * To change this template use File | Settings | File Templates.
 */
public enum Opcode {

    TAX(0xaa), TAY(0xa8), TSX(0xba), TXA(0x8a), TYA(0x98), TXS(0x9a);


    private final int code;

    private Opcode(int code) {
        this.code = code;
    }

    public static Opcode of(int code) {
        for (Opcode op : values()) {
            if (op.code == code) {
                return op;
            }
        }
        return null;
    }
}
