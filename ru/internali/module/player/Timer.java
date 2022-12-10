//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.client.gui.*;
import ru.internali.module.HUD.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.utils.*;

public class Timer extends Module
{
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final int n = (int)CatClient.instance.settingsManager.getSettingByName(this, "TimerSpeed").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "radius").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName(this, "IndGetX").getValDouble();
        final float n4 = (float)CatClient.instance.settingsManager.getSettingByName(this, "IndGetY").getValDouble();
        final float n5 = (float)CatClient.instance.settingsManager.getSettingByName(this, "radiusFilled").getValDouble();
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final ScaledResolution scaledResolution = new ScaledResolution(Timer.mc);
            RenderUtil.drawCircle222(n3, n4, 20.0f, HUD.getRainbowChams(6000, -17), n * 36);
            RenderUtil.drawCircle222(n3, n4, n2, Color.white.getRGB(), 360);
            RenderUtil.drawFilledCircle((int)n3, (int)n4, n5, Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + 300L + 300L)) / 15.0 % 360.0 / 360.0), 0.4f, 1.0f));
        }
    }
    
    public Timer() {
        super("Timer", "NoPush", Category.PLAYER);
        CatClient.instance.settingsManager.rSetting(new Setting("TimerSpeed", this, 1.0, 0.0, 10.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("radius", this, 18.0, 1.0, 60.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("IndGetX", this, 400.0, 0.0, 910.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("IndGetY", this, 500.0, 0.0, 500.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("radiusFilled", this, 14.8, 1.0, 60.0, false));
    }
    
    @SubscribeEvent
    public void onClientTickEvent(final TickEvent.ClientTickEvent clientTickEvent) {
        ReflectionUtils.setTimerSpeedD((float)CatClient.instance.settingsManager.getSettingByName(this, "TimerSpeed").getValDouble());
    }
    
    public void onDisable() {
        ReflectionUtils.setTimerSpeedD(1.0);
        super.onDisable();
    }
}
