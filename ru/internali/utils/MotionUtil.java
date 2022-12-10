//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;

public class MotionUtil
{
    private static final Minecraft mc;
    
    public static boolean isMoving() {
        return MotionUtil.mc.player.motionX > 0.0 || MotionUtil.mc.player.motionX < -0.0 || MotionUtil.mc.player.motionZ > 0.0 || MotionUtil.mc.player.motionZ < -0.0;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public static double getBaseMoveSpeed() {
        double n = 0.2873;
        if (MotionUtil.mc.player != null && MotionUtil.mc.player.isPotionActive(Potion.getPotionById(1))) {
            n *= 1.0 + 0.2 * (MotionUtil.mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier() + 1);
        }
        return n;
    }
    
    public static boolean isMoving(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.moveForward != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }
    
    public static double[] forward(final double n) {
        float moveForward = MotionUtil.mc.player.movementInput.moveForward;
        float moveStrafe = MotionUtil.mc.player.movementInput.moveStrafe;
        float n2 = MotionUtil.mc.player.prevRotationYaw + (MotionUtil.mc.player.rotationYaw - MotionUtil.mc.player.prevRotationYaw) * MotionUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static void setSpeed(final EntityLivingBase entityLivingBase, final double n) {
        final double[] forward = forward(n);
        entityLivingBase.motionX = forward[0];
        entityLivingBase.motionZ = forward[1];
    }
    
    public static double getSpeed(final EntityLivingBase entityLivingBase) {
        return Math.sqrt(entityLivingBase.motionX * entityLivingBase.motionX + entityLivingBase.motionZ * entityLivingBase.motionZ);
    }
}
