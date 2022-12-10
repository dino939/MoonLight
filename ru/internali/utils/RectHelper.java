//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class RectHelper implements Helper
{
    public static void polygon(final double n, final double n2, double n3, final double n4, final boolean b, final Color color) {
        n3 /= 2.0;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2884);
        GlStateManager.disableAlpha();
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        if (!b) {
            GL11.glLineWidth(1.0f);
        }
        GL11.glEnable(2848);
        GL11.glBegin(b ? 6 : 3);
        for (double n5 = 0.0; n5 <= n4; ++n5) {
            final double n6 = n5 * 6.283185307179586 / n4;
            GL11.glVertex2d(n + n3 * Math.cos(n6) + n3, n2 + n3 * Math.sin(n6) + n3);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager.enableAlpha();
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawSmoothRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        drawRect(n, n2, n3, n4, n5);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect(n * 2.0f - 1.0f, n2 * 2.0f, n * 2.0f, n4 * 2.0f - 1.0f, n5);
        drawRect(n * 2.0f, n2 * 2.0f - 1.0f, n3 * 2.0f, n2 * 2.0f, n5);
        drawRect(n3 * 2.0f, n2 * 2.0f, n3 * 2.0f + 1.0f, n4 * 2.0f - 1.0f, n5);
        drawRect(n * 2.0f, n4 * 2.0f - 1.0f, n3 * 2.0f, n4 * 2.0f, n5);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public Minecraft mc() {
        return null;
    }
    
    public static void drawGradientRectBetter(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        drawGradientRect(n, n2, n + n3, n2 + n4, n5, n6);
    }
    
    public static void drawMinecraftRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n3, (double)n2, 300.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 300.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n4, 300.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos((double)n3, (double)n4, 300.0).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRectBetter(final double n, final double n2, final double n3, final double n4, final int n5) {
        drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static void drawSmoothGradientRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        drawGradientRect(n, n2, n3, n4, n5, n6);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawGradientRect(n * 2.0 - 1.0, n2 * 2.0, n * 2.0, n4 * 2.0 - 1.0, n5, n6);
        drawGradientRect(n * 2.0, n2 * 2.0 - 1.0, n3 * 2.0, n2 * 2.0, n5, n6);
        drawGradientRect(n3 * 2.0, n2 * 2.0, n3 * 2.0 + 1.0, n4 * 2.0 - 1.0, n5, n6);
        drawGradientRect(n * 2.0, n4 * 2.0 - 1.0, n3 * 2.0, n4 * 2.0, n5, n6);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void drawRoundedRect(final double n, final double n2, final double n3, final double n4, final float n5, final Color color) {
        final float n6 = (float)(n + (n5 / 2.0f + 0.5f));
        final float n7 = (float)(n2 + (n5 / 2.0f + 0.5f));
        final float n8 = (float)(n3 - (n5 / 2.0f + 0.5f));
        final float n9 = (float)(n4 - (n5 / 2.0f + 0.5f));
        drawRect(n6, n7, n6 + n8, n7 + n9, color.getRGB());
        polygon(n, n2, n5 * 2.0f, 360.0, true, color);
        polygon(n + n8 - n5 + 1.2, n2, n5 * 2.0f, 360.0, true, color);
        polygon(n + n8 - n5 + 1.2, n2 + n9 - n5 + 1.0, n5 * 2.0f, 360.0, true, color);
        polygon(n, n2 + n9 - n5 + 1.0, n5 * 2.0f, 360.0, true, color);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        drawRect(n6 - n5 / 2.0f - 0.5f, n7 + n5 / 2.0f, n6 + n8, n7 + n9 - n5 / 2.0f, color.getRGB());
        drawRect(n6, n7 + n5 / 2.0f, n6 + n8 + n5 / 2.0f + 0.5f, n7 + n9 - n5 / 2.0f, color.getRGB());
        drawRect(n6 + n5 / 2.0f, n7 - n5 / 2.0f - 0.5f, n6 + n8 - n5 / 2.0f, n2 + n9 - n5 / 2.0f, color.getRGB());
        drawRect(n6 + n5 / 2.0f, n7, n6 + n8 - n5 / 2.0f, n7 + n9 + n5 / 2.0f + 0.5f, color.getRGB());
    }
    
    public static void drawRect(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBorderedRect(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final int n7, final boolean b) {
        drawRect(n - (b ? 0.0f : n5), n2 - (b ? 0.0f : n5), n3 + (b ? 0.0f : n5), n4 + (b ? 0.0f : n5), n7);
        drawRect(n + (b ? n5 : 0.0f), n2 + (b ? n5 : 0.0f), n3 - (b ? n5 : 0.0f), n4 - (b ? n5 : 0.0f), n6);
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        getInstance.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawSmoothRectBetter(final float n, final float n2, final float n3, final float n4, final int n5) {
        drawSmoothRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static void drawRect2(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawOutlineRect(final float n, final float n2, final float n3, final float n4, final Color color, final Color color2) {
        drawRect(n, n2, n + n3, n2 + n4, color.getRGB());
        final int rgb = color2.getRGB();
        drawRect(n - 1.0f, n2, n, n2 + n4, rgb);
        drawRect(n + n3, n2, n + n3 + 1.0f, n2 + n4, rgb);
        drawRect(n - 1.0f, n2 - 1.0f, n + n3 + 1.0f, n2, rgb);
        drawRect(n - 1.0f, n2 + n4, n + n3 + 1.0f, n2 + n4 + 1.0f, rgb);
    }
    
    public static void drawSkeetRectWithoutBorder(final float n, final float n2, final float n3, final float n4) {
        drawSmoothRect(n - 41.0f, n2 - 61.0f, n3 + 41.0f, n4 + 61.0f, new Color(48, 48, 48, 255).getRGB());
        drawSmoothRect(n - 40.0f, n2 - 60.0f, n3 + 40.0f, n4 + 60.0f, new Color(17, 17, 17, 255).getRGB());
    }
    
    public static void drawBorder(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final int n7, final boolean b) {
        drawRect(n - (b ? 0.0f : n5), n2 - (b ? 0.0f : n5), n3 + (b ? 0.0f : n5), n4 + (b ? 0.0f : n5), n7);
        drawRect(n + (b ? n5 : 0.0f), n2 + (b ? n5 : 0.0f), n3 - (b ? n5 : 0.0f), n4 - (b ? n5 : 0.0f), n6);
    }
    
    public static void drawSkeetRect(final float n, final float n2, final float n3, final float n4) {
        drawRect(n - 46.5f, n2 - 66.5f, n3 + 46.5f, n4 + 66.5f, new Color(0, 0, 0, 255).getRGB());
        drawRect(n - 46.0f, n2 - 66.0f, n3 + 46.0f, n4 + 66.0f, new Color(48, 48, 48, 255).getRGB());
        drawRect(n - 44.5f, n2 - 64.5f, n3 + 44.5f, n4 + 64.5f, new Color(33, 33, 33, 255).getRGB());
        drawRect(n - 43.5f, n2 - 63.5f, n3 + 43.5f, n4 + 63.5f, new Color(0, 0, 0, 255).getRGB());
        drawRect(n - 43.0f, n2 - 63.0f, n3 + 43.0f, n4 + 63.0f, new Color(9, 9, 9, 255).getRGB());
        drawRect(n - 40.5f, n2 - 60.5f, n3 + 40.5f, n4 + 60.5f, new Color(48, 48, 48, 255).getRGB());
        drawRect(n - 40.0f, n2 - 60.0f, n3 + 40.0f, n4 + 60.0f, new Color(17, 17, 17, 255).getRGB());
    }
}
