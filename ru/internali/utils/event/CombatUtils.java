//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.event;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import ru.internali.utils.*;

public class CombatUtils
{
    public static Minecraft mc;
    
    static {
        CombatUtils.mc = Minecraft.getMinecraft();
    }
    
    public static float[] getRotationFromPosition(final double n, final double n2, final double n3) {
        final double x = n - CombatUtils.mc.player.posX;
        final double y = n2 - Minecraft.getMinecraft().player.posZ;
        return new float[] { (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(n3 - Minecraft.getMinecraft().player.posY - 0.6, MathHelper.sqrt(x * x + y * y)) * 180.0 / 3.141592653589793)) };
    }
    
    public static float[] getRotations(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final String s) {
        if (s == "Head") {
            return getRotationFromPosition(n, n3, n2 - 0.1f + CombatUtils.mc.player.getEyeHeight() / 2.0f, n4, n5, n6);
        }
        if (s == "Chest") {
            return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 0.75, n4, n5, n6);
        }
        if (s == "PenisOfBavarca") {
            return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 1.2, n4, n5, n6);
        }
        if (s == "Legs") {
            return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 1.5, n4, n5, n6);
        }
        return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 0.5, n4, n5, n6);
    }
    
    public static float[] getRotationFromPosition(final double n, final double n2, final double n3, final float n4, final float n5, final float n6) {
        final double x = n - n4;
        final double y = n2 - n6;
        return new float[] { (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(n3 - n5 - 0.6, MathHelper.sqrt(x * x + y * y)) * 180.0 / 3.141592653589793)) };
    }
    
    public static float[] getNeededRotations(final double n, final double n2, final double n3, final float n4, final float n5, final float n6) {
        final double x = n - n4;
        final double y = n3 - n6;
        return new float[] { CombatUtils.mc.player.rotationYaw + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(Math.atan2(y, x) * 180.0 / 3.141592653589793 - 90.0) - CombatUtils.mc.player.rotationYaw)), MathHelper.clamp(CombatUtils.mc.player.rotationPitch + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(-(Math.atan2(n2 - (n5 + CombatUtils.mc.player.getEyeHeight()), MathHelper.sqrt(x * x + y * y)) * 180.0 / 3.141592653589793)) - CombatUtils.mc.player.rotationPitch)), -90.0f, 90.0f) };
    }
    
    public static float[] getRotations(final float n, final float n2, final float n3, final String s) {
        if (s == "Head") {
            return getRotationFromPosition(n, n3, n2 - 0.1f + CombatUtils.mc.player.getEyeHeight() / 2.0f);
        }
        if (s == "Chest") {
            return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 0.75);
        }
        if (s == "PenisOfBavarca") {
            return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 1.2);
        }
        if (s == "Legs") {
            return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 1.5);
        }
        return getRotationFromPosition(n, n3, n2 + CombatUtils.mc.player.getEyeHeight() / 2.0f - 0.5);
    }
}
