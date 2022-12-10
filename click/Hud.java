//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package click;

import click.font.*;
import ru.internali.utils.*;
import java.util.*;
import java.text.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.item.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import org.lwjgl.input.*;

public class Hud extends Gui
{
    private String toAdd;
    public static GlyphPageFontRenderer targetRenderer;
    public static GlyphPageFontRenderer smallRenderer;
    public TimerUtil timerUtil;
    public static GlyphPageFontRenderer renderer;
    public static GlyphPageFontRenderer myRenderer;
    public Random random;
    public DecimalFormat df;
    private Minecraft mc;
    public static GlyphPageFontRenderer fuckrenderer;
    
    public static void drawBorderedCircle(double n, double n2, float n3, final int n4, final int n5) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        final float n6 = 0.1f;
        GL11.glScalef(n6, n6, n6);
        n = (int)(n * (1.0f / n6));
        n2 = (int)(n2 * (1.0f / n6));
        drawCircle(n, n2, n3 *= 1.0f / n6, n5);
        drawUnfilledCircle(n, n2, n3, 1.0f, n4);
        GL11.glScalef(1.0f / n6, 1.0f / n6, 1.0f / n6);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawCustomRect(float n, float n2, float n3, float n4, final int n5) {
        if (n < n3) {
            final float n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final float n7 = n2;
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
        getBuffer.pos((double)n, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        drawRect(n - 1.0f, n2 - 1.0f, n, n4, new Color(255, 0, 255, 255).getRGB());
        drawRect(n, n2 - 1.0f, n3, n2, new Color(255, 0, 255, 255).getRGB());
        drawRect(n3 + 1.0f, n4 + 1.0f, n3, n2, new Color(255, 0, 255, 255).getRGB());
        drawRect(n3, n4 + 1.0f, n, n4, new Color(255, 0, 255, 255).getRGB());
    }
    
    public static void renderItem(final ItemStack itemStack, final Point point) {
        GlStateManager.enableTexture2D();
        GL11.glPushAttrib(524288);
        GL11.glDisable(3089);
        GlStateManager.clear(256);
        GL11.glPopAttrib();
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getRenderItem().zLevel = -150.0f;
        RenderHelper.enableGUIStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, point.x, point.y);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer, itemStack, point.x, point.y);
        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0f;
        GlStateManager.popMatrix();
        begin();
    }
    
