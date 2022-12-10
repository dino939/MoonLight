//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import ru.internali.utils.friend.*;
import net.minecraft.util.math.*;
import ru.internali.utils.*;
import java.awt.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class Tracers extends Module
{
    public static List Modes;
    public static List listA;
    static final boolean $assertionsDisabled;
    
    static {
        $assertionsDisabled = !Tracers.class.desiredAssertionStatus();
        Tracers.listA = new ArrayList();
        Tracers.Modes = new ArrayList();
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent renderWorldLastEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (Objects.equals(valString, "Def")) {
            if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
                final boolean viewBobbing = Tracers.mc.gameSettings.viewBobbing;
                Tracers.mc.gameSettings.viewBobbing = false;
                Tracers.mc.gameSettings.viewBobbing = viewBobbing;
                GL11.glPushMatrix();
                GL11.glEnable(2848);
                GL11.glDisable(2929);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glDepthMask(false);
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glLineWidth(1.5f);
                for (final Entity entity : Tracers.mc.world.loadedEntityList) {
                    if (entity != Tracers.mc.player) {
                        if (!(entity instanceof EntityPlayer)) {
                            continue;
                        }
                        assert Tracers.mc.getRenderViewEntity() != null;
                        Tracers.mc.player.getDistance(entity);
                        final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - Tracers.mc.getRenderManager().viewerPosX;
                        final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - Tracers.mc.getRenderManager().viewerPosY;
                        final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - Tracers.mc.getRenderManager().viewerPosZ;
                        if (FriendManager.friendsList.contains(entity.getName())) {
                            GL11.glColor4f(0.0f, 255.0f, 0.0f, 255.0f);
                        }
                        else {
                            GL11.glColor4f(255.0f, 255.0f, 255.0f, 255.0f);
                        }
                        final Vec3d rotateYaw = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Tracers.mc.player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Tracers.mc.player.rotationYaw));
                        GL11.glBegin(2);
                        GL11.glVertex3d(rotateYaw.x, Tracers.mc.player.getEyeHeight() + rotateYaw.y, rotateYaw.z);
                        GL11.glVertex3d(n, n2 + 1.1, n3);
                        GL11.glEnd();
                    }
                }
                GL11.glDisable(3042);
                GL11.glDepthMask(true);
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDisable(2848);
                GL11.glPopMatrix();
            }
        }
        else if (Objects.equals(valString, "Tusk")) {
            Tracers.mc.gameSettings.viewBobbing = false;
            GL11.glPushMatrix();
            GL11.glEnable(2848);
            GL11.glDisable(2929);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GL11.glLineWidth(2.0E-6f);
            for (final Entity entity2 : Tracers.mc.world.loadedEntityList) {
                final Minecraft mc = Tracers.mc;
                if (entity2 != Tracers.mc.player && entity2 instanceof EntityPlayer) {
                    assert Tracers.mc.getRenderViewEntity() != null;
                    final Minecraft mc2 = Tracers.mc;
                    Tracers.mc.player.getDistance(entity2);
                    final double n4 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) - Tracers.mc.getRenderManager().viewerPosX;
                    final double n5 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) - Tracers.mc.getRenderManager().viewerPosY;
                    final double n6 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) - Tracers.mc.getRenderManager().viewerPosZ;
                    final Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
                    final Minecraft mc3 = Tracers.mc;
                    final Vec3d rotatePitch = vec3d.rotatePitch(-(float)Math.toRadians(Tracers.mc.player.rotationPitch));
                    final Minecraft mc4 = Tracers.mc;
                    final Vec3d rotateYaw2 = rotatePitch.rotateYaw(-(float)Math.toRadians(Tracers.mc.player.rotationYaw));
                    GL11.glBegin(2);
                    if (FriendManager.isFriend(entity2.getName())) {
                        RenderUtils.setColor(CatClient.getClientColor());
                    }
                    else {
                        RenderUtils.setColor(Color.white);
                    }
                    final Minecraft mc5 = Tracers.mc;
                    GL11.glVertex3d(rotateYaw2.x, Tracers.mc.player.getEyeHeight() + rotateYaw2.y, rotateYaw2.z);
                    GL11.glVertex3d(n4, n5 + 1.1, n6);
                    GL11.glEnd();
                }
            }
            GL11.glDisable(3042);
            GL11.glDepthMask(true);
            GL11.glDisable(2848);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glPopMatrix();
        }
    }
    
    public Tracers() {
        super("Tracers", "Show Tracers to players", Category.RENDER);
        Tracers.Modes.add("Def");
        Tracers.Modes.add("Tusk");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Def", (ArrayList)Tracers.Modes));
    }
}
