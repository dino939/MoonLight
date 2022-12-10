//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class Vec3dSimple
{
    public double z;
    public double x;
    public double y;
    
    public Vec3dSimple(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Vec3dSimple) {
            final Vec3dSimple vec3dSimple = (Vec3dSimple)o;
            return vec3dSimple.x == this.x && vec3dSimple.y == this.y && vec3dSimple.z == this.z;
        }
        return false;
    }
    
    public void round() {
        this.x = (double)Math.round(this.x);
        this.y = (double)Math.round(this.y);
        this.z = (double)Math.round(this.z);
    }
}
