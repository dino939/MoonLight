//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.network.play.server.*;

public class EventPlayerList extends Event
{
    private static SPacketPlayerListItem.Action action;
    private static SPacketPlayerListItem.AddPlayerData data;
    
    public static SPacketPlayerListItem.AddPlayerData getData() {
        return EventPlayerList.data;
    }
    
    public static SPacketPlayerListItem.Action getAction() {
        return EventPlayerList.action;
    }
    
    public EventPlayerList(final SPacketPlayerListItem.Action action, final SPacketPlayerListItem.AddPlayerData data) {
        EventPlayerList.action = action;
        EventPlayerList.data = data;
    }
}
