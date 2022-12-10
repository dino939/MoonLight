//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

import java.io.*;
import javax.vecmath.*;

public class Vector3f extends Tuple3f implements Serializable
{
    static final long serialVersionUID = -7031930069184524614L;
    
    public Vector3f(final Vector3f vector3f) {
        super((Tuple3f)vector3f);
    }
    
    public Vector3f(final float[] array) {
        super(array);
    }
    
    public Vector3f(final Tuple3d tuple3d) {
        super(tuple3d);
    }
    
    public final void cross(final Vector3f vector3f, final Vector3f vector3f2) {
        final float x = vector3f.y * vector3f2.z - vector3f.z * vector3f2.y;
        final float y = vector3f2.x * vector3f.z - vector3f2.z * vector3f.x;
        this.z = vector3f.x * vector3f2.y - vector3f.y * vector3f2.x;
        this.x = x;
        this.y = y;
    }
    
    public Vector3f(final Tuple3f tuple3f) {
        super(tuple3f);
    }
    
    public final float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    public final float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
    
    public Vector3f(final Vector3d vector3d) {
        super((Tuple3d)vector3d);
    }
    
    public final float dot(final Vector3f vector3f) {
        return this.x * vector3f.x + this.y * vector3f.y + this.z * vector3f.z;
    }
    
    public final void normalize() {
        final float n = (float)(1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));
        this.x *= n;
        this.y *= n;
        this.z *= n;
    }
    
    public Vector3f() {
    }
    
    public final float angle(final Vector3f vector3f) {
        double a = this.dot(vector3f) / (this.length() * vector3f.length());
        if (a < -1.0) {
            a = -1.0;
        }
        if (a > 1.0) {
            a = 1.0;
        }
        return (float)Math.acos(a);
    }
    
    public final void normalize(final Vector3f vector3f) {
        final float n = (float)(1.0 / Math.sqrt(vector3f.x * vector3f.x + vector3f.y * vector3f.y + vector3f.z * vector3f.z));
        this.x = vector3f.x * n;
        this.y = vector3f.y * n;
        this.z = vector3f.z * n;
    }
    
    public Vector3f(final float n, final float n2, final float n3) {
        super(n, n2, n3);
    }
}
