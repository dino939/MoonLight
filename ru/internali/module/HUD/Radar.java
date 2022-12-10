//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.util.math.*;
import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import ru.internali.module.combat.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.item.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import ru.internali.utils.*;
import java.awt.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Radar extends Module
{
    float partialTicks;
    
    public float getTicks() {
        return this.partialTicks;
    }
    
    public double[] getLookVector(float n) {
        n *= 0.017453292f;
        return new double[] { -MathHelper.sin(n), MathHelper.cos(n) };
    }
    
    public Radar() {
        super("Radar", "Radar", Category.HUD);
        CatClient.instance.settingsManager.rSetting(new Setting("x", this, 2.0, 0.0, 897.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("y", this, 27.0, 0.0, 340.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("size", this, 70.0, 25.0, 200.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("players", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("monsters", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("animals", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("passives", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("invisibles", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("items", this, false));
    }
    
    private boolean qualifies(final Entity entity) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "players").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "monsters").getValBoolean();
        final boolean valBoolean3 = CatClient.instance.settingsManager.getSettingByName(this, "items").getValBoolean();
        final boolean valBoolean4 = CatClient.instance.settingsManager.getSettingByName(this, "invisibles").getValBoolean();
        final boolean valBoolean5 = CatClient.instance.settingsManager.getSettingByName(this, "animals").getValBoolean();
        final boolean valBoolean6 = CatClient.instance.settingsManager.getSettingByName(this, "passives").getValBoolean();
        return ((entity instanceof EntityPlayer && valBoolean && !AntiBot.isBot(String.valueOf(entity))) || (entity instanceof EntityMob && valBoolean2) || ((entity instanceof EntityAnimal || entity instanceof EntitySquid) && valBoolean5) || ((entity instanceof EntityVillager || entity instanceof EntityGolem) && valBoolean6) || (entity instanceof EntityItem && valBoolean3)) && (!entity.isInvisible() || valBoolean4) && entity != Radar.mc.player;
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "x").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "y").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName(this, "size").getValDouble();
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT && !Radar.mc.gameSettings.showDebugInfo) {
            GL11.glPushMatrix();
            final int n4 = (int)n3;
            final int n5 = (int)n3;
            final float n6 = n + n4 / 2.0f;
            final float n7 = n2 + n5 / 2.0f;
            RenderUtils.drawBorderedRect(n, n2, n + n4, n2 + n5, 1.0f, -12303292, -14540254);
            RenderUtils.drawRectSized(n + n4 / 2.0f - 0.5f, n2, 1.0f, (float)n5, -12303292);
            RenderUtils.drawRectSized(n, n2 + n5 / 2.0f - 0.5f, (float)n4, 1.0f, -12303292);
            RenderUtils.drawRectSized(n6 - 1.0f, n7 - 1.0f, 2.0f, 2.0f, -256);
            final int n8 = (int)(n3 / 2.0f);
            for (final Entity entity : Radar.mc.world.loadedEntityList) {
                if (this.qualifies(entity)) {
                    final double n9 = RenderUtils.lerp(entity.prevPosX, entity.posX, this.getTicks()) - RenderUtils.lerp(Radar.mc.player.prevPosX, Radar.mc.player.posX, this.getTicks());
                    final double n10 = RenderUtils.lerp(entity.prevPosZ, entity.posZ, this.getTicks()) - RenderUtils.lerp(Radar.mc.player.prevPosZ, Radar.mc.player.posZ, this.getTicks());
                    if (n9 * n9 + n10 * n10 > n8 * n8) {
                        continue;
                    }
                    final float sqrt_double = MathUtils.sqrt_double(n9 * n9 + n10 * n10);
                    final double[] lookVector = this.getLookVector(RotationUtils.getRotations(entity)[0] - (float)RenderUtils.lerp(Radar.mc.player.prevRotationYawHead, Radar.mc.player.rotationYawHead, this.getTicks()));
                    if (entity instanceof EntityMob) {
                        RenderUtils.drawRectSized(n6 - 1.0f - (float)lookVector[0] * sqrt_double, n7 - 1.0f - (float)lookVector[1] * sqrt_double, 2.0f, 2.0f, new Color(0, 252, 103).getRGB());
                    }
                    else if (entity instanceof EntityPlayer) {
                        RenderUtils.drawRectSized(n6 - 1.0f - (float)lookVector[0] * sqrt_double, n7 - 1.0f - (float)lookVector[1] * sqrt_double, 2.0f, 2.0f, new Color(248, 0, 0).getRGB());
                    }
                    else if (entity instanceof EntityAnimal || entity instanceof EntitySquid || entity instanceof EntityVillager || entity instanceof EntityGolem) {
                        RenderUtils.drawRectSized(n6 - 1.0f - (float)lookVector[0] * sqrt_double, n7 - 1.0f - (float)lookVector[1] * sqrt_double, 2.0f, 2.0f, new Color(248, 178, 0).getRGB());
                    }
                    else {
                        if (!(entity instanceof EntityItem)) {
                            continue;
                        }
                        RenderUtils.drawRectSized(n6 - 1.0f - (float)lookVector[0] * sqrt_double, n7 - 1.0f - (float)lookVector[1] * sqrt_double, 2.0f, 2.0f, new Color(0, 147, 241).getRGB());
                    }
                }
            }
        }
        GL11.glPopMatrix();
    }
}
