//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import ru.internali.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class strafe extends Module
{
    public static boolean isMoving() {
        return strafe.mc.player != null && (strafe.mc.player.movementInput.moveForward != 0.0f || strafe.mc.player.movementInput.moveStrafe != 0.0f);
    }
    
    public strafe() {
        super("Strafe", "strafing", Category.MOVEMENT);
    }
    
    public static void strafe() {
        strafe(getSpeed());
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        strafe();
    }
    
    public static double getDirection() {
        float rotationYaw = strafe.mc.player.rotationYaw;
        if (strafe.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (strafe.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (strafe.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (strafe.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (strafe.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return Math.toRadians(rotationYaw);
    }
    
    public static void strafe(final float n) {
        if (!isMoving()) {
            return;
        }
        final double direction = getDirection();
        strafe.mc.player.motionX = -Math.sin(direction) * n;
        strafe.mc.player.motionZ = Math.cos(direction) * n;
    }
    
    public static float getSpeed() {
        return (float)Math.sqrt(strafe.mc.player.motionX * strafe.mc.player.motionX + strafe.mc.player.motionZ * strafe.mc.player.motionZ);
    }
}
