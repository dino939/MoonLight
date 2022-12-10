//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class EventPreMotion extends Event
{
    private boolean onGround;
    private double posY;
    private float yaw;
    private double posZ;
    private float pitch;
    private double posX;
    
    public double getPosZ() {
        return this.posZ;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setPosZ(final double posZ) {
        this.posZ = posZ;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setPosY(final double posY) {
        this.posY = posY;
    }
    
    public double getPosY() {
        return this.posY;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPosX(final double posX) {
        this.posX = posX;
    }
    
    public double getPosX() {
        return this.posX;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public EventPreMotion(final float yaw, final float pitch, final double posX, final double posY, final double posZ, final boolean onGround) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.onGround = onGround;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public static void setYaw(float n) {
        n = n;
    }
}
