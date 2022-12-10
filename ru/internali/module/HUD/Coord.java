//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.client.*;
import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Coord extends Module
{
    int pingPlayer;
    public double speedometerCurrentSpeed;
    public static final Coord INSTANCE;
    private final Minecraft mc;
    
    public Coord() {
        super("Coord", "Coord", Category.HUD);
        this.speedometerCurrentSpeed = 0.0;
        this.mc = Minecraft.getMinecraft();
        CatClient.instance.settingsManager.rSetting(new Setting("Speed", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("SpeedKM", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("World", this, true));
    }
    
    public double turnIntoKpH(final double n) {
        return MathHelper.sqrt(n) * 71.2729367892;
    }
    
    static {
        INSTANCE = new Coord();
    }
    
    public double getSpeedKpH() {
        return Math.round(10.0 * this.turnIntoKpH(this.speedometerCurrentSpeed)) / 10.0;
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Speed").getValBoolean();
            final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "SpeedKM").getValBoolean();
            if (CatClient.instance.settingsManager.getSettingByName(this, "World").getValBoolean()) {
                final Vec3d vec3d = new Vec3d(Math.round(this.mc.player.posX * 100.0) / 100.0, Math.round(this.mc.player.posY * 100.0) / 100.0, Math.round(this.mc.player.posZ * 100.0) / 100.0);
                this.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(TextFormatting.GRAY).append("XYZ: ").append(TextFormatting.RESET).append(vec3d.x).append(", ").append(vec3d.y).append(", ").append(vec3d.z)), 4.0f, (float)(scaledResolution.getScaledHeight() - this.mc.fontRenderer.FONT_HEIGHT - 4), 16777215);
            }
            if (valBoolean2) {
                this.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(TextFormatting.GRAY).append("Speed: ").append(TextFormatting.RESET).append(this.getSpeedKpH())), 4.0f, (float)(scaledResolution.getScaledHeight() - this.mc.fontRenderer.FONT_HEIGHT - 20), 16777215);
            }
            if (valBoolean) {
                final double n = this.mc.player.posX - this.mc.player.prevPosX;
                final double n2 = this.mc.player.posZ - this.mc.player.prevPosZ;
                this.speedometerCurrentSpeed = n * n + n2 * n2;
            }
        }
    }
}