    public static void drawRoundedRect(double n, double n2, final double n3, final double n4, final double n5, final int n6) {
        final float n7 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n6 & 0xFF) / 255.0f;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        Tessellator.getInstance();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n7, n8, n9, n10);
        GL11.glDisable(3553);
        GL11.glColor4f(n8, n9, n10, n7);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        int n11 = 0;
        while (true) {
            GL11.glVertex2d((n *= 2.0) + n5 + Math.sin(n11 * 3.141592653589793 / 180.0) * (n5 * -1.0), (n2 *= 2.0) + n5 + Math.cos(n11 * 3.141592653589793 / 180.0) * (n5 * -1.0));
            n11 += 3;
        }
    }
    
    public void onRenderArmor(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        final NonNullList armorInventory = Minecraft.getMinecraft().player.inventory.armorInventory;
        int n = 3;
        for (int i = 0; i < armorInventory.size(); ++i) {
            renderItem((ItemStack)armorInventory.get(n--), new Point(scaledResolution.getScaledWidth() / 2 + n * 18 + 38, scaledResolution.getScaledHeight() - 55));
        }
    }
    
    public void onRenderInventory(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        final NonNullList mainInventory = Minecraft.getMinecraft().player.inventory.mainInventory;
        int n = 27;
        final int n2 = 4;
        int n3 = 0;
        while (true) {
            drawRoundedRect(scaledResolution.getScaledWidth() - 178 + n3 * 20, scaledResolution.getScaledHeight() - 100 + n2 * 20, 16.0, 16.0, 8.0, new Color(0, 0, 0, 100).getRGB());
            renderItem((ItemStack)mainInventory.get(n++), new Point(scaledResolution.getScaledWidth() - 178 + n3 * 20, scaledResolution.getScaledHeight() - 100 + n2 * 20));
            ++n3;
        }
    }
    
    public static void drawRect(float n, float n2, float n3, float n4, final int n5) {
        if (n < n3) {
            final float n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final float n7 = n2;
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
        getBuffer.pos((double)n, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    static {
        Hud.renderer = GlyphPageFontRenderer.create("Arial", 40, true, true, true);
        Hud.myRenderer = GlyphPageFontRenderer.create("Times New Roman Baltic", 19, true, true, true);
        Hud.fuckrenderer = GlyphPageFontRenderer.create("Courier New", 50, true, true, true);
        Hud.smallRenderer = GlyphPageFontRenderer.create("Courier New", 18, true, true, true);
        Hud.targetRenderer = GlyphPageFontRenderer.create("Courier New", 16, true, true, true);
    }
    
    private static void begin() {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GlStateManager.glLineWidth(2.0f);
    }
    
    private void onRenderKeyStrokes(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final int[] array = { 1 };
        final int a = Keyboard.isKeyDown(this.mc.gameSettings.keyBindForward.getKeyCode()) ? 125 : 50;
        final int a2 = Keyboard.isKeyDown(this.mc.gameSettings.keyBindLeft.getKeyCode()) ? 125 : 50;
        final int a3 = Keyboard.isKeyDown(this.mc.gameSettings.keyBindBack.getKeyCode()) ? 125 : 50;
        final int a4 = Keyboard.isKeyDown(this.mc.gameSettings.keyBindRight.getKeyCode()) ? 125 : 50;
        drawRoundedRect(5.0, 250.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, a2).getRGB());
        drawRoundedRect(38.0, 250.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, a3).getRGB());
        drawRoundedRect(71.0, 250.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, a4).getRGB());
        drawRoundedRect(38.0, 217.0, 30.0, 30.0, 6.0, new Color(0, 0, 0, a).getRGB());
        Hud.renderer.drawString("W", 42.5f, 219.0f, new Color(255, 0, 0, 255).getRGB(), true);
        Hud.renderer.drawString("A", 45.0f, 253.0f, new Color(255, 0, 0, 255).getRGB(), true);
        Hud.renderer.drawString("S", 12.0f, 253.0f, new Color(255, 0, 0, 255).getRGB(), true);
        Hud.renderer.drawString("D", 78.0f, 253.0f, new Color(255, 0, 0, 255).getRGB(), true);
        ++array[0];
    }
    
    public static void drawUnfilledCircle(final double n, final double n2, final float n3, float n4, final int n5) {
        n4 = 1.0f;
        GL11.glColor4f((n5 >> 16 & 0xFF) / 255.0f, (n5 >> 8 & 0xFF) / 255.0f, (n5 & 0xFF) / 255.0f, (n5 >> 24 & 0xFF) / 255.0f);
        GL11.glLineWidth(n4);
        GL11.glEnable(2848);
        GL11.glBegin(2);
        int n6 = 0;
        while (true) {
            GL11.glVertex2d(n + Math.sin(n6 * 3.141526 / 180.0) * n3, n2 + Math.cos(n6 * 3.141526 / 180.0) * n3);
            ++n6;
        }
    }
    
    public static int rainbow(final int n) {
        return Color.getHSBColor((float)(Math.ceil((System.currentTimeMillis() + n) / 10.0) % -360.0 / -360.0), 0.735f, 1.0f).getRGB();
    }
    
    public Hud() {
        this.mc = Minecraft.getMinecraft();
        this.df = new DecimalFormat("###.#");
        this.random = new Random();
        this.timerUtil = new TimerUtil();
    }
    
    public static void drawCircle(final double n, final double n2, final float n3, final int n4) {
        GL11.glColor4f((n4 >> 16 & 0xFF) / 255.0f, (n4 >> 8 & 0xFF) / 255.0f, (n4 & 0xFF) / 255.0f, (n4 >> 24 & 0xFF) / 255.0f);
        GL11.glBegin(9);
        int n5 = 0;
        while (true) {
            GL11.glVertex2d(n + Math.sin(n5 * 3.141526 / 180.0) * n3, n2 + Math.cos(n5 * 3.141526 / 180.0) * n3);
            ++n5;
        }
    }
}
