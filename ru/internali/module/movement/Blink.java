//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import ru.internali.module.*;
import java.util.*;
import ru.internali.utils.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.gameevent.*;

public class Blink extends Module
{
    Queue packets;
    int delay;
    
    public Blink() {
        super("Blink", "Blink", Category.MOVEMENT);
        this.delay = 0;
        this.packets = new LinkedList();
    }
    
    @SubscribeEvent
    public void onPlayerTick(final PacketEvent packetEvent) {
        if (PacketEvent.getPacket() instanceof CPacketPlayer && this.delay <= 20) {
            packetEvent.setCanceled(true);
            this.packets.add(PacketEvent.getPacket());
        }
    }
    
    public void onDisable() {
        while (!this.packets.isEmpty()) {
            Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        ++this.delay;
        if (this.delay > 20) {
            while (!this.packets.isEmpty()) {
                Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
                this.packets.clear();
                this.delay = 0;
            }
        }
    }
}
