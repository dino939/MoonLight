//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import java.util.*;
import ru.internali.module.*;
import net.minecraftforge.client.event.sound.*;
import net.minecraft.network.play.server.*;
import ru.internali.module.movement.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ExpInfo extends Module
{
    ArrayList exploids;
    
    public ExpInfo() {
        super("ExpInfo", "ExpInfo", Category.HUD);
        this.exploids = new ArrayList();
    }
    
    @SubscribeEvent
    public void onPacket(final PlaySoundEvent playSoundEvent) {
        if (playSoundEvent.getSound() instanceof SPacketCustomSound) {
            final SPacketCustomSound sPacketCustomSound = (SPacketCustomSound)playSoundEvent.getSound();
            final String getSoundName = sPacketCustomSound.getSoundName();
            switch (getSoundName) {
                case "timed_explosive_charge.explosion.1.3p":
                case "timed_explosive_charge.explosion.2.far.3p":
                case "timed_explosive_charge.explosion.2.3p":
                case "timed_explosive_charge.explosion.1.far.3p": {
                    Command.sendClientSideMessage(String.valueOf(new StringBuilder().append("Found Sound X:").append((int)sPacketCustomSound.getX()).append(" Z:").append((int)sPacketCustomSound.getY()).append(" Y:").append((int)sPacketCustomSound.getZ())));
                    break;
                }
            }
        }
    }
}
