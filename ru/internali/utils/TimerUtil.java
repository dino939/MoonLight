//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class TimerUtil
{
    public long lastMS;
    private long previousMS;
    
    public void reset() {
        this.previousMS = this.getTime();
    }
    
    public TimerUtil() {
        this.previousMS = 0L;
    }
    
    public boolean hasReached(final double n) {
        return this.getTime() - this.previousMS >= n;
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean delay(final float n) {
        return this.getTime() - this.lastMS >= n;
    }
}
