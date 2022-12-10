//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

public class Vector3 extends Vector
{
    public Vector3(final Number n, final Number n2, final Number n3) {
        super(n, n2, n3);
    }
    
    public Vector2 toVector2() {
        return new Vector2(this.getX(), this.getY());
    }
}
