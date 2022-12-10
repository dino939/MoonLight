//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.module.misc.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class Speed extends Module
{
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (MovementUtil.isMoving() && Speed.mc.player.onGround) {
            Speed.mc.player.move(MoverType.PLAYER, 0.0, 0.16, 0.0);
            final EntityPlayerSP player = Speed.mc.player;
            player.motionX *= 1.04;
            final EntityPlayerSP player2 = Speed.mc.player;
            player2.motionZ *= 1.04;
        }
    }
    
    public Speed() {
        super("Speed", "Speed", Category.MOVEMENT);
    }
}
