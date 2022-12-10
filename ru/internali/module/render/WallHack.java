//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class WallHack extends Module
{
    void render(final Entity entity, final float n) {
        try {
            if (entity == null || entity == Minecraft.getMinecraft().player) {
                return;
            }
            if (entity == Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
                return;
            }
            Minecraft.getMinecraft().entityRenderer.disableLightmap();
            Minecraft.getMinecraft().getRenderManager().renderEntityStatic(entity, n, false);
            Minecraft.getMinecraft().entityRenderer.enableLightmap();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Mob").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "Animal").getValBoolean();
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        if (valBoolean) {
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity instanceof EntityMob) {
                    if (entity == Minecraft.getMinecraft().getRenderViewEntity()) {
                        continue;
                    }
                    this.render(entity, renderWorldLastEvent.getPartialTicks());
                }
            }
        }
        if (valBoolean2) {
            for (final Entity entity2 : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity2 instanceof EntityAnimal) {
                    if (entity2 == Minecraft.getMinecraft().getRenderViewEntity()) {
                        continue;
                    }
                    this.render(entity2, renderWorldLastEvent.getPartialTicks());
                }
            }
        }
    }
    
    public WallHack() {
        super("WallHackMob", "WallHack", Category.RENDER);
        CatClient.instance.settingsManager.rSetting(new Setting("Mob", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Animal", this, false));
    }
}
