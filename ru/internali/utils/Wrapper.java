//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.settings.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.*;

public class Wrapper
{
    public static volatile Wrapper INSTANCE;
    
    public Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public InventoryPlayer inventory() {
        return this.player().inventory;
    }
    
    static {
        Wrapper.INSTANCE = new Wrapper();
    }
    
    public GameSettings mcSettings() {
        return Wrapper.INSTANCE.mc().gameSettings;
    }
    
    public void sendPacket(final Packet packet) {
        this.player().connection.sendPacket(packet);
    }
    
    public EntityPlayerSP getLocalPlayer() {
        return Wrapper.INSTANCE.getMinecraft().player;
    }
    
    public GameSettings getGameSettings() {
        return Wrapper.INSTANCE.getMinecraft().gameSettings;
    }
    
    public PlayerControllerMP controller() {
        return Wrapper.INSTANCE.mc().playerController;
    }
    
    public Minecraft mc() {
        return Minecraft.getMinecraft();
    }
    
    public FontRenderer fontRenderer() {
        return Wrapper.INSTANCE.mc().fontRenderer;
    }
    
    public FontRenderer getFontRenderer() {
        return Wrapper.INSTANCE.getMinecraft().fontRenderer;
    }
    
    public WorldClient getWorld() {
        return Wrapper.INSTANCE.getMinecraft().world;
    }
    
    public WorldClient world() {
        return Wrapper.INSTANCE.mc().world;
    }
    
    public EntityPlayerSP player() {
        return Wrapper.INSTANCE.mc().player;
    }
}
