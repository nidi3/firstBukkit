package net.minecraft.server;/* Decompiled through IntelliJad */// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.// Jad home page: http://www.kpdus.com/jad.html// Decompiler options: packfields(3) packimports(3) splitstr(64) lnc radix(10) lradix(10) // Source File Name:   SourceFilepackage net.minecraft.server;import java.io.DataInput;import java.io.DataOutput;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

// Referenced classes of package net.minecraft.server:
//            NBTBase, NBTTagByte, NBTTagShort, NBTTagInt, 
//            NBTTagLong, NBTTagFloat, NBTTagDouble, NBTTagString, 
//            NBTTagByteArray, NBTTagList

public class NBTTagCompound extends NBTBase {
    public static Logger log = Logger.getLogger("Minecraft");

            public NBTTagCompound() {
        a = new HashMap();            }
            void a(DataOutput dataoutput){
                NBTBase nbtbase;
        for(Iterator iterator = a.values().iterator(); iterator.hasNext(); NBTBase.a(nbtbase, dataoutput)) nbtbase = (NBTBase)iterator.next();


                try {
                    dataoutput.writeByte(0);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            void a(DataInput datainput) {
        a.clear();
                NBTBase nbtbase;
        for(; (nbtbase = NBTBase.b(datainput)).a() != 0; a.put(nbtbase.b(), nbtbase));
            }



            public Collection c() {
        return a.values();
            }

            public byte a() {
        return 10;
            }

            public void a(String s, NBTBase nbtbase) {
        a.put(s, nbtbase.a(s));
            }

            public void a(String s, byte byte0) {
        a.put(s, (new NBTTagByte(byte0)).a(s));
            }

            public void a(String s, short word0) {
        a.put(s, (new NBTTagShort(word0)).a(s));
            }

            public void a(String s, int i) {
        a.put(s, (new NBTTagInt(i)).a(s));
            }

            public void setLong(String s, long l1) {
        a.put(s, (new NBTTagLong(l1)).a(s));
            }

            public void a(String s, float f) {
        a.put(s, (new NBTTagFloat(f)).a(s));
            }

            public void a(String s, double d1) {
        a.put(s, (new NBTTagDouble(d1)).a(s));
            }

            public void setString(String s, String s1) {
                if (s.startsWith("Text")){
                log.info("set string "+s+"->"+s1);
                }
        a.put(s, (new NBTTagString(s1)).a(s));

            }

            public void a(String s, byte abyte0[]) {
        a.put(s, (new NBTTagByteArray(abyte0)).a(s));
            }

            public void a(String s, NBTTagCompound nbttagcompound) {
        a.put(s, nbttagcompound.a(s));
            }

            public void a(String s, boolean flag) {
        a(s, ((byte)(flag ? 1 : 0)));
            }





            public boolean hasKey(String s) {
        return a.containsKey(s);
            }

            public byte c(String s) {
        if(!a.containsKey(s)) return 0;
        else return ((NBTTagByte)a.get(s)).a;
            }

            public short d(String s) {
        if(!a.containsKey(s)) return 0;
        else return ((NBTTagShort)a.get(s)).a;
            }

            public int e(String s) {
        if(!a.containsKey(s)) return 0;
        else return ((NBTTagInt)a.get(s)).a;
            }

            public long getLong(String s) {
        if(!a.containsKey(s)) return 0L;
        else return ((NBTTagLong)a.get(s)).a;
            }

            public float g(String s) {
        if(!a.containsKey(s)) return 0.0F;
        else return ((NBTTagFloat)a.get(s)).a;
            }

            public double h(String s) {
        if(!a.containsKey(s)) return 0.0D;
        else return ((NBTTagDouble)a.get(s)).a;
            }

            public String getString(String s) {
        if(!a.containsKey(s)) return "";
        else return ((NBTTagString)a.get(s)).a;
            }

            public byte[] j(String s) {
        if(!a.containsKey(s)) return new byte[0];
        else return ((NBTTagByteArray)a.get(s)).a;
            }

            public NBTTagCompound k(String s) {
        if(!a.containsKey(s)) return new NBTTagCompound();
        else return (NBTTagCompound)a.get(s);
            }


            public NBTTagList l(String s) {
        if(!a.containsKey(s)) return new NBTTagList();
        else return (NBTTagList)a.get(s);
            }

            public boolean m(String s) {
        return c(s) != 0;
            }

            public String toString() {
        return (new StringBuilder()).append("").append(a.size()).append(" entries").toString();
            }

            private Map a;
}