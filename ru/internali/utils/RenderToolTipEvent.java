//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.item.*;

public class RenderToolTipEvent extends Event
{
    private int y;
    private int x;
    private ItemStack stack;
    
    public int getY() {
        return this.y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public ItemStack getStack() {
        return this.stack;
    }
    
    public RenderToolTipEvent(final ItemStack stack, final int x, final int y) {
        this.stack = stack;
        this.x = x;
        this.y = y;
    }
}
