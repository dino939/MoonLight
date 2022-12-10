//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.CHAT;

import ru.internali.utils.*;
import net.minecraft.network.*;

public class PacketEvents extends EventCancellable
{
    private final Packet packet;
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public boolean cancel() {
        return false;
    }
    
    public PacketEvents(final Packet packet) {
        this.packet = packet;
    }
    
    public static class Send extends PacketEvents
    {
        public Send(final Packet packet) {
            super(packet);
        }
    }
    
    public static class Receive extends PacketEvents
    {
        public Receive(final Packet packet) {
            super(packet);
        }
    }
}
