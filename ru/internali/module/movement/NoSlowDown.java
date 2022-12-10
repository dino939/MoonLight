//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.potion.*;
import ru.internali.utils.*;
import java.util.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class NoSlowDown extends Module
{
    public static List Modes;
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent inputUpdateEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Item").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "Sneak").getValBoolean();
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
            if (valBoolean && Minecraft.getMinecraft().player.isHandActive() && !Minecraft.getMinecraft().player.isRiding()) {
                final MovementInput movementInput = Minecraft.getMinecraft().player.movementInput;
                movementInput.moveStrafe /= 0.2f;
                final MovementInput movementInput2 = Minecraft.getMinecraft().player.movementInput;
                movementInput2.moveForward /= 0.2f;
            }
            if (valBoolean2 && Minecraft.getMinecraft().player.isSneaking()) {
                final MovementInput movementInput3 = Minecraft.getMinecraft().player.movementInput;
                movementInput3.moveStrafe /= 0.3f;
                final MovementInput movementInput4 = Minecraft.getMinecraft().player.movementInput;
                movementInput4.moveForward /= 0.3f;
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (NoSlowDown.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(2)))) {
            MoveUtils.setSpeed(0.23000000417232513);
        }
    }
    
    static {
        NoSlowDown.Modes = new ArrayList();
    }
    
    public NoSlowDown() {
        super("NoSlowDown", "NoSlowDown", Category.MOVEMENT);
        CatClient.instance.settingsManager.rSetting(new Setting("Item", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Sneak", this, false));
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
}
