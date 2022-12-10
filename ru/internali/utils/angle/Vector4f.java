//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

import java.io.*;
import javax.vecmath.*;

public class Vector4f extends Tuple4f implements Serializable
{
    static final long serialVersionUID = 8749319902347760659L;
    
    public final void normalize() {
        final float n = (float)(1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w));
        this.x *= n;
        this.y *= n;
        this.z *= n;
        this.w *= n;
    }
    
    public final float angle(final Vector4f vector4f) {
        double a = this.dot(vector4f) / (this.length() * vector4f.length());
        if (a < -1.0) {
            a = -1.0;
        }
        if (a > 1.0) {
            a = 1.0;
        }
        return (float)Math.acos(a);
    }
    
    public final void set(final Tuple3f tuple3f) {
        this.x = tuple3f.x;
        this.y = tuple3f.y;
        this.z = tuple3f.z;
        this.w = 0.0f;
    }
    
    public Vector4f(final float[] array) {
        super(array);
    }
    
    public Vector4f(final Vector4d vector4d) {
        super((Tuple4d)vector4d);
    }
    
    public Vector4f(final Tuple3f tuple3f) {
        super(tuple3f.x, tuple3f.y, tuple3f.z, 0.0f);
    }
    
    public final void normalize(final Vector4f vector4f) {
        final float n = (float)(1.0 / Math.sqrt(vector4f.x * vector4f.x + vector4f.y * vector4f.y + vector4f.z * vector4f.z + vector4f.w * vector4f.w));
        this.x = vector4f.x * n;
        this.y = vector4f.y * n;
        this.z = vector4f.z * n;
        this.w = vector4f.w * n;
    }
    
    public Vector4f() {
    }
    
    public Vector4f(final Vector4f vector4f) {
        super((Tuple4f)vector4f);
    }
    
    public Vector4f(final Tuple4f tuple4f) {
        super(tuple4f);
    }
    
    public Vector4f(final float n, final float n2, final float n3, final float n4) {
        super(n, n2, n3, n4);
    }
    
    public Vector4f(final Tuple4d tuple4d) {
        super(tuple4d);
    }
    
    public final float dot(final Vector4f vector4f) {
        return this.x * vector4f.x + this.y * vector4f.y + this.z * vector4f.z + this.w * vector4f.w;
    }
    
    public final float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }
    
    public final float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }
}
