//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class EventRenderScoreboard extends Event
{
    private EventType state;
    
    public boolean isPre() {
        return this.state == EventType.PRE;
    }
    
    public EventRenderScoreboard(final EventType state) {
        this.state = state;
    }
    
    public void setState(final EventType state) {
        this.state = state;
    }
    
    public EventType getState() {
        return this.state;
    }
}
