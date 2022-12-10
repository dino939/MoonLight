//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.util.math.*;

class Circle
{
    private final Vec3d vec;
    private final Vec3d color;
    byte existed;
    
    Vec3d position() {
        return this.vec;
    }
    
    Circle(final Vec3d vec, final Vec3d color) {
        this.vec = vec;
        this.color = color;
    }
    
    Vec3d color() {
        return this.color;
    }
    
    boolean update() {
        final byte existed = (byte)(this.existed + 1);
        this.existed = existed;
        return existed > 20;
    }
}
