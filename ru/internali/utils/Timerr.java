//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class Timerr
{
    private long prevMS;
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public void setDifference(final long n) {
        this.prevMS = this.getTime() - n;
    }
    
    public void reset() {
        this.prevMS = this.getTime();
    }
    
    public Timerr() {
        this.prevMS = 0L;
    }
    
    public long getDifference() {
        return this.getTime() - this.prevMS;
    }
    
    public boolean delay(final float n) {
        return this.getTime() - this.prevMS >= n;
    }
}
