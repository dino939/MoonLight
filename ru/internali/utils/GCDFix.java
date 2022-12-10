//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;

public class GCDFix
{
    private float pitch;
    private float yaw;
    static Minecraft mc;
    
    public final float getPitch() {
        return this.pitch;
    }
    
    @Override
    public int hashCode() {
        return Float.hashCode(this.yaw) * 31 + Float.hashCode(this.pitch);
    }
    
    static {
        GCDFix.mc = Minecraft.getMinecraft();
    }
    
    public static float getFixedRotation(final float n) {
        return getDeltaMouse(n) * getGCDValue();
    }
    
    public final float getYaw() {
        return this.yaw;
    }
    
    public static float getGCDValue() {
        return (float)(getGCD() * 0.15);
    }
    
    public final void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public GCDFix(final float yaw, final float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public static float getDeltaMouse(final float n) {
        return (float)Math.round(n / getGCDValue());
    }
    
    public static float getGCD() {
        final float n;
        return (n = (float)(GCDFix.mc.gameSettings.mouseSensitivity * 0.6 + 0.2)) * n * n * 8.0f;
    }
    
    public final void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GCDFix)) {
            return false;
        }
        final GCDFix gcdFix = (GCDFix)o;
        return Float.compare(this.yaw, gcdFix.yaw) == 0 && Float.compare(this.pitch, gcdFix.pitch) == 0;
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("Rotation(yaw=").append(this.yaw).append(", pitch=").append(this.pitch).append(")"));
    }
}
