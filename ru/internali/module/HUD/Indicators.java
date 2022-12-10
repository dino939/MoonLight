//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import ru.internali.utils.*;
import java.text.*;
import net.minecraft.util.math.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.network.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;

public class Indicators extends Module
{
    private boolean show;
    private double enemyDistance;
    private double healthBarWidth;
    private float hurtAnim;
    private double enemyHP;
    private EntityPlayer entityPlayer;
    private float cooldownAnim;
    private String enemyNickname;
    private Entity entity;
    
    @SubscribeEvent
    public void Indicators(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "IndGetX").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "IndGetY").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName(this, "HpGetX").getValDouble();
        final float n4 = (float)CatClient.instance.settingsManager.getSettingByName(this, "HpGetY").getValDouble();
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (Indicators.mc.player == null || Indicators.mc.world == null) {
                return;
            }
            if (Indicators.mc.gameSettings.showDebugInfo) {
                return;
            }
            final float n5 = n;
            final float n6 = n2;
            final float n7 = n5;
            final float n8 = n6;
            final float n9 = n7 + 48.0f + 70.0f;
            final float n10 = n8 + 74.0f;
            GlStateManager.pushMatrix();
            RectHelper.drawRect(n7, n8, n9, n10, new Color(5, 5, 5, 158).getRGB());
            RectHelper.drawSmoothRect(n5, n6, n9, n6 - 1.0f, CatClient.getClientColor().getRGB());
            RectHelper.drawRect(n5 + 3.0f, n6 + 14.0f, n9 - 3.0f, n6 + 14.5, new Color(205, 205, 205).getRGB());
            Indicators.mc.fontRenderer.drawStringWithShadow("Indicators", n5 + 33.0f, n6 + 5.0f, -1);
            if (Minecraft.getDebugFPS() > 5) {
                final float n11 = (Minecraft.getDebugFPS() < 10) ? 0.25f : (6.0f / Minecraft.getDebugFPS());
            }
            final String value = String.valueOf(new StringBuilder().append("").append(Indicators.mc.player.getHealth() / Indicators.mc.player.getMaxHealth() * 100.0f));
            final String format = String.format(String.valueOf(new StringBuilder().append("%.2f ").append(ChatFormatting.WHITE).append("b/s")), MovementHelper.getSpeed() * 16.0f);
            final String value2 = String.valueOf(new StringBuilder().append("").append(Minecraft.getDebugFPS()));
            final String format2 = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            final String value3 = String.valueOf(new StringBuilder().append("").append(Indicators.mc.player.getHealth()));
            Indicators.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("FPS: ").append(value2)), n7 + 3.0f, n8 + 20.0f, -1);
            Indicators.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Speed: ").append(format)), n7 + 3.0f, n8 + 31.0f, -1);
            Indicators.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Time: ").append(format2)), n7 + 3.0f, n8 + 42.0f, -1);
            Indicators.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Health: ").append(value3).append(" / ").append(value)), n7 + 3.0f, n8 + 53.0f, -1);
            final float n12 = n3;
            final float n13 = n4;
            this.enemyHP = Indicators.mc.player.getHealth();
            this.enemyNickname = Indicators.mc.player.getName();
            this.entityPlayer = (EntityPlayer)this.entity;
            final double n14 = 97.0 * MathHelper.clamp((double)(Math.round(this.enemyHP) / 20.0f), 0.0, 1.0);
            final String value4 = String.valueOf(Math.round(this.enemyHP));
            Gui.drawRect((int)(n12 + 125.5), (int)(n13 - 9.5), (int)(n12 + 265.0f), (int)(n13 + 30.5f), new Color(31, 31, 31, 255).getRGB());
            Gui.drawRect((int)(n12 + 166.0f), (int)(n13 + 6.0f), (int)(n12 + 263.0f), (int)(n13 + 15.0f), new Color(40, 40, 40, 255).getRGB());
            Gui.drawRect((int)(n12 + 166.0f), (int)(n13 + 6.0f), (int)(n12 + 166.0f + this.healthBarWidth), (int)(n13 + 15.0f), new Color(2816512).getRGB());
            Gui.drawRect((int)(n12 + 166.0f), (int)(n13 + 6.0f), (int)(n12 + 166.0f + n14), (int)(n13 + 15.0f), new Color(2816512).getRGB());
            try {
                this.drawHead(Objects.requireNonNull(Indicators.mc.getConnection()).getPlayerInfo(Indicators.mc.player.getUniqueID()).getLocationSkin(), (int)(n12 + 127.0f), (int)(n13 - 8.0f));
            }
            catch (Exception ex) {}
            Indicators.mc.fontRenderer.drawStringWithShadow(value4, n12 + 128.0f + 46.0f - Indicators.mc.fontRenderer.getStringWidth(value4) / 2.0f, n13 + 19.5f, -1);
            Indicators.mc.fontRenderer.drawStringWithShadow("\u2764", n12 + 128.0f + 46.0f + Indicators.mc.fontRenderer.getStringWidth(value4), n13 + 19.5f, new Color(2816512).getRGB());
            Indicators.mc.fontRenderer.drawStringWithShadow(Indicators.mc.player.getName(), n12 + 167.0f, n13 - 5.0f, -1);
            Indicators.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Ground: ").append(Indicators.mc.player.onGround)), n7 + 3.0f, n8 + 64.0f, -1);
            GlStateManager.popMatrix();
        }
    }
    
    public Indicators() {
        super("Indicators", "Coord", Category.HUD);
        CatClient.instance.settingsManager.rSetting(new Setting("IndGetX", this, 0.0, 0.0, 897.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("IndGetY", this, 268.0, 0.0, 340.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("HpGetX", this, 701.0, -120.0, 701.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("HpGetY", this, 508.0, 5.0, 508.0, true));
    }
    
    public void drawHead(final ResourceLocation resourceLocation, final int n, final int n2) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Indicators.mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawScaledCustomSizeModalRect(n, n2, 8.0f, 8.0f, 8, 8, 37, 37, 64.0f, 64.0f);
    }
}
