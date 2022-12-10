//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package net.minecraft.client.gui;

import net.minecraft.util.text.*;

public class ChatLine
{
    private final int updateCounterCreated;
    private final ITextComponent lineString;
    private final int chatLineID;
    
    public ChatLine(final int updateCounterCreated, final ITextComponent lineString, final int chatLineID) {
        this.lineString = lineString;
        this.updateCounterCreated = updateCounterCreated;
        this.chatLineID = chatLineID;
    }
    
    public ITextComponent getChatComponent() {
        return this.lineString;
    }
    
    public int getUpdatedCounter() {
        return this.updateCounterCreated;
    }
    
    public int getChatLineID() {
        return this.chatLineID;
    }
}
