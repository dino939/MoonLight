//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import ru.internali.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Keystrokes extends Module
{
    public int color;
    
    public Keystrokes() {
        super("Keystrokes", "Keystrokes", Category.HUD);
        CatClient.instance.settingsManager.rSetting(new Setting("keyX", this, 1.0, 1.0, 897.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("keyY", this, 1.0, -105.0, 340.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("mouseButtons", this, false));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        final ScaledResolution scaledResolution = new ScaledResolution(Keystrokes.mc);
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "keyX").getValDouble();
            final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "keyY").getValDouble();
            final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "mouseButtons").getValBoolean();
            GlStateManager.pushMatrix();
            if (!Keystrokes.mc.gameSettings.showDebugInfo) {
                final int n3 = (int)n;
                final int n4 = (int)n2;
                this.color = Color.WHITE.getRGB();
                RenderUtils.drawRect((float)(n3 + 20), (float)(n4 + 111), (float)(n3 + 41), (float)(n4 + 130), -1879048192);
                RenderUtils.drawRect((float)(n3 + 1), (float)(n4 + 130), (float)(n3 + 61), (float)(n4 + 150), -1879048192);
                if (Keystrokes.mc.gameSettings.keyBindForward.isPressed()) {
                    RenderUtils.drawRect((float)(n3 + 21), (float)(n4 + 112), (float)(n3 + 40), (float)(n4 + 130), this.color);
                }
                if (Keystrokes.mc.gameSettings.keyBindBack.isPressed()) {
                    RenderUtils.drawRect((float)(n3 + 21), (float)(n4 + 131), (float)(n3 + 40), (float)(n4 + 149), this.color);
                }
                if (Keystrokes.mc.gameSettings.keyBindLeft.isPressed()) {
                    RenderUtils.drawRect((float)(n3 + 2), (float)(n4 + 131), (float)(n3 + 20), (float)(n4 + 149), this.color);
                }
                if (Keystrokes.mc.gameSettings.keyBindRight.isPressed()) {
                    RenderUtils.drawRect((float)(n3 + 41), (float)(n4 + 131), (float)(n3 + 60), (float)(n4 + 149), this.color);
                }
                if (valBoolean) {
                    RenderUtils.drawRect((float)(n3 + 30), (float)(n4 + 170), (float)(n3 + 61), (float)(n4 + 150), -1879048192);
                    RenderUtils.drawRect((float)(n3 + 1), (float)(n4 + 170), (float)(n3 + 30), (float)(n4 + 150), -1879048192);
                    if (Keystrokes.mc.gameSettings.keyBindAttack.isPressed()) {
                        RenderUtils.drawRect((float)(n3 + 2), (float)(n4 + 150), (float)(n3 + 30), (float)(n4 + 169), this.color);
                    }
                    if (Keystrokes.mc.gameSettings.keyBindUseItem.isPressed()) {
                        RenderUtils.drawRect((float)(n3 + 60), (float)(n4 + 150), (float)(n3 + 31), (float)(n4 + 169), this.color);
                    }
                    Keystrokes.mc.fontRenderer.drawStringWithShadow("LMB", (float)(n3 + 7), (float)(n4 + 156), -1);
                    Keystrokes.mc.fontRenderer.drawStringWithShadow("RMB", (float)(n3 + 37), (float)(n4 + 156), -1);
                }
                Keystrokes.mc.fontRenderer.drawStringWithShadow("W", (float)(n3 + 28), (float)(n4 + 117), -1);
                Keystrokes.mc.fontRenderer.drawStringWithShadow("A", (float)(n3 + 8), (float)(n4 + 136), -1);
                Keystrokes.mc.fontRenderer.drawStringWithShadow("S", (float)(n3 + 28), (float)(n4 + 136), -1);
                Keystrokes.mc.fontRenderer.drawStringWithShadow("D", (float)(n3 + 48), (float)(n4 + 136), -1);
            }
            GlStateManager.popMatrix();
        }
    }
}
