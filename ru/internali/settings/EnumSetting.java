//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.settings;

public class EnumSetting extends Setting
{
    private Enum currentValue;
    private final Enum[] values;
    
    public String getCurrentValueName() {
        return this.currentValue.toString();
    }
    
    public boolean setValueFromName(final String s) {
        for (final Enum currentValue : this.values) {
            if (s.equalsIgnoreCase(currentValue.toString())) {
                this.setCurrentValue(currentValue);
                return true;
            }
        }
        return false;
    }
    
    public void setCurrentValue(final Enum currentValue) {
        this.currentValue = currentValue;
    }
    
    public EnumSetting(final String s, final String s2, final Enum[] values, final Enum currentValue) {
        super(s, s2);
        this.values = values;
        this.currentValue = currentValue;
    }
    
    public Enum[] getValues() {
        return this.values;
    }
    
    public Enum getCurrentValue() {
        return this.currentValue;
    }
}
