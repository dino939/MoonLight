//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.settings.*;
import ru.internali.module.*;

public class InventoryMove extends Module
{
    TimerUtil timerUtil;
    
    public static float getDirection() {
        float rotationYaw = InventoryMove.mc.player.rotationYaw;
        if (InventoryMove.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (InventoryMove.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (InventoryMove.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (InventoryMove.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (InventoryMove.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    void handleRight(double n) {
        n = 0.046;
        if (!Keyboard.isKeyDown(InventoryMove.mc.gameSettings.keyBindRight.getKeyCode())) {
            return;
        }
        this.moveRight(n);
    }
    
    void moveLeft(double n) {
        n = 0.046;
        final float direction = getDirection();
        final EntityPlayerSP player = InventoryMove.mc.player;
        player.motionZ += MathHelper.sin(direction) * n;
        final EntityPlayerSP player2 = InventoryMove.mc.player;
        player2.motionX += MathHelper.cos(direction) * n;
    }
    
    void moveBack(double n) {
        n = 0.046;
        final float direction = getDirection();
        final EntityPlayerSP player = InventoryMove.mc.player;
        player.motionX += MathHelper.sin(direction) * n;
        final EntityPlayerSP player2 = InventoryMove.mc.player;
        player2.motionZ -= MathHelper.cos(direction) * n;
    }
    
    void handleLeft(double n) {
        n = 0.046;
        if (!Keyboard.isKeyDown(InventoryMove.mc.gameSettings.keyBindLeft.getKeyCode())) {
            return;
        }
        this.moveLeft(n);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent clientTickEvent) {
        if (InventoryMove.mc.currentScreen == null || InventoryMove.mc.currentScreen instanceof GuiChat || InventoryMove.mc.player == null || InventoryMove.mc.world == null) {
            return;
        }
        this.timerUtil.reset();
        double n = 0.046;
        if (!InventoryMove.mc.player.onGround) {
            n /= 4.0;
        }
        this.handleJump();
        this.handleForward(n);
        if (!InventoryMove.mc.player.onGround) {
            n /= 2.0;
        }
        this.handleBack(n);
        this.handleLeft(n);
        this.handleRight(n);
    }
    
    void handleForward(double n) {
        n = 0.046;
        if (!Keyboard.isKeyDown(InventoryMove.mc.gameSettings.keyBindForward.getKeyCode())) {
            return;
        }
        this.moveForward(n);
    }
    
    void handleJump() {
        if (InventoryMove.mc.player.onGround && Keyboard.isKeyDown(InventoryMove.mc.gameSettings.keyBindJump.getKeyCode())) {
            InventoryMove.mc.player.jump();
        }
    }
    
    public static void setPressed(final KeyBinding keyBinding, final boolean b) {
        try {
            keyBinding.getClass().getDeclaredField("pressed");
        }
        catch (NoSuchFieldException cause) {
            throw new RuntimeException(cause);
        }
    }
    
    void moveForward(double n) {
        n = 0.046;
        final float direction = getDirection();
        InventoryMove.mc.player.setSprinting(true);
        final EntityPlayerSP player = InventoryMove.mc.player;
        player.motionX -= MathHelper.sin(direction) * n;
        final EntityPlayerSP player2 = InventoryMove.mc.player;
        player2.motionZ += MathHelper.cos(direction) * n;
    }
    
    void moveRight(double n) {
        n = 0.046;
        final float direction = getDirection();
        final EntityPlayerSP player = InventoryMove.mc.player;
        player.motionZ -= MathHelper.sin(direction) * n;
        final EntityPlayerSP player2 = InventoryMove.mc.player;
        player2.motionX -= MathHelper.cos(direction) * n;
    }
    
    void handleBack(double n) {
        n = 0.046;
        if (!Keyboard.isKeyDown(InventoryMove.mc.gameSettings.keyBindBack.getKeyCode())) {
            return;
        }
        this.moveBack(n);
    }
    
    public InventoryMove(final String s, final String s2, final Category category) {
        super(s, s2, category);
        this.timerUtil = new TimerUtil();
    }
}
