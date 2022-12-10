//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import ru.internali.settings.*;
import ru.internali.module.*;
import java.util.*;

public class NumberSetting extends Setting
{
    private float current;
    private float minimum;
    private float maximum;
    private String desc;
    private float increment;
    
    public float getNumberValue() {
        return this.current;
    }
    
    public void setMaxValue(final float maximum) {
        this.maximum = maximum;
    }
    
    public float getIncrement() {
        return this.increment;
    }
    
    public void setIncrement(final float increment) {
        this.increment = increment;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public float getMaxValue() {
        return this.maximum;
    }
    
    public float getMinValue() {
        return this.minimum;
    }
    
    public void setValueNumber(final float current) {
        this.current = current;
    }
    
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    
    public void setMinValue(final float minimum) {
        this.minimum = minimum;
    }
    
    public NumberSetting(final String s, final Module module, final String s2, final ArrayList list) {
        super(s, module, s2, list);
    }
    
    public enum NumberType
    {
        private static final NumberType[] $VALUES;
        
        SIZE("SIZE", 2, "Size");
        
        String name;
        
        DISTANCE("DISTANCE", 4, "Distance"), 
        MS("MS", 0, "Ms"), 
        DEFAULT("DEFAULT", 5, ""), 
        APS("APS", 1, "Aps"), 
        PERCENTAGE("PERCENTAGE", 3, "Percentage");
        
        public String getName() {
            return this.name;
        }
        
        private NumberType(final String name, final int ordinal, final String name2) {
            this.name = name2;
        }
        
        static {
            $VALUES = new NumberType[] { NumberType.MS, NumberType.APS, NumberType.SIZE, NumberType.PERCENTAGE, NumberType.DISTANCE, NumberType.DEFAULT };
        }
    }
}
