//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

public class Vector
{
    private Number z;
    private Number x;
    private Number y;
    
    public Number getZ() {
        return this.z;
    }
    
    public Vector setY(final Number y) {
        this.y = y;
        return this;
    }
    
    public Number getX() {
        return this.x;
    }
    
    public Vector(final Number x, final Number y, final Number z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector setZ(final Number z) {
        this.z = z;
        return this;
    }
    
    public Number getY() {
        return this.y;
    }
    
    public Vector setX(final Number x) {
        this.x = x;
        return this;
    }
}
