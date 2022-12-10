//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import ru.internali.*;
import net.minecraft.client.renderer.*;
import ru.internali.utils.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class InvViewer extends Module
{
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final ScaledResolution scaledResolution = new ScaledResolution(InvViewer.mc);
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
            final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            RenderUtils.drawShadowRect(n, n2, n + 145.0f, n2 + 48.0f, 2);
            GlStateManager.resetColor();
            for (int i = 0; i < 27; ++i) {
                final ItemStack itemStack = (ItemStack)InvViewer.mc.player.inventory.mainInventory.get(i + 9);
                final float n3 = n + i % 9 * 16;
                final float n4 = n2 + i / 9 * 16;
                InvViewer.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, (int)n3, (int)n4);
                InvViewer.mc.getRenderItem().renderItemOverlayIntoGUI(InvViewer.mc.fontRenderer, itemStack, (int)n3, (int)n4, (String)null);
            }
            RenderHelper.disableStandardItemLighting();
            InvViewer.mc.getRenderItem().zLevel = 0.0f;
            GlStateManager.popMatrix();
        }
    }
    
    public InvViewer() {
        super("InvViewer", "InvViewer", Category.HUD);
        CatClient.instance.settingsManager.rSetting(new Setting("Horizontal", this, 30.0, 0.0, 810.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Vertical", this, 60.0, 0.0, 490.0, true));
    }
}
