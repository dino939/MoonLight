//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import ru.internali.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;
import ru.internali.utils.*;

public class BombInfo extends Module
{
    private ItemStack BOOK2;
    private int SPECKLED_MELON;
    private int FLINT;
    private final FontRenderer fr;
    private ItemStack SLIME_BALL2;
    private int ARROW;
    private ItemStack SPECTRAL_ARROW2;
    private ItemStack FLINT2;
    private int PRISMARINE_SHARD;
    private static final RenderItem itemRender;
    private int SHULKER_SHELL;
    private static RenderItem kappita;
    private int BOOK;
    ScaledResolution sr;
    private ItemStack ARROW2;
    private ItemStack PRISMARINE_SHARD2;
    private int SPECTRAL_ARROW;
    private ItemStack SPECKLED_MELON2;
    private int SLIME_BALL;
    private ItemStack SHULKER_SHELL2;
    
    static {
        itemRender = Minecraft.getMinecraft().getRenderItem();
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
                if (getItem == Items.BOOK) {
                    this.BOOK += getStack.getCount();
                    this.BOOK2 = getStack;
                }
                else if (getItem == Items.SPECKLED_MELON) {
                    this.SPECKLED_MELON += getStack.getCount();
                    this.SPECKLED_MELON2 = getStack;
                }
                else if (getItem == Items.SLIME_BALL) {
                    this.SLIME_BALL += getStack.getCount();
                    this.SLIME_BALL2 = getStack;
                }
                else if (getItem == Items.FLINT) {
                    this.FLINT += getStack.getCount();
                    this.FLINT2 = getStack;
                }
                else if (getItem == Items.PRISMARINE_SHARD) {
                    this.PRISMARINE_SHARD += getStack.getCount();
                    this.PRISMARINE_SHARD2 = getStack;
                }
                else if (getItem == Items.SHULKER_SHELL) {
                    this.SHULKER_SHELL += getStack.getCount();
                    this.SHULKER_SHELL2 = getStack;
                }
                else if (getItem == Items.SPECTRAL_ARROW) {
                    this.SPECTRAL_ARROW += getStack.getCount();
                    this.SPECTRAL_ARROW2 = getStack;
                }
                else if (getItem == Items.ARROW) {
                    this.ARROW += getStack.getCount();
                    this.ARROW2 = getStack;
                }
            }
            int n3 = 0;
            if (this.BOOK != 0) {
                this.drawitem(this.BOOK2, 0, n3 * 25, this.BOOK);
                ++n3;
            }
            if (this.SPECKLED_MELON != 0) {
                this.drawitem(this.SPECKLED_MELON2, 0, n3 * 25, this.SPECKLED_MELON);
                ++n3;
            }
            if (this.SLIME_BALL != 0) {
                this.drawitem(this.SLIME_BALL2, 0, n3 * 25, this.SLIME_BALL);
                ++n3;
            }
            if (this.FLINT != 0) {
                this.drawitem(this.FLINT2, 0, n3 * 25, this.FLINT);
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
            if (this.SPECTRAL_ARROW != 0) {
                this.drawitem(this.SPECTRAL_ARROW2, 0, n3 * 25, this.SPECTRAL_ARROW);
                ++n3;
            }
            if (this.ARROW != 0) {
                this.drawitem(this.ARROW2, 0, n3 * 25, this.ARROW);
                ++n3;
            }
            this.BOOK = 0;
            this.SPECKLED_MELON = 0;
            this.SLIME_BALL = 0;
            this.FLINT = 0;
            this.PRISMARINE_SHARD = 0;
            this.SHULKER_SHELL = 0;
            this.SPECTRAL_ARROW = 0;
            this.ARROW = 0;
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
            GL11.glPopMatrix();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public BombInfo() {
        super("BombInfo", "show Cartiges", Category.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        this.sr = new ScaledResolution(BombInfo.mc);
        this.BOOK = 0;
        this.SPECKLED_MELON = 0;
        this.SLIME_BALL = 0;
        this.FLINT = 0;
        this.PRISMARINE_SHARD = 0;
        this.SHULKER_SHELL = 0;
        this.SPECTRAL_ARROW = 0;
        this.ARROW = 0;
        CatClient.instance.settingsManager.rSetting(new Setting("PosX", this, 0.0, 0.0, 300.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("PosY", this, 50.0, 0.0, 280.0, true));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        this.drawcard();
    }
    
    public void drawitem(final ItemStack itemStack, final int n, final int n2, final int i) {
        BombInfo.itemRender.zLevel = 1.0f;
        this.fr.drawString("", 0, 0, Color.white.getRGB());
        RenderUtils.drawShadowRect(n, n2, n + 19, n2 + 20, 2);
        GlStateManager.resetColor();
        BombInfo.itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
        BombInfo.itemRender.renderItemOverlayIntoGUI(BombInfo.mc.fontRenderer, itemStack, n, n2, String.valueOf(new StringBuilder().append("").append(i)));
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
    }
}
