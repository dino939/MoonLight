//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import ru.internali.module.*;
import net.minecraftforge.client.event.sound.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;
import net.minecraft.client.settings.*;

public class AutoFishMod extends Module
{
    private transient Timer timer;
    
    public AutoFishMod() {
        super("AutoFish", "AutoFishMod", Category.OUTHER);
        this.timer = new Timer();
    }
    
    @SubscribeEvent
    public void onPlaySound(final PlaySoundEvent playSoundEvent) {
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.fishEntity != null && playSoundEvent.getName().equals("entity.bobber.splash")) {
            this.sheduleUse(500);
            this.sheduleUse(1000);
        }
    }
    
    private void sheduleUse(final int n) {
        this.timer.schedule(new TimerTask() {
            final AutoFishMod this$0;
            
            @Override
            public void run() {
                KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
            }
        }, n);
    }
}
