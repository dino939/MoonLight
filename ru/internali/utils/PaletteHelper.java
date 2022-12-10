//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.awt.*;
import net.minecraft.client.*;
import java.text.*;
import java.util.*;

public class PaletteHelper implements Helper
{
    public static int fade(final int n, final int n2, final float n3) {
        final float n4 = 1.0f - n3;
        return ((int)((n >> 24 & 0xFF) * n4 + (n2 >> 24 & 0xFF) * n3) & 0xFF) << 24 | ((int)((n >> 16 & 0xFF) * n4 + (n2 >> 16 & 0xFF) * n3) & 0xFF) << 16 | ((int)((n >> 8 & 0xFF) * n4 + (n2 >> 8 & 0xFF) * n3) & 0xFF) << 8 | ((int)((n & 0xFF) * n4 + (n2 & 0xFF) * n3) & 0xFF);
    }
    
    public static int getColor(final Color color) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static int getColor(final int n, final int n2, final int n3, final int n4) {
        return 0x0 | n4 << 24 | n << 16 | n2 << 8 | n3;
    }
    
    public static int getColor(final int n, final int n2) {
        return getColor(n, n, n, n2);
    }
    
    public static int getHealthColor(final float a, final float b) {
        return Color.HSBtoRGB(Math.max(0.0f, Math.min(a, b) / b) / 3.0f, 1.0f, 0.8f) | 0xFF000000;
    }
    
    public Minecraft mc() {
        return null;
    }
    
    public static Color rainbow(final int n, final float s, final float b) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 16L)) % 360.0 / 360.0), s, b);
    }
    
    public static Color astolfo(final boolean b, final int n) {
        new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        float n2;
        float n3;
        for (n2 = (b ? 2000.0f : 100.0f), n3 = (float)(System.currentTimeMillis() % (int)n2 + n); n3 > n2; n3 -= n2) {}
        float n4;
        if ((n4 = n3 / n2) > 0.5) {
            n4 = 0.5f - (n4 - 0.5f);
        }
        return Color.getHSBColor(n4 + 0.5f, 0.4f, 1.0f);
    }
    
    public static Color astolfo(final float n, final int n2) {
        float n3;
        for (n3 = (float)(System.currentTimeMillis() % (int)n + n2); n3 > n; n3 -= n) {}
        float n4;
        if ((n4 = n3 / n) > 0.5) {
            n4 = 0.5f - (n4 - 0.5f);
        }
        return Color.getHSBColor(n4 + 0.5f, 0.4f, 1.0f);
    }
    
    public static int getColor(final int n) {
        return getColor(n, n, n, 255);
    }
    
    public static int fadeColor(final int n, final int n2, float n3) {
        if (n3 > 1.0f) {
            n3 = 1.0f - n3 % 1.0f;
        }
        return fade(n, n2, n3);
    }
}
