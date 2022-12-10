//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import ru.internali.utils.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class BackTP extends Module
{
    public TimerUtils timerHelper;
    
    public void onDisable() {
        BackTP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BackTP.mc.player.posX, BackTP.mc.player.posY + 5.0, BackTP.mc.player.posZ, true));
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        BackTP.mc.player.onGround = true;
        BackTP.mc.player.setSprinting(false);
        BackTP.mc.player.onGround = false;
        if (!BackTP.mc.player.isSneaking()) {
            return;
        }
        if (BackTP.mc.player.ticksExisted % 5 == 0) {
            BackTP.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BackTP.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        if (BackTP.mc.player.onGround) {
            BackTP.mc.player.jump();
        }
        if (this.timerHelper.hasReached(30.0f)) {
            BackTP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BackTP.mc.player.posX, 200.0, BackTP.mc.player.posZ, true));
            this.timerHelper.reset();
        }
    }
    
    public void onEnable() {
        BackTP.mc.player.getPosition().getX();
        BackTP.mc.player.getPosition().getY();
        BackTP.mc.player.getPosition().getZ();
        super.onEnable();
    }
    
    public BackTP() {
        super("BackTP", "Clip", Category.PLAYER);
    }
}
