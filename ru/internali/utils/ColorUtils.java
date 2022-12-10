//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.text.*;
import java.util.*;
import net.minecraft.entity.*;

public class ColorUtils
{
    public static int getAlpha(final int rgb) {
        return new Color(rgb).getAlpha();
    }
    
    public static Color astolfoColorsToColorObj(final int n, final int n2, final int a) {
        final int astolfoColors = astolfoColors(n, n2);
        return new Color(getRed(astolfoColors), getGreen(astolfoColors), getBlue(astolfoColors), a);
    }
    
    public static int Yellowastolfo(final int n, final float n2) {
        float n3;
        float n4;
        for (n3 = 2900.0f, n4 = System.currentTimeMillis() % (int)n3 + (-n - n2) * 9.0f; n4 > n3; n4 -= n3) {}
        float n5;
        if ((n5 = n4 / n3) > 0.6) {
            n5 = 0.6f - (n5 - 0.6f);
        }
        return Color.HSBtoRGB(n5 + 0.6f, 0.5f, 1.0f);
    }
    
    public static void setColor(final int n) {
        GlStateManager.color((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static Color rainbowRGB(final int n, final float saturation, final float brightness) {
        return new Color(getRed(Color.HSBtoRGB((System.currentTimeMillis() + n) % 11520L / 11520.0f, saturation, brightness)), getGreen(Color.HSBtoRGB((System.currentTimeMillis() + n) % 11520L / 11520.0f, saturation, brightness)), getBlue(Color.HSBtoRGB((System.currentTimeMillis() + n) % 11520L / 11520.0f, saturation, brightness)));
    }
    
    public static int getAstolfoRainbow(final int n) {
        float n2;
        float n3;
        for (n2 = 2900.0f, n3 = System.currentTimeMillis() % (int)n2 + (float)((1000 - n) * 9); n3 > n2; n3 -= n2) {}
        float n4 = n3 / n2;
        if (n4 > 0.5) {
            n4 = 0.5f - (n4 - 0.5f);
        }
        return Color.HSBtoRGB(n4 + 0.5f, 0.65f, 1.0f);
    }
    
    public static int rainbow(final int n, final long n2) {
        return Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + n2 + n)) / 15.0 % 360.0 / 360.0), 0.4f, 1.0f).getRGB();
    }
    
    public static int getRed(final int rgb) {
        return new Color(rgb).getRed();
    }
    
    public static int rainbowLT(final int n, final long n2) {
        return Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + n2 + n)) / 3.0 % 248.0 / 248.0), 0.5f, 0.6f).getRGB();
    }
    
    public static Color getGradientOffset(final Color color, final Color color2, double n) {
        if (n > 1.0) {
            final double n2 = n % 1.0;
            n = (((int)n % 2 == 0) ? n2 : (1.0 - n2));
        }
        final double n3 = 1.0 - n;
        return new Color((int)(color.getRed() * n3 + color2.getRed() * n), (int)(color.getGreen() * n3 + color2.getGreen() * n), (int)(color.getBlue() * n3 + color2.getBlue() * n));
    }
    
    public static int astolfo(final int n, final float n2) {
        float n3;
        float abs;
        for (n3 = 3000.0f, abs = Math.abs(System.currentTimeMillis() % n + -n2 / 21.0f * 2.0f); abs > n3; abs -= n3) {}
        float n4;
        if ((n4 = abs / n3) > 0.5) {
            n4 = 0.5f - (n4 - 0.5f);
        }
        return Color.HSBtoRGB(n4 + 0.5f, 0.5f, 1.0f);
    }
    
    public static int reAlpha(final int rgb, final float a) {
        final Color color = new Color(rgb);
        return new Color(0.003921569f * color.getRed(), 0.003921569f * color.getGreen(), 0.003921569f * color.getBlue(), a).getRGB();
    }
    
    public static int getColor(final int n, final int n2, final int n3) {
        return getColor(n, n2, n3, 255);
    }
    
    public static void setColor(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.color(n, n2, n3, n4);
    }
    
    public static Color TwoColoreffect(final Color color, final Color color2, final double n) {
        final float clamp = MathUtils.clamp((float)Math.sin(18.84955592153876 * (n / 4.0 % 1.0)) / 2.0f + 0.5f, 0.0f, 1.0f);
        return new Color(MathUtils.lerp(color.getRed() / 255.0f, color2.getRed() / 255.0f, clamp), MathUtils.lerp(color.getGreen() / 255.0f, color2.getGreen() / 255.0f, clamp), MathUtils.lerp(color.getBlue() / 255.0f, color2.getBlue() / 255.0f, clamp));
    }
    
    public static void setColor(final Color color) {
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static int[] getFractionIndicies(final float[] array, final float n) {
        final int[] array2 = new int[2];
        int n2;
        for (n2 = 0; n2 < array.length && array[n2] <= n; ++n2) {}
        if (n2 >= array.length) {
            n2 = array.length - 1;
        }
        array2[0] = n2 - 1;
        array2[1] = n2;
        return array2;
    }
    
    public static Color fade(final Color color, final int n, final int n2) {
        final float[] hsbvals = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbvals);
        hsbvals[2] = (0.5f + 0.5f * Math.abs((System.currentTimeMillis() % 2000L / 1000.0f + n / (float)n2 * 2.0f) % 2.0f - 1.0f)) % 2.0f;
        return new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]));
    }
    
    public static int color(final int r, final int g, final int b, int a) {
        a = 255;
        return new Color(r, g, b, a).getRGB();
    }
    
    public static Color injectAlpha(final int n, final int a) {
        return new Color(getRed(n), getGreen(n), getBlue(n), a);
    }
    
    public static Color astolfoColorsToColorObj(final int n, final int n2) {
        return astolfoColorsToColorObj(n, n2, 255);
    }
    
    public static int getGreen(final int rgb) {
        return new Color(rgb).getGreen();
    }
    
    public static void glColor(final int n, final int n2) {
        GlStateManager.color((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, n2 / 255.0f);
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static Color blend(final Color color, final Color color2, final double n) {
        final float n2 = (float)n;
        final float n3 = 1.0f - n2;
        final float[] compArray = new float[3];
        final float[] compArray2 = new float[3];
        color.getColorComponents(compArray);
        color2.getColorComponents(compArray2);
        float r = compArray[0] * n2 + compArray2[0] * n3;
        float g = compArray[1] * n2 + compArray2[1] * n3;
        float b = compArray[2] * n2 + compArray2[2] * n3;
        if (r < 0.0f) {
            r = 0.0f;
        }
        else if (r > 255.0f) {
            r = 255.0f;
        }
        if (g < 0.0f) {
            g = 0.0f;
        }
        else if (g > 255.0f) {
            g = 255.0f;
        }
        if (b < 0.0f) {
            b = 0.0f;
        }
        else if (b > 255.0f) {
            b = 255.0f;
        }
        Color color3 = null;
        try {
            color3 = new Color(r, g, b);
        }
        catch (IllegalArgumentException ex) {
            NumberFormat.getNumberInstance();
        }
        return color3;
    }
    
    public static int getColor(final int n, final int n2, final int n3, final int n4) {
        return 0x0 | n4 << 24 | n << 16 | n2 << 8 | n3;
    }
    
    public static int getColor1(final int n) {
        return getColor(n, n, n, 255);
    }
    
    public static int getRandomColor() {
        final char[] charArray = "012345678".toCharArray();
        String value = "0x";
        for (int i = 0; i < 6; ++i) {
            value = String.valueOf(new StringBuilder().append(value).append(charArray[new Random().nextInt(charArray.length)]));
        }
        return Integer.decode(value);
    }
    
    public static Color rainbow() {
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((System.nanoTime() + 999999999999L) / 1.0E10f % 1.0f, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static Color blendColors(final float[] array, final Color[] array2, final float n) {
        if (array == null) {
            throw new IllegalArgumentException("Fractions can't be null");
        }
        if (array2 == null) {
            throw new IllegalArgumentException("Colours can't be null");
        }
        if (array.length != array2.length) {
            throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
        }
        final int[] fractionIndicies = getFractionIndicies(array, n);
        final float[] array3 = { array[fractionIndicies[0]], array[fractionIndicies[1]] };
        final Color[] array4 = { array2[fractionIndicies[0]], array2[fractionIndicies[1]] };
        return blend(array4[0], array4[1], 1.0f - (n - array3[0]) / (array3[1] - array3[0]));
    }
    
    public static int rainbowNew(final int n, final float s, final float b) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 16L)) % 360.0 / 360.0), s, b).getRGB();
    }
    
    public static int getColor(final int n) {
        return getColor(n, n, n, 255);
    }
    
    public static Color astolfoColors1(final int n, final int n2) {
        float n3;
        float n4;
        for (n3 = 2900.0f, n4 = System.currentTimeMillis() % (int)n3 + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {}
        float n5;
        if ((n5 = n4 / n3) > 0.5) {
            n5 = 0.5f - (n5 - 0.5f);
        }
        return new Color(n5 + 0.5f, 0.5f, 1.0f);
    }
    
    public static Color getColorWithOpacity(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static int getColor(final Color color) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static Color rainbowCol(final int n, final float s, final float b) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 16L)) % 360.0 / 360.0), s, b);
    }
    
    public static int astolfoColors(final int n, final int n2) {
        float n3;
        float n4;
        for (n3 = 2900.0f, n4 = System.currentTimeMillis() % (int)n3 + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {}
        float n5;
        if ((n5 = n4 / n3) > 0.5) {
            n5 = 0.5f - (n5 - 0.5f);
        }
        return Color.HSBtoRGB(n5 + 0.5f, 0.5f, 1.0f);
    }
    
    public static int getColor(final int n, final int n2) {
        return getColor(n, n, n, n2);
    }
    
    public static int TwoColoreffect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7) {
        final float clamp = MathUtils.clamp((float)Math.sin(18.84955592153876 * (n7 / 4.0 % 1.0)) / 2.0f + 0.5f, 0.0f, 1.0f);
        return getColor((int)MathUtils.lerp((float)n, (float)n4, clamp), (int)MathUtils.lerp((float)n2, (float)n5, clamp), (int)MathUtils.lerp((float)n3, (float)n6, clamp));
    }
    
    public static Color getHealthColor(final EntityLivingBase entityLivingBase) {
        final float getHealth = entityLivingBase.getHealth();
        final float[] array = { 0.0f, 0.15f, 0.55f, 0.7f, 0.9f };
        final Color[] array2 = { new Color(133, 0, 0), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN };
        final float n = getHealth / entityLivingBase.getMaxHealth();
        return (getHealth >= 0.0f) ? blendColors(array, array2, n).brighter() : array2[0];
    }
    
    public static Color fade(final Color color) {
        return fade(color, 2, 100);
    }
    
    public static Color Yellowastolfo1(final int n, final float n2) {
        float n3;
        float n4;
        for (n3 = 2900.0f, n4 = System.currentTimeMillis() % (int)n3 + (n - n2) * 9.0f; n4 > n3; n4 -= n3) {}
        float n5;
        if ((n5 = n4 / n3) > 0.6) {
            n5 = 0.6f - (n5 - 0.6f);
        }
        return new Color(n5 + 0.6f, 0.5f, 1.0f);
    }
    
    public static Color injectAlpha(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static Color rainbow(final int n, final float s, final float b) {
        return Color.getHSBColor((System.currentTimeMillis() + n) % 11520L / 11520.0f, s, b);
    }
    
    public static int getBlue(final int rgb) {
        return new Color(rgb).getBlue();
    }
    
    public static int getTeamColor(final Entity entity) {
        return entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u0405cR\u043f\u0457\u0405f]\u043f\u0457\u0405c").append(entity.getName()))) ? getColor(new Color(255, 60, 60)) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u04059B\u043f\u0457\u0405f]\u043f\u0457\u04059").append(entity.getName()))) ? getColor(new Color(60, 60, 255)) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u0405eY\u043f\u0457\u0405f]\u043f\u0457\u0405e").append(entity.getName()))) ? getColor(new Color(255, 255, 60)) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u0405aG\u043f\u0457\u0405f]\u043f\u0457\u0405a").append(entity.getName()))) ? getColor(new Color(60, 255, 60)) : getColor(new Color(255, 255, 255)))));
    }
}
