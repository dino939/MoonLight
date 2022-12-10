//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import java.awt.*;
import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import ru.internali.utils.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Chams extends Module
{
    public static int getRainbowChams(final int n, final int n2) {
        return Color.getHSBColor((System.currentTimeMillis() * 1L + n2 / 2) % n * 2L / (float)n, 1.0f, 1.0f).getRGB();
    }
    
    public Chams() {
        super("Chams", "ChinaHat", Category.RENDER);
        CatClient.instance.settingsManager.rSetting(new Setting("red", this, 255.0, 1.0, 255.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("green", this, 255.0, 1.0, 255.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("blue", this, 255.0, 1.0, 255.0, true));
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            for (final Entity entity : Chams.mc.world.loadedEntityList) {
                final CastHelper castHelper = new CastHelper();
                castHelper.apply(CastHelper.EntityType.PLAYERS);
                if (CastHelper.isInstanceof(entity, castHelper.build()) != null) {
                    final float n = 255.0f;
                    final float n2 = 255.0f;
                    final float n3 = 255.0f;
                    final float n4 = 255.0f;
                    GL11.glPushMatrix();
                    GlStateManager.disableBlend();
                    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
                    GL11.glEnable(3042);
                    GL11.glDisable(3553);
                    GL11.glBlendFunc(770, 771);
                    GlStateManager.color(n, n2, n3, n4);
                }
            }
        }
    }
}
