//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module;

import java.util.*;
import net.minecraft.client.*;
import ru.internali.module.misc.*;
import net.minecraftforge.common.*;
import net.minecraft.util.text.*;
import ru.internali.notifications.*;
import java.io.*;
import net.minecraftforge.client.event.*;
import ru.internali.utils.*;

public abstract class Module
{
    private int key;
    public final BooleanSetting drawOnHud;
    public boolean visible;
    private String name;
    float PosX;
    public boolean enable;
    private List settings;
    protected static Minecraft mc;
    private boolean toggled;
    int posX;
    int posY;
    private Category category;
    private String description;
    float sizeY;
    float PosY;
    float sizeX;
    
    public String getDescription() {
        return this.description;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (!SelfDestruct.self) {
            if (this.toggled) {
                this.onEnable();
            }
            else {
                this.onDisable();
            }
        }
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        notifications.add(this.name, String.valueOf(new StringBuilder().append(TextFormatting.RED).append("Disable!")), Type.Red);
    }
    
    public int getPosX() {
        return this.posX;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void setPosY(final int posY) {
        this.posY = posY;
    }
    
    public void setPosX(final int posX) {
        this.posX = posX;
    }
    
    public Module(final String name, final String description, final Category category) {
        this.posX = 100;
        this.posY = 100;
        this.visible = true;
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
        this.enable = false;
        this.drawOnHud = new BooleanSetting("Draw on Hud", "drawonhud", true);
    }
    
    public void onEnableA() throws IOException {
        EventManager.register(this);
    }
    
    public void setSizeY(final int n) {
        this.sizeY = (float)n;
    }
    
    public int getSizeX() {
        return (int)this.sizeX;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void onDisableA() {
        EventManager.unregister(this);
    }
    
    public void setEnable(final boolean enable) {
        this.enable = enable;
        if (enable) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (!SelfDestruct.self) {
            if (this.toggled) {
                this.onEnableR();
            }
            else {
                this.onDisableR();
                this.onDisable();
            }
        }
    }
    
    public void toggler() {
        this.toggled = !this.toggled;
        this.onToggle();
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public void setSizeX(final int n) {
        this.sizeX = (float)n;
    }
    
    public void onRender3D() {
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        notifications.add(this.name, String.valueOf(new StringBuilder().append(TextFormatting.GREEN).append("Enable!")), Type.Green);
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    protected void onCameraSetup(final EntityViewRenderEvent.CameraSetup cameraSetup) {
    }
    
    public static double deltaTime() {
        Minecraft.getMinecraft();
        double n2;
        if (Minecraft.getDebugFPS() > 0) {
            final double n = 1.0;
            Minecraft.getMinecraft();
            n2 = n / Minecraft.getDebugFPS();
        }
        else {
            n2 = 1.0;
        }
        return n2;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public int getSizeY() {
        return (int)this.sizeY;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public void setEnabled(final boolean toggled) {
        if (toggled) {
            EventManager.register(this);
        }
        else {
            EventManager.unregister(this);
        }
        this.toggled = toggled;
    }
    
    public boolean isHud() {
        return this.sizeY != 0.0f || this.sizeX != 0.0f;
    }
    
    public void onUpdate(final PlayerHook playerHook) {
    }
    
    public boolean isEnable() {
        return this.enable;
    }
    
    public void onToggle() {
    }
    
    public void onDisableR() {
        this.onDisable();
    }
    
    static {
        Module.mc = Minecraft.getMinecraft();
    }
    
    public List getSettings() {
        return this.settings;
    }
    
    public void onEnableR() {
        this.onEnable();
    }
    
    public boolean isToggler() {
        return this.toggled;
    }
}
