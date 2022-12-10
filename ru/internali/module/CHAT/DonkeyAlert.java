//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.CHAT;

import com.mojang.realmsclient.gui.*;
import ru.internali.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import ru.internali.module.movement.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import java.util.*;

public class DonkeyAlert extends Module
{
    private int antiSpam;
    
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
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "DonkeyAlert").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "MuleAlert").getValBoolean();
        final boolean valBoolean3 = CatClient.instance.settingsManager.getSettingByName(this, "LlamaAlert").getValBoolean();
        final boolean valBoolean4 = CatClient.instance.settingsManager.getSettingByName(this, "HorseAlert").getValBoolean();
        final boolean valBoolean5 = CatClient.instance.settingsManager.getSettingByName(this, "ShulkerAlert").getValBoolean();
        final boolean valBoolean6 = CatClient.instance.settingsManager.getSettingByName(this, "VillagerAlert").getValBoolean();
        final boolean valBoolean7 = CatClient.instance.settingsManager.getSettingByName(this, "ZombieVillagerAlert").getValBoolean();
        ++this.antiSpam;
        for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof EntityDonkey && valBoolean && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found Donkey! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
            if (entity instanceof EntityMule && valBoolean2 && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found Mule! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
            if (entity instanceof EntityLlama && valBoolean3 && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found Llama! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
            if (entity instanceof EntityHorse && valBoolean4 && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found Horse! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
            if (entity instanceof EntityShulker && valBoolean5 && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found Shulker! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
            if (entity instanceof EntityVillager && valBoolean6 && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found Villager! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
            if (entity instanceof EntityZombieVillager && valBoolean7 && this.antiSpam >= 100) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(this.colorchoice()).append(" Found ZombieVillager! X:").append((int)entity.posX).append(" Z:").append((int)entity.posZ).append(" Y:").append((int)entity.posY)));
                this.antiSpam = -600;
            }
        }
    }
    
    public DonkeyAlert() {
        super("DonkeyAlert", "DonkeyAlert", Category.CHAT);
        CatClient.instance.settingsManager.rSetting(new Setting("DonkeyAlert", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("MuleAlert", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("LlamaAlert", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("HorseAlert", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("ShulkerAlert", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("VillagerAlert", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("ZombieVillagerAlert", this, false));
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
    
    private enum color
    {
        GRAY("GRAY", 7), 
        LIGHT_PURPLE("LIGHT_PURPLE", 13), 
        GOLD("GOLD", 6), 
        DARK_BLUE("DARK_BLUE", 1), 
        WHITE("WHITE", 15), 
        BLACK("BLACK", 0), 
        RED("RED", 12);
        
        private static final color[] $VALUES;
        
        YELLOW("YELLOW", 14), 
        DARK_AQUA("DARK_AQUA", 3), 
        BLUE("BLUE", 9), 
        GREEN("GREEN", 10), 
        DARK_RED("DARK_RED", 4), 
        DARK_GREEN("DARK_GREEN", 2), 
        DARK_GRAY("DARK_GRAY", 8), 
        AQUA("AQUA", 11), 
        DARK_PURPLE("DARK_PURPLE", 5);
        
        private color(final String name, final int ordinal) {
        }
        
        static {
            $VALUES = new color[] { color.BLACK, color.DARK_BLUE, color.DARK_GREEN, color.DARK_AQUA, color.DARK_RED, color.DARK_PURPLE, color.GOLD, color.GRAY, color.DARK_GRAY, color.BLUE, color.GREEN, color.AQUA, color.RED, color.LIGHT_PURPLE, color.YELLOW, color.WHITE };
        }
    }
}
