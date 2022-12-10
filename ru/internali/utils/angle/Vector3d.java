//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

import java.io.*;
import javax.vecmath.*;

public class Vector3d extends Tuple3d implements Serializable
{
    static final long serialVersionUID = 3761969948420550442L;
    
    public final void normalize() {
        final double n = 1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.x *= n;
        this.y *= n;
        this.z *= n;
    }
    
    public Vector3d(final Vector3d vector3d) {
        super((Tuple3d)vector3d);
    }
    
    public Vector3d(final Tuple3d tuple3d) {
        super(tuple3d);
    }
    
    public final void cross(final Vector3d vector3d, final Vector3d vector3d2) {
        final double x = vector3d.y * vector3d2.z - vector3d.z * vector3d2.y;
        final double y = vector3d2.x * vector3d.z - vector3d2.z * vector3d.x;
        this.z = vector3d.x * vector3d2.y - vector3d.y * vector3d2.x;
        this.x = x;
        this.y = y;
    }
    
    public Vector3d(final Vector3f vector3f) {
        super((Tuple3f)vector3f);
    }
    
    public Vector3d(final Tuple3f tuple3f) {
        super(tuple3f);
    }
    
    public Vector3d(final double n, final double n2, final double n3) {
        super(n, n2, n3);
    }
    
    public Vector3d(final double[] array) {
        super(array);
    }
    
    public final double dot(final Vector3d vector3d) {
        return this.x * vector3d.x + this.y * vector3d.y + this.z * vector3d.z;
    }
    
    public Vector3d() {
    }
    
    public final void normalize(final Vector3d vector3d) {
        final double n = 1.0 / Math.sqrt(vector3d.x * vector3d.x + vector3d.y * vector3d.y + vector3d.z * vector3d.z);
        this.x = vector3d.x * n;
        this.y = vector3d.y * n;
        this.z = vector3d.z * n;
    }
    
    public final double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    public final double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
    
    public final double angle(final Vector3d vector3d) {
        double a = this.dot(vector3d) / (this.length() * vector3d.length());
        if (a < -1.0) {
            a = -1.0;
        }
        if (a > 1.0) {
            a = 1.0;
        }
        return Math.acos(a);
    }
}
