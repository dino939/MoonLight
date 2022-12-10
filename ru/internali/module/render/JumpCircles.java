//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import java.awt.*;
import ru.internali.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import java.util.function.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import java.util.*;

public class JumpCircles extends Module
{
    static float pt;
    static Color jumpCircleColor;
    static List circles;
    static final int TYPE;
    static final byte MAX_JC_TIME;
    
    static {
        MAX_JC_TIME = 20;
        TYPE = 0;
        JumpCircles.jumpCircleColor = CatClient.getClientColor();
        JumpCircles.circles = new ArrayList();
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (JumpCircles.mc.player.motionY == 0.33319999363422365) {
            handleEntityJump((Entity)JumpCircles.mc.player);
        }
        onLocalPlayerUpdate(JumpCircles.mc.player);
    }
    
    public JumpCircles() {
        super("JumpCircles", "JumpCircles", Category.RENDER);
    }
    
    public static void handleEntityJump(final Entity entity) {
        JumpCircles.circles.add(new Circle(entity.getPositionVector(), new Vec3d((double)(int)((JumpCircles.jumpCircleColor.getRed() >> 16 & 0xFF) / 100.0f), (double)(int)((JumpCircles.jumpCircleColor.getGreen() >> 8 & 0xFF) / 100.0f), (double)(int)((JumpCircles.jumpCircleColor.getBlue() & 0xFF) / 100.0f))));
    }
    
    public static void onLocalPlayerUpdate(final EntityPlayerSP entityPlayerSP) {
        JumpCircles.circles.removeIf(Circle::update);
    }
    
    @SubscribeEvent
    public void JumpCircles(final RenderWorldLastEvent renderWorldLastEvent) {
        final EntityPlayerSP player = JumpCircles.mc.player;
        final double n = -(player.lastTickPosX + (player.posX - player.lastTickPosX) * JumpCircles.mc.getRenderPartialTicks());
        final double n2 = -(player.lastTickPosY + (player.posY - player.lastTickPosY) * JumpCircles.mc.getRenderPartialTicks());
        final double n3 = -(player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * JumpCircles.mc.getRenderPartialTicks());
        GL11.glPushMatrix();
        GL11.glTranslated(n, n2, n3);
        GL11.glDisable(2884);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(3008);
        GL11.glDisable(2929);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glShadeModel(7425);
        Collections.reverse(JumpCircles.circles);
        try {
            for (final Circle circle : JumpCircles.circles) {
                final float n4 = circle.existed / 20.0f;
                final double x = circle.position().x;
                final double n5 = circle.position().y - n4 * 0.5;
                final double z = circle.position().z;
                final float n6 = n4 + 1.0f - n4;
                GL11.glBegin(8);
                for (int i = 0; i <= 360; i += 5) {
                    GL11.glColor4f((float)circle.color().x, (float)circle.color().y, (float)circle.color().z, 0.2f * (1.0f - circle.existed / 20.0f));
                    GL11.glVertex3d(x + Math.cos(Math.toRadians(i * 4)) * n4, n5, z + Math.sin(Math.toRadians(i * 4)) * n4);
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.01f * (1.0f - circle.existed / 20.0f));
                    GL11.glVertex3d(x + Math.cos(Math.toRadians(i)) * n6, n5 + Math.sin(n4 * 8.0f) * 0.5, z + Math.sin(Math.toRadians(i) * n6));
                }
                GL11.glEnd();
            }
        }
        catch (Exception ex) {}
        Collections.reverse(JumpCircles.circles);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glEnable(2884);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glPopMatrix();
    }
}
