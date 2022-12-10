//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class EventPreMotionUpdate extends Event
{
    private float pitch;
    private float yaw;
    public double z;
    private boolean ground;
    public double y;
    public double x;
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public boolean onGround() {
        return this.ground;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public EventPreMotionUpdate(final float yaw, final float pitch, final boolean ground, final double x, final double y, final double z) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.ground = ground;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setGround(final boolean ground) {
        this.ground = ground;
    }
}
