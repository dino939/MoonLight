//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

public class Angle extends Vector2
{
    public Angle constrantAngle() {
        this.setYaw(this.getYaw() % 360.0f);
        this.setPitch(this.getPitch() % 360.0f);
        while (this.getYaw() <= -180.0f) {
            this.setYaw(this.getYaw() + 360.0f);
        }
        while (this.getPitch() <= -180.0f) {
            this.setPitch(this.getPitch() + 360.0f);
        }
        while (this.getYaw() > 180.0f) {
            this.setYaw(this.getYaw() - 360.0f);
        }
        while (this.getPitch() > 180.0f) {
            this.setPitch(this.getPitch() - 360.0f);
        }
        return this;
    }
    
    public Float getPitch() {
        return this.getY().floatValue();
    }
    
    public Angle setPitch(final Float y) {
        this.setY(y);
        return this;
    }
    
    public Angle setYaw(final Float x) {
        this.setX(x);
        return this;
    }
    
    public Float getYaw() {
        return this.getX().floatValue();
    }
    
    public Angle(final Float n, final Float n2) {
        super(n, n2);
    }
}
