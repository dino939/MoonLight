//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import ru.internali.settings.*;

public class FloatSetting extends Setting
{
    private float value;
    private final float min;
    private final float max;
    private final float step;
    
    public FloatSetting(final String s, final String s2, final float n, final float n2, final float n3) {
        this(s, s2, n, n2, n3, 0.1f);
    }
    
    public FloatSetting(final String s, final String s2, final float n) {
        this(s, s2, n, 0.0f, 10.0f, 0.1f);
    }
    
    public float getValue() {
        return this.value;
    }
    
    public float getStep() {
        return this.step;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public FloatSetting(final String s, final String s2, final float value, final float min, final float max, final float step) {
        super(s, s2);
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public boolean setValueWithStep(final float n) {
        return this.setValue(this.step * Math.round(n / this.step));
    }
    
    public boolean setValue(final float value) {
        if (value >= this.min && value <= this.max) {
            this.value = value;
            return true;
        }
        return false;
    }
}
