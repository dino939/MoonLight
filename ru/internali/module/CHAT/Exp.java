//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.CHAT;

import ru.internali.module.*;
import net.minecraftforge.client.event.sound.*;
import ru.internali.module.movement.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Exp extends Module
{
    public Exp() {
        super("SoundInfo", "SoundInfo", Category.CHAT);
    }
    
    @SubscribeEvent
    public void Exp(final PlaySoundEvent playSoundEvent) {
        final float getXPosF = playSoundEvent.getSound().getXPosF();
        final float getYPosF = playSoundEvent.getSound().getYPosF();
        final float getZPosF = playSoundEvent.getSound().getZPosF();
        playSoundEvent.getName().equals("entity.bobber.splash");
        Command.sendClientSideMessage(String.valueOf(new StringBuilder().append("Found Sound X:").append((int)getXPosF).append(" Z:").append((int)getYPosF).append(" Y:").append((int)getZPosF)));
    }
}
