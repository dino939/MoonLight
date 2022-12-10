//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.fov;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;

public class FOVUtil
{
    public static int getDistanceFromMouse(final Entity entity) {
        final float[] neededRotations = UtilsForFov.getNeededRotations(entity);
        final float n = Minecraft.getMinecraft().player.rotationYaw - neededRotations[0];
        final float n2 = Minecraft.getMinecraft().player.rotationPitch - neededRotations[1];
        return (int)MathHelper.sqrt(n * n + n2 * n2 * 2.0f);
    }
    
    public static String getPlayerName(final EntityPlayer entityPlayer) {
        return (entityPlayer.getGameProfile() != null) ? entityPlayer.getGameProfile().getName() : "null";
    }
    
    public static boolean isInAttackFOV(final Entity entity, final int n) {
        return UtilsForFov.getDistanceFromMouse(entity) <= n;
    }
    
    public static float[] getNeededRotations(final Entity entity) {
        final Vec3d eyesPos = UtilsForFov.getEyesPos();
        final double x = entity.posX - eyesPos.x;
        final double y = entity.posY - eyesPos.y;
        final double y2 = entity.posZ - eyesPos.z;
        return new float[] { Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - Minecraft.getMinecraft().player.rotationYaw), Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - Minecraft.getMinecraft().player.rotationPitch) };
    }
}
