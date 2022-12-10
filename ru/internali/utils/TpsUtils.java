//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.util.math.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;

public class TpsUtils
{
    private long timeLastTimeUpdate;
    private static final float[] tickRates;
    private int nextIndex;
    
    private void onTimeUpdate() {
        if (this.timeLastTimeUpdate != -1L) {
            TpsUtils.tickRates[this.nextIndex % TpsUtils.tickRates.length] = MathHelper.clamp(20.0f / ((System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f), 0.0f, 20.0f);
            ++this.nextIndex;
        }
        this.timeLastTimeUpdate = System.currentTimeMillis();
    }
    
    public static float getTickRate() {
        float n = 0.0f;
        float n2 = 0.0f;
        for (final float n3 : TpsUtils.tickRates) {
            if (n3 > 0.0f) {
                n2 += n3;
                ++n;
            }
        }
        return MathHelper.clamp(n2 / n, 0.0f, 20.0f);
    }
    
    @SubscribeEvent
    public void onUpdate(final PacketEvent packetEvent) {
        if (PacketEvent.getPacket() instanceof SPacketTimeUpdate) {
            this.onTimeUpdate();
        }
    }
    
    public TpsUtils() {
        this.nextIndex = 0;
        this.nextIndex = 0;
        this.timeLastTimeUpdate = -1L;
        Arrays.fill(TpsUtils.tickRates, 0.0f);
    }
    
    public void voids() {
    }
    
    static {
        tickRates = new float[20];
    }
}
