//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import ru.internali.utils.*;

public class Slient extends Event
{
    private boolean onground;
    private double y;
    private static float pitch;
    private static float yaw;
    private boolean isPre;
    
    public float getYaw() {
        return Slient.yaw;
    }
    
    public void setGround(final boolean onground) {
        this.onground = onground;
    }
    
    public boolean isPre() {
        return this.isPre;
    }
    
    public boolean isPost() {
        return !this.isPre;
    }
    
    public static void setYaw(final float yaw) {
        Slient.yaw = yaw;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public boolean isOnground() {
        return this.onground;
    }
    
    public Slient(final double y, final float yaw, final float pitch, final boolean onground) {
        this.isPre = true;
        Slient.yaw = yaw;
        Slient.pitch = pitch;
        this.y = y;
        this.onground = onground;
    }
    
    public Slient() {
        this.isPre = false;
    }
    
    public static void setPitch(final float pitch) {
        Slient.pitch = pitch;
    }
    
    public float getPitch() {
        return Slient.pitch;
    }
}
