//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import ru.internali.settings.*;

public class BooleanSetting extends Setting
{
    private boolean value;
    
    public void setValue(final boolean value) {
        this.value = value;
    }
    
    public void toggle() {
        this.setValue(!this.value);
    }
    
    public boolean getValue() {
        return this.value;
    }
    
    public BooleanSetting(final String s, final String s2, final boolean value) {
        super(s, s2);
        this.value = value;
    }
}
