//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;

@Cancelable
public class PacketEvent extends Event
{
    private static Packet packet;
    
    public static Packet getPacket() {
        return PacketEvent.packet;
    }
    
    public PacketEvent(final Packet packet) {
        PacketEvent.packet = packet;
    }
    
    public void setPacket(final Packet packet) {
        PacketEvent.packet = packet;
    }
}
