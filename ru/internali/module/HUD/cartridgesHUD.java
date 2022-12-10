//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.client.gui.*;
import ru.internali.module.*;
import net.minecraft.client.*;
import ru.internali.*;
import ru.internali.settings.*;
import java.awt.*;
import ru.internali.utils.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class cartridgesHUD extends Module
{
    private int RABBIT_HIDE;
    private int SHULKER_SHELL;
    private int PRISMARINE_CRYSTALS;
    private int PRISMARINE_SHARD;
    private ItemStack BRICK2;
    private int STICK;
    private ItemStack STICK2;
    ScaledResolution sr;
    private ItemStack RABBIT_HIDE2;
    private int ARROW;
    private final FontRenderer fr;
    private static RenderItem kappita;
    private ItemStack SHULKER_SHELL2;
    private int GUNPOWDER;
    private static final RenderItem itemRender;
    private ItemStack ARROW2;
    private ItemStack PRISMARINE_SHARD2;
    private int BRICK;
    private ItemStack PRISMARINE_CRYSTALS2;
    private ItemStack GUNPOWDER2;
    
    public cartridgesHUD() {
        super("CartridgesHUD", "show Cartiges", Category.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.sr = new ScaledResolution(cartridgesHUD.mc);
        this.STICK = 0;
        this.BRICK = 0;
        this.PRISMARINE_CRYSTALS = 0;
        this.PRISMARINE_SHARD = 0;
        this.SHULKER_SHELL = 0;
        this.RABBIT_HIDE = 0;
        this.GUNPOWDER = 0;
        this.ARROW = 0;
        CatClient.instance.settingsManager.rSetting(new Setting("PosX", this, 50.0, 0.0, 200.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("PosY", this, 50.0, 0.0, 500.0, true));
    }
    
    public void drawitem(final ItemStack itemStack, final int n, final int n2, final int i) {
        cartridgesHUD.itemRender.zLevel = 1.0f;
        this.fr.drawString("", 0, 0, Color.white.getRGB());
        RenderUtils.drawShadowRect(n, n2, n + 19, n2 + 20, 2);
        GlStateManager.resetColor();
        cartridgesHUD.itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
        cartridgesHUD.itemRender.renderItemOverlayIntoGUI(cartridgesHUD.mc.fontRenderer, itemStack, n, n2, String.valueOf(new StringBuilder().append("").append(i)));
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
    }
    
    public void drawcard() {
        if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
            return;
        }
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "PosX").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "PosY").getValDouble();
        try {
            GlStateManager.enableTexture2D();
            GL11.glPushMatrix();
            GL11.glTranslated((double)n, (double)n2, 0.0);
            for (int i = 0; i < Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.size(); ++i) {
                final Item getItem = Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.get(i).getStack().getItem();
                final ItemStack getStack = Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getItem == Items.STICK) {
                    this.STICK += getStack.getCount();
                    this.STICK2 = getStack;
                }
                else if (getItem == Items.BRICK) {
                    this.BRICK += getStack.getCount();
                    this.BRICK2 = getStack;
                }
                else if (getItem == Items.PRISMARINE_CRYSTALS) {
                    this.PRISMARINE_CRYSTALS += getStack.getCount();
                    this.PRISMARINE_CRYSTALS2 = getStack;
                }
                else if (getItem == Items.PRISMARINE_SHARD) {
                    this.PRISMARINE_SHARD += getStack.getCount();
                    this.PRISMARINE_SHARD2 = getStack;
                }
                else if (getItem == Items.SHULKER_SHELL) {
                    this.SHULKER_SHELL += getStack.getCount();
                    this.SHULKER_SHELL2 = getStack;
                }
                else if (getItem == Items.RABBIT_HIDE) {
                    this.RABBIT_HIDE += getStack.getCount();
                    this.RABBIT_HIDE2 = getStack;
                }
                else if (getItem == Items.GUNPOWDER) {
                    this.GUNPOWDER += getStack.getCount();
                    this.GUNPOWDER2 = getStack;
                }
                else if (getItem == Items.ARROW) {
                    this.ARROW += getStack.getCount();
                    this.ARROW2 = getStack;
                }
            }
            int n3 = 0;
            if (this.STICK != 0) {
                this.drawitem(this.STICK2, 0, n3 * 25, this.STICK);
                ++n3;
            }
            if (this.BRICK != 0) {
                this.drawitem(this.BRICK2, 0, n3 * 25, this.BRICK);
                ++n3;
            }
            if (this.PRISMARINE_CRYSTALS != 0) {
                this.drawitem(this.PRISMARINE_CRYSTALS2, 0, n3 * 25, this.PRISMARINE_CRYSTALS);
                ++n3;
            }
            if (this.PRISMARINE_SHARD != 0) {
                this.drawitem(this.PRISMARINE_SHARD2, 0, n3 * 25, this.PRISMARINE_SHARD);
                ++n3;
            }
            if (this.SHULKER_SHELL != 0) {
                this.drawitem(this.SHULKER_SHELL2, 0, n3 * 25, this.SHULKER_SHELL);
                ++n3;
            }
            if (this.RABBIT_HIDE != 0) {
                this.drawitem(this.RABBIT_HIDE2, 0, n3 * 25, this.RABBIT_HIDE);
                ++n3;
            }
            if (this.GUNPOWDER != 0) {
                this.drawitem(this.GUNPOWDER2, 0, n3 * 25, this.GUNPOWDER);
                ++n3;
            }
            if (this.ARROW != 0) {
                this.drawitem(this.ARROW2, 0, n3 * 25, this.ARROW);
                ++n3;
            }
            this.STICK = 0;
            this.BRICK = 0;
            this.PRISMARINE_CRYSTALS = 0;
            this.PRISMARINE_SHARD = 0;
            this.SHULKER_SHELL = 0;
            this.RABBIT_HIDE = 0;
            this.GUNPOWDER = 0;
            this.ARROW = 0;
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
            GL11.glPopMatrix();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        this.drawcard();
    }
    
    static {
        itemRender = Minecraft.getMinecraft().getRenderItem();
    }
}
