//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import java.awt.*;
import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import ru.internali.utils.*;

public class Trails extends Module
{
    public static List Modes;
    private Color Colorid;
    ArrayList trails;
    
    public static Color getRainbowChams(final int n, final int n2) {
        return Color.getHSBColor((System.currentTimeMillis() * 1L + n2 / 2) % n * 2L / (float)n, 1.0f, 1.0f);
    }
    
    public Trails() {
        super("Trails", "Trails", Category.RENDER);
        this.trails = new ArrayList();
        Trails.Modes.add("Rainbow");
        Trails.Modes.add("Client");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Rainbow", (ArrayList)Trails.Modes));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        this.trails.add(new trail(Trails.mc.player.lastTickPosX, Trails.mc.player.lastTickPosY, Trails.mc.player.lastTickPosZ));
    }
    
    static {
        Trails.Modes = new ArrayList();
    }
    
    @SubscribeEvent
    public void Trails(final RenderWorldLastEvent renderWorldLastEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (Objects.equals(valString, "Rainbow")) {
            GL11.glPushMatrix();
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GL11.glDisable(3008);
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glBegin(8);
            RenderUtils.glColor(this.Colorid = getRainbowChams(6000, -17), 180.0f);
        }
        else if (Objects.equals(valString, "Client")) {
            GL11.glPushMatrix();
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GL11.glDisable(3008);
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glBegin(8);
            RenderUtils.glColor(getRainbowChams(6000, -17), 180.0f);
        }
        for (int i = 0; i < this.trails.size(); ++i) {
            final trail o = this.trails.get(i);
            if (o.getTimer().isDeley(1000L)) {
                this.trails.remove(o);
            }
            RenderUtils.putVertex3d(RenderUtils.getRenderPos(o.getX(), o.getY(), o.getZ()));
            RenderUtils.putVertex3d(RenderUtils.getRenderPos(o.getX(), o.getY() + Trails.mc.player.height, o.getZ()));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GL11.glEnable(3008);
        GL11.glShadeModel(7424);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
