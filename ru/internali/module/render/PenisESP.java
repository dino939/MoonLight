//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import ru.internali.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PenisESP extends Module
{
    public void esp(final EntityPlayer entityPlayer, final double n, final double n2, final double n3) {
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GL11.glLineWidth(1.0f);
        GL11.glTranslated(n, n2, n3);
        GL11.glRotatef(-entityPlayer.rotationYaw, 0.0f, entityPlayer.height, 0.0f);
        GL11.glTranslated(-n, -n2, -n3);
        GL11.glTranslated(n, n2 + entityPlayer.height / 2.0f - 0.22499999403953552, n3);
        GL11.glColor4f(1.38f, 0.55f, 2.38f, 1.0f);
        GL11.glTranslated(0.0, 0.0, 0.07500000298023224);
        final Cylinder cylinder = new Cylinder();
        cylinder.setDrawStyle(100013);
        cylinder.draw(0.1f, 0.11f, 0.4f, 25, 20);
        GL11.glColor4f(1.38f, 0.85f, 1.38f, 1.0f);
        GL11.glTranslated(0.0, 0.0, -0.12500000298023223);
        GL11.glTranslated(-0.09000000074505805, 0.0, 0.0);
        final Sphere sphere = new Sphere();
        sphere.setDrawStyle(100013);
        sphere.draw(0.14f, 10, 20);
        GL11.glTranslated(0.16000000149011612, 0.0, 0.0);
        final Sphere sphere2 = new Sphere();
        sphere2.setDrawStyle(100013);
        sphere2.draw(0.14f, 10, 20);
        GL11.glColor4f(1.35f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslated(-0.07000000074505806, 0.0, 0.589999952316284);
        final Sphere sphere3 = new Sphere();
        sphere3.setDrawStyle(100013);
        sphere3.draw(0.13f, 15, 20);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public PenisESP() {
        super("PenisESP", "PenisESP", Category.RENDER);
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        for (final EntityPlayer next : PenisESP.mc.world.loadedEntityList) {
            if (next instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = next;
                final double n = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX);
                PenisESP.mc.getRenderManager();
                final double n2 = n - Minecraft.getMinecraft().getRenderManager().viewerPosX;
                final double n3 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY);
                PenisESP.mc.getRenderManager();
                final double n4 = n3 - Minecraft.getMinecraft().getRenderManager().viewerPosY;
                final double n5 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ);
                PenisESP.mc.getRenderManager();
                final double n6 = n5 - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                RenderHelper.disableStandardItemLighting();
                this.esp(entityPlayer, n2, n4, n6);
                RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
        }
    }
}
