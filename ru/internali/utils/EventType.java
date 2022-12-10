//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public enum EventType
{
    SEND("SEND", 2);
    
    private static final EventType[] $VALUES;
    
    PRE("PRE", 0), 
    POST("POST", 1), 
    RECEIVE("RECEIVE", 3);
    
    static {
        $VALUES = new EventType[] { EventType.PRE, EventType.POST, EventType.SEND, EventType.RECEIVE };
    }
    
    private EventType(final String name, final int ordinal) {
    }
}
