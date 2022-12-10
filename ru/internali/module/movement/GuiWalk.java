//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import new_gui.*;
import clickgui.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class GuiWalk extends Module
{
    public void onDisable() {
        super.onDisable();
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onKeyUpdate(final InputUpdateEvent inputUpdateEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Sneak").getValBoolean();
        if (GuiWalk.mc.world != null && GuiWalk.mc.player != null && (GuiWalk.mc.currentScreen instanceof GuiContainer || GuiWalk.mc.currentScreen instanceof GuiIngameMenu || GuiWalk.mc.currentScreen instanceof GuiOptions || GuiWalk.mc.currentScreen instanceof CSGOGui || GuiWalk.mc.currentScreen instanceof Clickgui)) {
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindForward.getKeyCode())) {
                final MovementInput movementInput = GuiWalk.mc.player.movementInput;
                ++movementInput.moveForward;
                GuiWalk.mc.player.movementInput.forwardKeyDown = true;
            }
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindBack.getKeyCode())) {
                final MovementInput movementInput2 = GuiWalk.mc.player.movementInput;
                --movementInput2.moveForward;
                GuiWalk.mc.player.movementInput.backKeyDown = true;
            }
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindRight.getKeyCode())) {
                final MovementInput movementInput3 = GuiWalk.mc.player.movementInput;
                --movementInput3.moveStrafe;
                GuiWalk.mc.player.movementInput.rightKeyDown = true;
            }
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindLeft.getKeyCode())) {
                final MovementInput movementInput4 = GuiWalk.mc.player.movementInput;
                ++movementInput4.moveStrafe;
                GuiWalk.mc.player.movementInput.rightKeyDown = true;
            }
            GuiWalk.mc.player.movementInput.jump = Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindJump.getKeyCode());
            GuiWalk.mc.player.movementInput.sneak = (valBoolean && Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindSneak.getKeyCode()));
            if (GuiWalk.mc.player.movementInput.sneak) {
                GuiWalk.mc.player.movementInput.moveStrafe *= (float)0.3;
                GuiWalk.mc.player.movementInput.moveForward *= (float)0.3;
            }
        }
    }
    
    public GuiWalk() {
        super("GuiWalk", "go", Category.MOVEMENT);
        CatClient.instance.settingsManager.rSetting(new Setting("Sneak", this, false));
    }
}
