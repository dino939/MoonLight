//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.notifications;

import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import ru.internali.utils.*;
import ru.internali.*;
import java.util.*;

public class notifications
{
    public static List Tests;
    public static List Names;
    public static List Types;
    public static List Times;
    
    public static void show() {
        for (int i = 0; i < notifications.Names.size(); ++i) {
            if ((int)notifications.Times.get(i) != 120) {
                notifications.Times.set(i, (int)notifications.Times.get(i) + 1);
            }
            else {
                notifications.Names.remove(i);
                notifications.Tests.remove(i);
                notifications.Types.remove(i);
                notifications.Times.remove(i);
            }
        }
        notifications.height = 22.0f;
        for (int j = 0; j < notifications.Names.size(); ++j) {
            drawnotif(j, (String)notifications.Names.get(j), (String)notifications.Tests.get(j), (Type)notifications.Types.get(j), (int)notifications.Times.get(j));
        }
    }
    
    public static void drawnotif(final int n, final String s, final String s2, final Type type, final int n2) {
        GlStateManager.enableTexture2D();
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glPushMatrix();
        GL11.glTranslated((double)(100.0f / 100.0f + 0.0f), (double)(scaledResolution.getScaledHeight() / 100 + 0), 0.0);
        if (n2 >= 10) {
            GL11.glTranslated((double)(scaledResolution.getScaledWidth() - 110), scaledResolution.getScaledHeight() - n2 * 1.2 + 7.0, 0.0);
        }
        else {
            GL11.glTranslated((double)(scaledResolution.getScaledWidth() - n2), scaledResolution.getScaledHeight() - n2 * 1.2 + 7.0, 0.0);
        }
        final int a = 190 - (n2 - 1);
        if (a >= 0) {
            if (type == Type.Green) {
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 100.0f, 30.0f, new Color(35, 35, 40, a).getRGB());
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 7.0f, 30.0f, new Color(51, 255, 0, a).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, 10.0f, 2.0f, CatClient.getClientColor().getRGB());
                Minecraft.getMinecraft().fontRenderer.drawString(s2, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, a).getRGB());
            }
            else if (type == Type.Red) {
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 100.0f, 30.0f, new Color(35, 35, 40, a).getRGB());
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 7.0f, 30.0f, new Color(255, 0, 0, a).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, 10.0f, 2.0f, CatClient.getClientColor().getRGB());
                Minecraft.getMinecraft().fontRenderer.drawString(s2, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, a).getRGB());
            }
            else if (type == Type.OK) {
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 100.0f, 30.0f, new Color(35, 35, 40, a).getRGB());
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 7.0f, 30.0f, new Color(51, 255, 0, a).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, 10.0f, 2.0f, CatClient.getClientColor().getRGB());
                Minecraft.getMinecraft().fontRenderer.drawString(s2, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, a).getRGB());
            }
        }
        GL11.glPopMatrix();
        GlStateManager.disableTexture2D();
    }
    
    static {
        notifications.Names = new ArrayList();
        notifications.Tests = new ArrayList();
        notifications.Types = new ArrayList();
        notifications.Times = new ArrayList();
    }
    
    public static void add(final String s, final String s2, final Type type) {
        notifications.Names.add(s);
        notifications.Tests.add(s2);
        notifications.Types.add(type);
        notifications.Times.add(0);
    }
}
