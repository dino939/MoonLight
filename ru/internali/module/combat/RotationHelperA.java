//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import ru.internali.utils.*;

public class RotationHelperA
{
    static Minecraft mc;
    
    public static boolean canSeeEntityAtFov(final Entity entity, final float n) {
        return angleDifference((float)(Math.toDegrees(Math.atan2(entity.posZ - RotationHelperA.mc.player.posZ, entity.posX - RotationHelperA.mc.player.posX)) - 90.0), RotationHelperA.mc.player.rotationYaw) <= n;
    }
    
    public static float[] faceBody(final float n, final float n2, final float n3, final float n4, final float n5) {
        final double x = n - Minecraft.getMinecraft().player.posX;
        final double y = n3 - Minecraft.getMinecraft().player.posZ;
        final double n6 = n2 + 1.66 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float n7 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n8 = (float)(-(Math.atan2(n6 - 0.55, x2) * 180.0 / 3.141592653589793));
        final float n9 = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float n10 = n9 * n9 * n9 * 1.2f;
        final float updateRotation = updateRotation(Minecraft.getMinecraft().player.rotationPitch, n8, n5);
        final float updateRotation2 = updateRotation(Minecraft.getMinecraft().player.rotationYaw, n7, n4);
        return new float[] { updateRotation2 - updateRotation2 % n10, updateRotation - updateRotation % n10 };
    }
    
    public static float[] getNeededRotations(final BlockPos blockPos) {
        final double n = blockPos.getX() - Minecraft.getMinecraft().player.posX;
        final double n2 = blockPos.getZ() - Minecraft.getMinecraft().player.posZ;
        return new float[] { (float)(MathHelper.atan2(n2, n) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(MathHelper.atan2(blockPos.getY() - Minecraft.getMinecraft().player.posY, (double)MathHelper.sqrt(n * n + n2 * n2)) * 180.0 / 3.141592653589793)) };
    }
    
    public static double angleDifference(final double n, final double n2) {
        float n3 = (float)(Math.abs(n - n2) % 360.0);
        if (n3 > 180.0f) {
            n3 = 360.0f - n3;
        }
        return n3;
    }
    
    public static float updateRotation(final float n, final float n2, final float n3) {
        float wrapDegrees = MathHelper.wrapDegrees(n2 - n);
        if (wrapDegrees > n3) {
            wrapDegrees = n3;
        }
        if (wrapDegrees < -n3) {
            wrapDegrees = -n3;
        }
        return n + wrapDegrees;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(RotationHelperA.mc.player.posX, RotationHelperA.mc.player.getEntityBoundingBox().minY + RotationHelperA.mc.player.getEyeHeight(), RotationHelperA.mc.player.posZ);
    }
    
    static {
        RotationHelperA.mc = Minecraft.getMinecraft();
    }
    
    public static float[] faceHead(final float n, final float n2, final float n3, final float n4, final float n5) {
        final double x = n - Minecraft.getMinecraft().player.posX;
        final double y = n3 - Minecraft.getMinecraft().player.posZ;
        final double n6 = n2 + 1.66 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float n7 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n8 = (float)(-(Math.atan2(n6 - 0.1, x2) * 180.0 / 3.141592653589793));
        final float n9 = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float n10 = n9 * n9 * n9 * 1.2f;
        final float updateRotation = updateRotation(Minecraft.getMinecraft().player.rotationPitch, n8, n5);
        final float updateRotation2 = updateRotation(Minecraft.getMinecraft().player.rotationYaw, n7, n4);
        return new float[] { updateRotation2 - updateRotation2 % n10, updateRotation - updateRotation % n10 };
    }
    
    public static float[] getRotationVector(final Vec3d vec3d, final boolean b, final float n, final float n2, final float n3) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - (RotationHelperA.mc.player.posY + RotationHelperA.mc.player.getEyeHeight() + 0.5);
        final double y2 = vec3d.z - eyesPos.z;
        final double sqrt = Math.sqrt(x * x + y2 * y2);
        float randomizeFloat = 0.0f;
        if (b) {
            randomizeFloat = MathematicHelper.randomizeFloat(-n, n);
        }
        float randomizeFloat2 = 0.0f;
        if (b) {
            randomizeFloat2 = MathematicHelper.randomizeFloat(-n2, n2);
        }
        return new float[] { updateRotation(RotationHelperA.mc.player.rotationYaw, RotationHelperA.mc.player.rotationYaw + GCDCalcHelper.getFixedRotation(MathHelper.wrapDegrees((float)(Math.toDegrees(Math.atan2(y2, x)) - 90.0) + randomizeFloat - RotationHelperA.mc.player.rotationYaw)), n3), updateRotation(RotationHelperA.mc.player.rotationPitch, MathHelper.clamp(RotationHelperA.mc.player.rotationPitch + GCDCalcHelper.getFixedRotation(MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, sqrt))) + randomizeFloat2 - RotationHelperA.mc.player.rotationPitch)), -90.0f, 90.0f), n3) };
    }
    
    public static class Rotation
    {
        public static float renderPacketYaw;
        public static boolean isReady;
        public static float packetYaw;
        public static float lastPacketYaw;
        public static float lastBodyYaw;
        public static float lastPacketPitch;
        public static float bodyYaw;
        public static float packetPitch;
        public static int rotationCounter;
        public static float lastRenderPacketYaw;
        public static boolean isAiming;
        
        static {
            Rotation.isReady = false;
        }
    }
}
