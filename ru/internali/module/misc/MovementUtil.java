//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;

public class MovementUtil
{
    public static final double WALK_SPEED = 0.221;
    public static Minecraft mc;
    
    public static float getDirection() {
        float rotationYaw = MovementUtil.mc.player.rotationYaw;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (MovementUtil.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (MovementUtil.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (MovementUtil.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public static double getSpeed() {
        return getSpeed(false, 0.2873);
    }
    
    static {
        MovementUtil.mc = Minecraft.getMinecraft();
    }
    
    public static float getRoundedStrafing() {
        return getRoundedMovementInput(MovementUtil.mc.player.movementInput.moveStrafe);
    }
    
    public static double getMotion(final EntityPlayer entityPlayer) {
        return Math.abs(entityPlayer.motionX) + Math.abs(entityPlayer.motionZ);
    }
    
    public static void setMotion(final double n) {
        double n2 = MovementUtil.mc.player.movementInput.moveForward;
        double n3 = MovementUtil.mc.player.movementInput.moveStrafe;
        float rotationYaw = MovementUtil.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            MovementUtil.mc.player.motionX = 0.0;
            MovementUtil.mc.player.motionZ = 0.0;
        }
        else {
            if (n2 != 0.0) {
                if (n3 > 0.0) {
                    rotationYaw += ((n2 > 0.0) ? -45 : 45);
                }
                else if (n3 < 0.0) {
                    rotationYaw += ((n2 > 0.0) ? 45 : -45);
                }
                n3 = 0.0;
                if (n2 > 0.0) {
                    n2 = 1.0;
                }
                else if (n2 < 0.0) {
                    n2 = -1.0;
                }
            }
            MovementUtil.mc.player.motionX = n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
            MovementUtil.mc.player.motionZ = n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
        }
    }
    
    public static float calcMoveYaw(final float n) {
        final float roundedForward = getRoundedForward();
        final float n2 = (float)Math.toRadians(n - 90.0f * getRoundedStrafing() * ((roundedForward != 0.0f) ? (roundedForward * 0.5f) : 1.0f) - ((roundedForward < 0.0f) ? 180 : 0));
        final float n3 = 0.005f * (MovementUtil.mc.gameSettings.mouseSensitivity / 0.005f);
        return n2 - n2 % (n3 * n3 * n3 * 1.2f);
    }
    
    public static double getJumpSpeed() {
        double n = 0.0;
        if (MovementUtil.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
            n += (MovementUtil.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1;
        }
        return n;
    }
    
    private static final float getRoundedMovementInput(final float n) {
        return (n > 0.0f) ? 1.0f : ((n < 0.0f) ? -1.0f : 0.0f);
    }
    
    public static double getSpeed(final boolean b, double n) {
        if (MovementUtil.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
        }
        if (b && MovementUtil.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
            n /= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect(MobEffects.SLOWNESS)).getAmplifier() + 1);
        }
        return n;
    }
    
    public static boolean isBlockAboveHead() {
        return !MovementUtil.mc.world.getCollisionBoxes((Entity)MovementUtil.mc.player, new AxisAlignedBB(MovementUtil.mc.player.posX - 0.3, MovementUtil.mc.player.posY + MovementUtil.mc.player.getEyeHeight(), MovementUtil.mc.player.posZ + 0.3, MovementUtil.mc.player.posX + 0.3, MovementUtil.mc.player.posY + 2.5, MovementUtil.mc.player.posZ - 0.3)).isEmpty();
    }
    
    public static boolean isMoving() {
        return MovementUtil.mc.player != null && (MovementUtil.mc.player.movementInput.moveForward != 0.0f || MovementUtil.mc.player.movementInput.moveStrafe != 0.0f);
    }
    
    public static float getDirection2() {
        float rotationYaw = MovementUtil.mc.player.rotationYaw;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (MovementUtil.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (MovementUtil.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (MovementUtil.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public static void strafe(final float n, final double n2) {
        if (!isMoving()) {
            return;
        }
        MovementUtil.mc.player.motionX = -Math.sin(n) * n2;
        MovementUtil.mc.player.motionZ = Math.cos(n) * n2;
    }
    
    public static double getDistance2D() {
        final double n = MovementUtil.mc.player.posX - MovementUtil.mc.player.prevPosX;
        final double n2 = MovementUtil.mc.player.posZ - MovementUtil.mc.player.prevPosZ;
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static float getRoundedForward() {
        return getRoundedMovementInput(MovementUtil.mc.player.movementInput.moveForward);
    }
    
    public static double[] forward(final double n) {
        float moveForward = Minecraft.getMinecraft().player.movementInput.moveForward;
        float moveStrafe = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float n2 = Minecraft.getMinecraft().player.prevRotationYaw + (Minecraft.getMinecraft().player.rotationYaw - Minecraft.getMinecraft().player.prevRotationYaw) * Minecraft.getMinecraft().getRenderPartialTicks();
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
    
    public static double getBaseMoveSpeed() {
        double n = 0.2873;
        if (MovementUtil.mc.player != null && MovementUtil.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
        }
        return n;
    }
    
    public static void strafe(final float n) {
        if (!isMoving()) {
            return;
        }
        final double n2 = getDirection();
        MovementUtil.mc.player.motionX = -Math.sin(n2) * n;
        MovementUtil.mc.player.motionZ = Math.cos(n2) * n;
    }
    
    public static void hClip(final double n) {
        final double radians = Math.toRadians(MovementUtil.mc.player.rotationYaw);
        MovementUtil.mc.player.setPosition(MovementUtil.mc.player.posX + -Math.sin(radians) * n, MovementUtil.mc.player.posY, MovementUtil.mc.player.posZ + Math.cos(radians) * n);
    }
}
