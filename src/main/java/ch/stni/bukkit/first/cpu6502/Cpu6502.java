package ch.stni.bukkit.first.cpu6502;

import java.util.EnumSet;

/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 08.10.11
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class Cpu6502 {
    private enum State {NO, RESET_WAIT}

    private enum Flag {NEGATIVE, OVERFLOW, UNUSED, BREAK, DECIMAL, INTERRUPT, ZERO, CARRY}


    private State state = State.NO;
    private byte a;
    private byte x;
    private byte y;
    private EnumSet<Flag> flags = EnumSet.noneOf(Flag.class);
    private int pc;
    private short sp;

//    private boolean dataOut;

    public static class Output {
        private short adr;
        private byte data;
        private boolean rw;

        private Output(byte data, short adr, boolean rw) {
            this.data = data;
            this.adr = adr;
            this.rw = rw;
        }

        static Output read(int adr) {
            return new Output((byte) 0, (short) adr, true);
        }

        static Output write(int adr, int data) {
            return new Output((byte) data, (short) adr, false);
        }
    }

    public Output step(boolean irq, boolean nmi, byte data, boolean so, boolean res) {
        if (!res) {
            if (state == State.RESET_WAIT) {
                pc = data;
                state = State.NO;
            } else {
               // dataOut = true;
                state = State.RESET_WAIT;
                return Output.read(0xfffc);
            }
        }
        if (!so) {
            flags.add(Flag.OVERFLOW);
        }
        Opcode op = Opcode.of(data);
        switch (op) {
            case TAX:
                x = a;
                pc++;
                break;
            case TAY:
                y = a;
                pc++;
                break;
            case TXA:
                a = x;
                pc++;
                break;
            case TYA:
                y = a;
                pc++;
                break;
        }
        return null;
    }
}
