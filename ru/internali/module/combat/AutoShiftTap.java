//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import org.w3c.dom.css.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import net.minecraftforge.common.*;

public class AutoShiftTap extends Module
{
    public int ticks;
    Counter counter;
    
    @SubscribeEvent
    public void OnPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (AutoShiftTap.mc.gameSettings.keyBindAttack.isKeyDown()) {
            KeyBinding.setKeyBindState(AutoShiftTap.mc.gameSettings.keyBindSneak.getKeyCode(), true);
            this.ticks = 0;
        }
        if (this.ticks == 2) {
            KeyBinding.setKeyBindState(AutoShiftTap.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }
        ++this.ticks;
    }
    
    public AutoShiftTap() {
        super("AutoShiftTap", "AutoShift", Category.COMBAT);
        this.ticks = 0;
    }
    
    @Override
    public void onEnable() {
        this.ticks = 0;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
}
