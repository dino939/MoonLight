//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module;

public enum Category
{
    HUD("HUD", 5);
    
    private static final Category[] $VALUES;
    
    RENDER("RENDER", 3), 
    MOVEMENT("MOVEMENT", 1), 
    WORLD("WORLD", 7), 
    MANAGER("MANAGER", 6), 
    CONFIG("CONFIG", 9), 
    PLAYER("PLAYER", 2), 
    COMBAT("COMBAT", 0), 
    OUTHER("OUTHER", 4), 
    CHAT("CHAT", 8);
    
    static {
        $VALUES = new Category[] { Category.COMBAT, Category.MOVEMENT, Category.PLAYER, Category.RENDER, Category.OUTHER, Category.HUD, Category.MANAGER, Category.WORLD, Category.CHAT, Category.CONFIG };
    }
    
    private Category(final String name, final int ordinal) {
    }
}
