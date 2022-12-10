//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class RotationUtils
{
    public static float[] getRotationFromPosition(final double n, final double n2, final double n3) {
        final double x = n - Minecraft.getMinecraft().player.posX;
        final double y = n2 - Minecraft.getMinecraft().player.posZ;
        return new float[] { (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(n3 - Minecraft.getMinecraft().player.posY - 0.6, MathHelper.sqrt(x * x + y * y)) * 180.0 / 3.141592653589793)) };
    }
    
    public static float[] getNeededRotations(final Entity entity) {
        final double n = entity.posX - Minecraft.getMinecraft().player.posX;
        final double n2 = entity.posZ - Minecraft.getMinecraft().player.posZ;
        return new float[] { (float)(MathHelper.atan2(n2, n) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(MathHelper.atan2(entity.posY + entity.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY)), (double)MathHelper.sqrt(n * n + n2 * n2)) * 180.0 / 3.141592653589793)) };
    }
    
    public static float[] getRotations(final EntityLivingBase entityLivingBase, final String s) {
        if (s == "Head") {
            return getRotationFromPosition(entityLivingBase.posX, entityLivingBase.posZ, entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f);
        }
        if (s == "Chest") {
            return getRotationFromPosition(entityLivingBase.posX, entityLivingBase.posZ, entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 0.75);
        }
        if (s == "Dick") {
            return getRotationFromPosition(entityLivingBase.posX, entityLivingBase.posZ, entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 1.2);
        }
        if (s == "Legs") {
            return getRotationFromPosition(entityLivingBase.posX, entityLivingBase.posZ, entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 1.5);
        }
        return getRotationFromPosition(entityLivingBase.posX, entityLivingBase.posZ, entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 0.5);
    }
    
    public static float[] getAverageRotations(final List list) {
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        for (final Entity entity : list) {
            n += entity.posX;
            n2 += entity.getEntityBoundingBox().maxY - 2.0;
            n3 += entity.posZ;
        }
        final float[] array = new float[2];
        final double n4 = n / list.size();
        final double n5 = n3 / list.size();
        final double n6;
        array[0] = getRotationFromPosition(n4, n5, n6 = n2 / list.size())[0];
        array[1] = getRotationFromPosition(n4, n5, n6)[1];
        return array;
    }
    
    public static float getNewAngle(float n) {
        if ((n %= 360.0f) >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    public static float changeRotation(final float n, final float n2) {
        float wrapAngleTo180_float = MathUtils.wrapAngleTo180_float(n2 - n);
        if (wrapAngleTo180_float > 1000.0f) {
            wrapAngleTo180_float = 1000.0f;
        }
        if (wrapAngleTo180_float < -1000.0f) {
            wrapAngleTo180_float = -1000.0f;
        }
        return n + wrapAngleTo180_float;
    }
    
    public static float[] getRotations(final Entity entity) {
        final double x = entity.posX - MinecraftHelper.mc.player.posX;
        final double y = entity.posZ - MinecraftHelper.mc.player.posZ;
        double n;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            n = entityLivingBase.posY + entityLivingBase.getEyeHeight() - MinecraftHelper.mc.player.posY + MinecraftHelper.mc.player.getEyeHeight();
        }
        else {
            n = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - MinecraftHelper.mc.player.posY + MinecraftHelper.mc.player.getEyeHeight();
        }
        return new float[] { changeRotation(MinecraftHelper.mc.player.rotationYaw, (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f), changeRotation(MinecraftHelper.mc.player.rotationPitch, (float)(-(Math.atan2(n - ((entity instanceof EntityPlayer) ? 0.25 : 0.0), MathUtils.sqrt_double(x * x + y * y)) * 180.0 / 3.141592653589793))) };
    }
    
    public static float getTrajAngleSolutionLow(final float n, final float n2, final float n3) {
        return (float)Math.toDegrees(Math.atan((n3 * n3 - Math.sqrt(n3 * n3 * n3 * n3 - 0.006f * (0.006f * (n * n) + 2.0f * n2 * (n3 * n3)))) / (0.006f * n)));
    }
    
    public static float getDistanceBetweenAngles(final float n, final float n2) {
        float n3 = Math.abs(n - n2) % 360.0f;
        if (n3 > 180.0f) {
            n3 = 360.0f - n3;
        }
        return n3;
    }
}
