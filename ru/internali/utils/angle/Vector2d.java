//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

import java.io.*;
import javax.vecmath.*;

public class Vector2d extends Tuple2d implements Serializable
{
    static final long serialVersionUID = 8572646365302599857L;
    
    public Vector2d(final Vector2f vector2f) {
        super((Tuple2f)vector2f);
    }
    
    public Vector2d(final Vector2d vector2d) {
        super((Tuple2d)vector2d);
    }
    
    public Vector2d() {
    }
    
    public Vector2d(final double n, final double n2) {
        super(n, n2);
    }
    
    public final double lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }
    
    public final double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    
    public Vector2d(final double[] array) {
        super(array);
    }
    
    public final void normalize() {
        final double n = 1.0 / Math.sqrt(this.x * this.x + this.y * this.y);
        this.x *= n;
        this.y *= n;
    }
    
    public final void normalize(final Vector2d vector2d) {
        final double n = 1.0 / Math.sqrt(vector2d.x * vector2d.x + vector2d.y * vector2d.y);
        this.x = vector2d.x * n;
        this.y = vector2d.y * n;
    }
    
    public Vector2d(final Tuple2d tuple2d) {
        super(tuple2d);
    }
    
    public Vector2d(final Tuple2f tuple2f) {
        super(tuple2f);
    }
    
    public final double angle(final Vector2d vector2d) {
        double a = this.dot(vector2d) / (this.length() * vector2d.length());
        if (a < -1.0) {
            a = -1.0;
        }
        if (a > 1.0) {
            a = 1.0;
        }
        return Math.acos(a);
    }
    
    public final double dot(final Vector2d vector2d) {
        return this.x * vector2d.x + this.y * vector2d.y;
    }
}
