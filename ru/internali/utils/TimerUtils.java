//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class TimerUtils
{
    private long lastMS;
    private long prevMS;
    long MC;
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean isDelay(final long n) {
        return System.currentTimeMillis() - this.lastMS >= n;
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public int convertToMS(final int n) {
        return 1000 / n;
    }
    
    public boolean hasReached(final float n) {
        return this.getCurrentMS() - this.lastMS >= n;
    }
    
    public void setLastMS(final long lastMS) {
        this.lastMS = lastMS;
    }
    
    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }
    
    public TimerUtils() {
        this.lastMS = 0L;
        this.MC = System.currentTimeMillis();
        this.prevMS = 0L;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
    
    public boolean delay(final float n) {
        return this.getTime() - this.prevMS >= n;
    }
    
    public boolean isDeley(final long n) {
        return System.currentTimeMillis() - this.MC >= n;
    }
}
