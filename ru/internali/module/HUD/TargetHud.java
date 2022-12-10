//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import ru.internali.module.combat.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import ru.internali.utils.friend.*;
import net.minecraft.client.network.*;
import java.util.*;
import ru.internali.utils.*;
import net.minecraft.util.text.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class TargetHud extends Module
{
    private String enemyNickname;
    private Entity entity;
    public static List Modes;
    private double healthBarWidth;
    private EntityPlayer entityPlayer;
    private static RenderItem itemRender;
    private boolean show;
    private Entity target;
    private double enemyHP;
    private double enemyDistance;
    
    public void drawitem(final ItemStack itemStack, final int n, final int n2) {
        GlStateManager.enableDepth();
        TargetHud.itemRender.zLevel = 200.0f;
        TargetHud.itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
        TargetHud.itemRender.renderItemOverlayIntoGUI(TargetHud.mc.fontRenderer, itemStack, n, n2, "");
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        if (itemStack.getCount() == 0 || itemStack.getCount() == 1) {
            TargetHud.mc.fontRenderer.drawString("", n + 13, n2 + 10, Color.white.getRGB());
        }
        else {
            TargetHud.mc.fontRenderer.drawString(String.valueOf(new StringBuilder().append("").append(itemStack.getCount())), n + 13, n2 + 10, Color.white.getRGB());
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        if (text.getType() != RenderGameOverlayEvent.ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }
        try {
            GlStateManager.enableTexture2D();
            for (final Entity target : TargetHud.mc.world.loadedEntityList) {
                if (target instanceof EntityPlayer && TargetHud.mc.player.getDistance(target) <= 250.0f && target != TargetHud.mc.player && !AntiBot.isBot(target.getName())) {
                    this.target = target;
                }
            }
            if (AimPistol.target instanceof EntityPlayer) {
                this.targethud(AimPistol.target);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        TargetHud.Modes = new ArrayList();
        TargetHud.itemRender = Minecraft.getMinecraft().getRenderItem();
    }
    
    public void targethud(final Entity entity) {
        final ScaledResolution scaledResolution = new ScaledResolution(TargetHud.mc);
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "PosX").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "PosY").getValDouble();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(scaledResolution.getScaledWidth() / 2 + 10 + n), (double)(scaledResolution.getScaledHeight() / 2 + n2), (double)(scaledResolution.getScaledWidth() / 2 + 10));
        Gui.drawRect(-6, 0, 145, 45, new Color(36, 36, 40, 47).getRGB());
        if (FriendManager.isFriend(entity.getName())) {
            TargetHud.mc.fontRenderer.drawStringWithShadow(entity.getName(), 28.0f, 4.0f, Color.GREEN.getRGB());
        }
        else {
            TargetHud.mc.fontRenderer.drawStringWithShadow(entity.getName(), 28.0f, 4.0f, Color.white.getRGB());
        }
        try {
            TargetHud.mc.getTextureManager().bindTexture(Objects.requireNonNull(TargetHud.mc.getConnection()).getPlayerInfo(entity.getUniqueID()).getLocationSkin());
            Gui.drawScaledCustomSizeModalRect(2, 2, 8.0f, 8.0f, 8, 8, 22, 22, 64.0f, 64.0f);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        int n3 = 8;
        RenderUtil.drawFilledCircle(n3, 35, 10.0f, Color.LIGHT_GRAY);
        RenderUtil.drawFilledCircle(n3, 35, 9.0f, new Color(35, 35, 40, 108));
        this.drawitem(((EntityPlayer)entity).getHeldItemMainhand(), n3 - 8, 27);
        n3 += 24;
        RenderUtil.drawFilledCircle(n3, 35, 10.0f, Color.LIGHT_GRAY);
        RenderUtil.drawFilledCircle(n3, 35, 9.0f, new Color(35, 35, 40, 108));
        this.drawitem(((EntityPlayer)entity).getHeldItemOffhand(), n3 - 8, 27);
        n3 += 24;
        for (final ItemStack itemStack : entity.getArmorInventoryList()) {
            RenderUtil.drawFilledCircle(n3, 35, 10.0f, Color.LIGHT_GRAY);
            RenderUtil.drawFilledCircle(n3, 35, 9.0f, new Color(35, 35, 40, 108));
            this.drawitem(itemStack, n3 - 8, 27);
            n3 += 24;
        }
        RenderUtil.drawFilledCircle(130, 13, 11.0f, Color.LIGHT_GRAY);
        RenderUtil.drawFilledCircle(130, 13, ((EntityPlayer)entity).getHealth() / 2.0f, Color.green);
        if (((EntityPlayer)entity).hurtTime > 0) {
            RenderUtil.drawFilledCircle(130, 13, ((EntityPlayer)entity).getHealth() / 2.0f, Color.red);
        }
        final int[] array = { 1 };
        final int n4 = 0;
        ++array[n4];
        final int n5 = (int)((EntityPlayer)entity).getHealth();
        if (((EntityPlayer)entity).hurtTime > 0) {
            TargetHud.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n5).append("").append(TextFormatting.RED).append(" -").append(((EntityPlayer)entity).hurtTime)), 28.0f, (float)(4 + TargetHud.mc.fontRenderer.FONT_HEIGHT), Color.white.getRGB());
        }
        else {
            TargetHud.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n5).append("")), 28.0f, (float)(4 + TargetHud.mc.fontRenderer.FONT_HEIGHT), Color.white.getRGB());
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        GL11.glPopMatrix();
    }
    
    public TargetHud() {
        super("TargetHud", "TargetHud", Category.HUD);
        CatClient.instance.settingsManager.rSetting(new Setting("PosX", this, 30.0, -400.0, 325.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("PosY", this, 60.0, -270.0, 225.0, true));
    }
}
