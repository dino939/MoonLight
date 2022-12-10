//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraft.client.*;

public class Rotation
{
    private static float Yaw;
    private static boolean settingPitch;
    private static float Pitch;
    private static boolean settingYaw;
    
    public static void setYaw(final float yaw) {
        Rotation.settingPitch = true;
        Rotation.Yaw = yaw;
    }
    
    public static void setPitch(final float pitch) {
        Rotation.settingPitch = true;
        Rotation.Pitch = pitch;
    }
    
    public static float getPitch() {
        final float pitch = Rotation.Pitch;
        Rotation.Pitch = Minecraft.getMinecraft().player.rotationPitch;
        return pitch;
    }
    
    public static float getYaw() {
        final float yaw = Rotation.Yaw;
        Rotation.Yaw = Minecraft.getMinecraft().player.rotationYaw;
        return yaw;
    }
}
