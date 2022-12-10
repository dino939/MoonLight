//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class AngleUtil
{
    private static Minecraft mc;
    
    public static float[] calculateAngles(final Entity entity) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), InterpolationUtil.interpolateEntityTime(entity, AngleUtil.mc.getRenderPartialTicks()));
    }
    
    public static float calculateAngleDifference(final float n, final float n2, final double n3, final int n4) {
        return (float)(n - n2 / (n3 * n4));
    }
    
    public static float calculateAngleDifference(final float n, final float n2) {
        final float n3 = Math.abs(n2 - n) % 360.0f;
        return (n3 > 180.0f) ? (360.0f - n3) : n3;
    }
    
    public static float[] calculateAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d2.z - vec3d.z, vec3d2.x - vec3d.x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2((vec3d2.y - vec3d.y) * -1.0, MathHelper.sqrt(Math.pow(vec3d2.x - vec3d.x, 2.0) + Math.pow(vec3d2.z - vec3d.z, 2.0))))) };
    }
    
    public static float[] calculateAngles(final BlockPos blockPos) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), new Vec3d((Vec3i)blockPos));
    }
    
    static {
        AngleUtil.mc = Minecraft.getMinecraft();
    }
    
    public static float[] calculateCenter(final BlockPos blockPos) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), new Vec3d((Vec3i)blockPos).add(new Vec3d(0.5, 0.0, 0.5)));
    }
    
    public static float[] calculateCenter(final Entity entity) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), InterpolationUtil.interpolateEntityTime(entity, AngleUtil.mc.getRenderPartialTicks()).add(new Vec3d((double)(entity.width / 2.0f), (double)(entity.getEyeHeight() / 2.0f), (double)(entity.width / 2.0f))));
    }
}
