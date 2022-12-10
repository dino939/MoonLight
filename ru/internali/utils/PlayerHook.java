//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class PlayerHook
{
    float yaw;
    float pitch;
    double y;
    boolean onGround;
    double z;
    double x;
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public static void setYaw(float n) {
        n = n;
    }
    
    public static void setPitch(float n) {
        n = n;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public PlayerHook(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
}
