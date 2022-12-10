//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.angle;

import java.util.*;

public class AngleUtil
{
    private static Angle smoothedAngle;
    private static float maxYawSmoothing;
    private static float maxPitchSmoothing;
    private static float minPitchSmoothing;
    private static Random random;
    private static float minYawSmoothing;
    private static Vector3 delta;
    
    public AngleUtil(final float minYawSmoothing, final float maxYawSmoothing, final float minPitchSmoothing, final float maxPitchSmoothing) {
        AngleUtil.minYawSmoothing = minYawSmoothing;
        AngleUtil.maxYawSmoothing = maxYawSmoothing;
        AngleUtil.minPitchSmoothing = minPitchSmoothing;
        AngleUtil.maxPitchSmoothing = maxPitchSmoothing;
        AngleUtil.random = new Random();
        AngleUtil.delta = new Vector3(0.0f, 0.0f, 0.0f);
        AngleUtil.smoothedAngle = new Angle(Float.valueOf(0.0f), Float.valueOf(0.0f));
    }
    
    public static float randomFloat(final float n, final float n2) {
        return n + AngleUtil.random.nextFloat() * (n2 - n);
    }
    
    public static Angle calculateAngle(final Vector3 vector3, final Vector3 vector4) {
        final Angle angle = new Angle(Float.valueOf(0.0f), Float.valueOf(0.0f));
        final float n = 1.5f;
        AngleUtil.delta.setX(vector3.getX().floatValue() - vector4.getX().floatValue()).setY(vector3.getY().floatValue() + n - (vector4.getY().floatValue() + n)).setZ(vector3.getZ().floatValue() - vector4.getZ().floatValue());
        final double hypot = Math.hypot(AngleUtil.delta.getX().doubleValue(), AngleUtil.delta.getZ().doubleValue());
        final float n2 = (float)Math.atan2(AngleUtil.delta.getZ().floatValue(), AngleUtil.delta.getX().floatValue());
        final float n3 = (float)Math.atan2(AngleUtil.delta.getY().floatValue(), hypot);
        final float n4 = 57.29578f;
        return angle.setYaw(Float.valueOf(n2 * n4 - 90.0f)).setPitch(Float.valueOf(-(n3 * n4))).constrantAngle();
    }
    
    public static Angle smoothAngle(final Angle angle, final Angle angle2, final float n, final float n2) {
        return AngleUtil.smoothedAngle.setYaw(Float.valueOf(angle2.getYaw() - angle.getYaw())).setPitch(Float.valueOf(angle2.getPitch() - angle.getPitch())).constrantAngle().setYaw(Float.valueOf(angle2.getYaw() - AngleUtil.smoothedAngle.getYaw() / 100.0f * randomFloat(AngleUtil.minYawSmoothing, AngleUtil.maxYawSmoothing))).setPitch(Float.valueOf(angle2.getPitch() - AngleUtil.smoothedAngle.getPitch() / 100.0f * randomFloat(AngleUtil.minPitchSmoothing, AngleUtil.maxPitchSmoothing))).constrantAngle();
    }
}
