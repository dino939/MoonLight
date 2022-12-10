//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraftforge.client.event.*;
import org.lwjgl.input.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import ru.internali.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.mojang.realmsclient.gui.*;
import ru.internali.*;
import ru.internali.module.*;
import java.util.*;
import ru.internali.settings.*;

public class AmmoGun extends Module
{
    public String held;
    public static boolean canMove;
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        if (Mouse.isButtonDown(0)) {
            this.mouseClicked(Mouse.getX(), Mouse.getY(), 0);
        }
        Minecraft.getMinecraft().getRenderItem();
        final ItemStack itemStack = new ItemStack(Items.END_CRYSTAL, 1);
        GL11.glPushMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        AmmoGun.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, this.getPosX() / 2 - 5, this.getPosY() / -2 + 530);
        AmmoGun.mc.getRenderItem().renderItemOverlayIntoGUI(AmmoGun.mc.fontRenderer, itemStack, this.getPosX() / 2 - 5, this.getPosY() / -2 + 530, String.valueOf(new StringBuilder().append(this.colorchoice()).append(String.valueOf(InventoryUtil.getItems(Items.END_CRYSTAL)))));
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        GL11.glPopMatrix();
    }
    
    private ChatFormatting colorchoice() {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        switch (valString) {
            case "BLACK": {
                return ChatFormatting.BLACK;
            }
            case "RED": {
                return ChatFormatting.RED;
            }
            case "AQUA": {
                return ChatFormatting.AQUA;
            }
            case "BLUE": {
                return ChatFormatting.BLUE;
            }
            case "GOLD": {
                return ChatFormatting.GOLD;
            }
            case "GRAY": {
                return ChatFormatting.GRAY;
            }
            case "WHITE": {
                return ChatFormatting.WHITE;
            }
            case "GREEN": {
                return ChatFormatting.GREEN;
            }
            case "YELLOW": {
                return ChatFormatting.YELLOW;
            }
            case "DARK_RED": {
                return ChatFormatting.DARK_RED;
            }
            case "DARK_AQUA": {
                return ChatFormatting.DARK_AQUA;
            }
            case "DARK_BLUE": {
                return ChatFormatting.DARK_BLUE;
            }
            case "DARK_GRAY": {
                return ChatFormatting.DARK_GRAY;
            }
            case "DARK_GREEN": {
                return ChatFormatting.DARK_GREEN;
            }
            case "DARK_PURPLE": {
                return ChatFormatting.LIGHT_PURPLE;
            }
            case "LIGHT_PURPLE": {
                return ChatFormatting.DARK_PURPLE;
            }
            default: {
                return ChatFormatting.WHITE;
            }
        }
    }
    
    public AmmoGun() {
        super("AmmoGun", "AmmoGun", Category.HUD);
        this.held = "Waiting";
        final ArrayList<String> list = new ArrayList<String>();
        list.add("BLACK");
        list.add("RED");
        list.add("AQUA");
        list.add("BLUE");
        list.add("GOLD");
        list.add("GRAY");
        list.add("WHITE");
        list.add("GREEN");
        list.add("YELLOW");
        list.add("DARK_RED");
        list.add("DARK_AQUA");
        list.add("DARK_BLUE");
        list.add("DARK_GRAY");
        list.add("DARK_GREEN");
        list.add("DARK_PURPLE");
        list.add("LIGHT_PURPLE");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "AQUA", list));
    }
    
    public void mouseClicked(final int posX, final int posY, final int n) {
        AmmoGun.canMove = true;
        if (n == 0) {
            this.setHeld(this.getName());
            if (this.isMouseOnButton(posX, posY) && AmmoGun.canMove) {
                this.setPosX(posX);
                this.setPosY(posY);
            }
        }
        if (!this.isMouseOnButton(posX, posY) || n == 1) {}
    }
    
    public boolean isMouseOnButton(final int n, final int n2) {
        return n >= this.getPosX() - 20 && n <= this.getPosX() + 20 && n2 >= this.getPosY() - 15 && n2 <= this.getPosY() + 20;
    }
    
    public void setHeld(final String s) {
        this.held = this.getName();
    }
    
    static {
        AmmoGun.canMove = false;
    }
    
    public String getHeld() {
        return this.held;
    }
    
    private enum color
    {
        DARK_GRAY("DARK_GRAY", 8);
        
        private static final color[] $VALUES;
        
        DARK_BLUE("DARK_BLUE", 1), 
        DARK_PURPLE("DARK_PURPLE", 5), 
        AQUA("AQUA", 11), 
        GRAY("GRAY", 7), 
        BLUE("BLUE", 9), 
        WHITE("WHITE", 15), 
        GOLD("GOLD", 6), 
        RED("RED", 12), 
        GREEN("GREEN", 10), 
        DARK_AQUA("DARK_AQUA", 3), 
        BLACK("BLACK", 0), 
        LIGHT_PURPLE("LIGHT_PURPLE", 13), 
        DARK_RED("DARK_RED", 4), 
        DARK_GREEN("DARK_GREEN", 2), 
        YELLOW("YELLOW", 14);
        
        private color(final String name, final int ordinal) {
        }
        
        static {
            $VALUES = new color[] { color.BLACK, color.DARK_BLUE, color.DARK_GREEN, color.DARK_AQUA, color.DARK_RED, color.DARK_PURPLE, color.GOLD, color.GRAY, color.DARK_GRAY, color.BLUE, color.GREEN, color.AQUA, color.RED, color.LIGHT_PURPLE, color.YELLOW, color.WHITE };
        }
    }
}
