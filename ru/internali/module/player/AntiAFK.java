//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class AntiAFK extends Module
{
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (this.isToggled() && AntiAFK.mc.player.onGround) {
            AntiAFK.mc.player.jump();
            KeyBinding.setKeyBindState(AntiAFK.mc.gameSettings.keyBindSneak.getKeyCode(), true);
            KeyBinding.setKeyBindState(AntiAFK.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }
    }
    
    public AntiAFK() {
        super("AntiAFK", "AntiAFK", Category.PLAYER);
    }
}
