//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.notifications;

public enum Type
{
    OK("OK", 2), 
    Red("Red", 1);
    
    private static final Type[] $VALUES;
    
    Green("Green", 0);
    
    static {
        $VALUES = new Type[] { Type.Green, Type.Red, Type.OK };
    }
    
    private Type(final String name, final int ordinal) {
    }
}
