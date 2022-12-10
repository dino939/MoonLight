//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ShkafRender extends Module
{
    ArrayList armorStands;
    
    public ShkafRender() {
        super("ShkafRender", "ShkafRender", Category.RENDER);
        this.armorStands = new ArrayList();
        CatClient.instance.settingsManager.rSetting(new Setting("Wallhack", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Glowing", this, false));
    }
    
    public void onDisable() {
        super.onDisable();
        for (final Entity entity : ShkafRender.mc.world.loadedEntityList) {
            if (entity instanceof EntityArmorStand && entity.isGlowing()) {
                entity.setGlowing(false);
            }
        }
    }
    
    void render(final Entity entity, final float n) {
        try {
            if (entity == null || entity == ShkafRender.mc.player) {
                return;
            }
            if (entity == ShkafRender.mc.getRenderViewEntity() && ShkafRender.mc.gameSettings.thirdPersonView == 0) {
                return;
            }
            ShkafRender.mc.entityRenderer.disableLightmap();
            ShkafRender.mc.getRenderManager().renderEntityStatic(entity, n, false);
            ShkafRender.mc.entityRenderer.enableLightmap();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void ShkafRender(final RenderWorldLastEvent renderWorldLastEvent) {
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Wallhack").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "Glowing").getValBoolean();
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof EntityArmorStand && entity != Minecraft.getMinecraft().getRenderViewEntity()) {
                if (valBoolean) {
                    this.render(entity, renderWorldLastEvent.getPartialTicks());
                }
                if (!valBoolean2 || entity.isGlowing()) {
                    continue;
                }
                entity.setGlowing(true);
            }
        }
    }
}
