//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class EventStep extends Event
{
    private double realHeight;
    private double stepHeight;
    private boolean pre;
    private boolean active;
    
    public EventStep() {
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public boolean isPre() {
        return this.pre;
    }
    
    public double getStepHeight() {
        return this.stepHeight;
    }
    
    public EventStep(final boolean pre, final double n) {
        this.pre = pre;
        this.realHeight = n;
        this.stepHeight = n;
    }
    
    public void setStepHeight(final double stepHeight) {
        this.stepHeight = stepHeight;
    }
    
    public void setRealHeight(final double realHeight) {
        this.realHeight = realHeight;
    }
    
    public double getRealHeight() {
        return this.realHeight;
    }
    
    public EventStep(final boolean pre, final double stepHeight, final double realHeight) {
        this.pre = pre;
        this.stepHeight = stepHeight;
        this.realHeight = realHeight;
    }
}
