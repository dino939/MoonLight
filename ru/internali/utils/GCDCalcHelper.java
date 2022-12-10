//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;

public class GCDCalcHelper implements Helper
{
    public static float getGCDValue() {
        return (float)(getGCD() * 0.15);
    }
    
    public static float getFixedRotation(final float n) {
        return getDeltaMouse(n) * getGCDValue();
    }
    
    public static float getGCD() {
        final float n;
        return (n = (float)(MinecraftHelper.mc.gameSettings.mouseSensitivity * 0.6 + 0.2)) * n * n * 8.0f;
    }
    
    public static float getDeltaMouse(final float n) {
        return (float)Math.round(n / getGCDValue());
    }
    
    @Override
    public Minecraft mc() {
        return null;
    }
}
