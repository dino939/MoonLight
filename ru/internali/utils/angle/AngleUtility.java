//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

import java.util.*;
import net.minecraft.client.*;
import ru.internali.utils.*;
import net.minecraft.entity.*;

public class AngleUtility
{
    private Vector3 delta;
    private final Random random;
    private static Minecraft minecraft;
    private float minPitchSmoothing;
    private float maxPitchSmoothing;
    private float maxYawSmoothing;
    private float height;
    private float minYawSmoothing;
    private Angle smoothedAngle;
    
    public void setHeight(final float height) {
        this.height = height;
    }
    
    public AngleUtility(final float minYawSmoothing, final float maxYawSmoothing, final float minPitchSmoothing, final float maxPitchSmoothing) {
        this.height = (float)MathUtils.getRandomInRange(1.100000023841858, 1.7999999523162842);
        this.minYawSmoothing = minYawSmoothing;
        this.maxYawSmoothing = maxYawSmoothing;
        this.minPitchSmoothing = minPitchSmoothing;
        this.maxPitchSmoothing = maxPitchSmoothing;
        this.random = new Random();
        this.delta = new Vector3(0.0f, 0.0f, 0.0f);
        this.smoothedAngle = new Angle(0.0f, 0.0f);
    }
    
    static {
        AngleUtility.minecraft = Minecraft.getMinecraft();
    }
    
    public float randomFloat(final float n, final float n2) {
        return n + this.random.nextFloat() * (n2 - n);
    }
    
    public Angle smoothAngle(final Angle angle, final Angle angle2, final float n, final float n2) {
        return this.smoothedAngle.setYaw(angle2.getYaw() - angle.getYaw() - ((Math.abs(angle2.getYaw() - angle.getYaw()) > 5.0f) ? (Math.abs(angle2.getYaw() - angle.getYaw()) / Math.abs(angle2.getYaw() - angle.getYaw()) * 2.0f / 2.0f) : 0.0f)).setPitch(angle2.getPitch() - angle.getPitch()).constrantAngle().setYaw(angle2.getYaw() - this.smoothedAngle.getYaw() / n2 * this.randomFloat(this.minYawSmoothing, this.maxYawSmoothing)).constrantAngle().setPitch(angle2.getPitch() - this.smoothedAngle.getPitch() / n * this.randomFloat(this.minPitchSmoothing, this.maxPitchSmoothing)).constrantAngle();
    }
    
    public static float[] getRotations(final EntityLivingBase entityLivingBase) {
        return getRotationFromPosition(entityLivingBase.posX, entityLivingBase.posZ, entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f);
    }
    
    public Angle calculateAngle(final Vector3 vector3, final Vector3 vector4, final EntityLivingBase entityLivingBase, final int n) {
        final Angle angle = new Angle(0.0f, 0.0f);
        this.delta.setX(vector3.getX().floatValue() - vector4.getX().floatValue()).setY(vector3.getY().floatValue() + this.height - (vector4.getY().floatValue() + this.height)).setZ(vector3.getZ().floatValue() - vector4.getZ().floatValue());
        final double hypot = Math.hypot(this.delta.getX().doubleValue(), this.delta.getZ().doubleValue());
        final float n2 = (float)Math.atan2(this.delta.getZ().floatValue(), this.delta.getX().floatValue());
        final float n3 = (float)Math.atan2(this.delta.getY().floatValue(), hypot);
        final float n4 = 57.29578f;
        return angle.setYaw(n2 * n4 - 90.0f).setPitch(-(n3 * n4)).constrantAngle();
    }
    
    public static float[] getRotationFromPosition(final double n, final double n2, final double n3) {
        final double x = n - Minecraft.getMinecraft().player.posX;
        final double y = n2 - Minecraft.getMinecraft().player.posZ;
        return new float[] { (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(n3 - Minecraft.getMinecraft().player.posY - 1.2, MathUtils.sqrt_double(x * x + y * y)) * 180.0 / 3.141592653589793)) };
    }
    
    public static class Angle extends Vector2
    {
        public int calls;
        public float lastPitch;
        public int requests;
        
        public Angle setYaw(final Float x) {
            this.setX(x);
            return this;
        }
        
        public Float getPitch() {
            final float floatValue = this.getY().floatValue();
            this.lastPitch = floatValue;
            return floatValue;
        }
        
        public Angle(final Float n, final Float n2) {
            super(n, n2);
        }
        
        public Float getYaw() {
            return this.getX().floatValue();
        }
        
        public Angle setPitch(final Float y) {
            this.setY(y);
            return this;
        }
        
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
    }
}
