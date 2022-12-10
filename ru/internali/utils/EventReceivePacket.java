//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.network.*;

public class EventReceivePacket extends EventCancellable
{
    private static Packet packet;
    
    public boolean cancel() {
        return false;
    }
    
    public void setPacket(final Packet packet) {
        EventReceivePacket.packet = packet;
    }
    
    public EventReceivePacket(final Packet packet) {
        EventReceivePacket.packet = packet;
    }
    
    public static Packet getPacket() {
        return EventReceivePacket.packet;
    }
}
