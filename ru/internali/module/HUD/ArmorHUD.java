//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.client.renderer.*;
import net.minecraftforge.client.event.*;
import ru.internali.*;
import ru.internali.utils.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class ArmorHUD extends Module
{
    private static RenderItem itemRender;
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "PosX").getValDouble();
        final float n2 = 50.0f;
        RenderUtils.drawShadowRect(n, n2, n + 20.0f, n2 + 80.0f, 4);
        int posY = this.getPosY();
        this.getPosX();
        final Iterator<ItemStack> iterator = ArmorHUD.mc.player.getArmorInventoryList().iterator();
        while (iterator.hasNext()) {
            RenderUtils.renderItem(iterator.next(), (int)(n + 2.0f), posY + 2);
            posY -= 15;
        }
    }
    
    public ArmorHUD() {
        super("ArmorHUD", "ArmorHUD", Category.HUD);
        CatClient.instance.settingsManager.rSetting(new Setting("PosX", this, 50.0, 0.0, 700.0, true));
    }
}
