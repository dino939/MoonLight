//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import ru.internali.utils.*;

public class trail
{
    double x;
    double y;
    TimerUtils timer;
    double z;
    
    public double getX() {
        return this.x;
    }
    
    public TimerUtils getTimer() {
        return this.timer;
    }
    
    public trail(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.timer = new TimerUtils();
    }
    
    public double getZ() {
        return this.z;
    }
    
    public double getY() {
        return this.y;
    }
}
