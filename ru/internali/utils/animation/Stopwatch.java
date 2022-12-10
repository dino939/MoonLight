//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.animation;

public final class Stopwatch
{
    private long ms;
    
    public final long getElapsedTime() {
        return this.getCurrentMS() - this.ms;
    }
    
    public final boolean elapsed(final long n) {
        return this.getCurrentMS() - this.ms > n;
    }
    
    public Stopwatch() {
        this.ms = this.getCurrentMS();
    }
    
    public final void reset() {
        this.ms = this.getCurrentMS();
    }
    
    private long getCurrentMS() {
        return System.currentTimeMillis();
    }
}
