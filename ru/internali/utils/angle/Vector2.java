//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

public class Vector2 extends Vector
{
    public Vector2(final Number n, final Number n2) {
        super(n, n2, (Number)0);
    }
    
    public Vector3 toVector3() {
        return new Vector3(this.getX(), this.getY(), this.getZ());
    }
}
