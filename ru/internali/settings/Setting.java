//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.settings;

import ru.internali.module.*;
import java.util.*;

public class Setting extends clickgui.setting.Setting
{
    private Module parent;
    private boolean valueBoolean;
    private double min;
    Module module;
    private String name;
    private float valueNumeric;
    private String sval;
    private String[] values;
    private float maxValue;
    private float minValue;
    private String id;
    private boolean bval;
    private boolean onlyint;
    private ArrayList options;
    private double max;
    private String valueString;
    private double dval;
    String descriptions;
    private String mode;
    
    public void setValString(final String sval) {
        this.sval = sval;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public float getValueNumeric() {
        return this.valueNumeric;
    }
    
    public void setValueBoolean(final boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }
    
    public void setValueNumeric(final float valueNumeric) {
        this.valueNumeric = valueNumeric;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public float getMaxValue() {
        return this.maxValue;
    }
    
    public void setValueString(final String valueString) {
        this.valueString = valueString;
    }
    
    public Setting(final String s, final String s2) {
        this.onlyint = false;
    }
    
    public Setting(final String name, final Module parent, final String sval, final ArrayList options) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }
    
    public String getDescriptions() {
        return this.descriptions;
    }
    
    public Setting(final String name, final Module parent, final boolean bval) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getValueString() {
        return this.valueString;
    }
    
    public void setValDouble(final double dval) {
        this.dval = dval;
    }
    
    public float getValFloat() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return (float)this.dval;
    }
    
    public boolean getValBoolean() {
        return this.bval;
    }
    
    public Setting(final String name, final Module parent, final double dval, final double min, final double max, final boolean onlyint) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }
    
    public double getMin() {
        return this.min;
    }
    
    public Setting(final String s, final Module module, final double n, final float n2, final float n3) {
        this.onlyint = false;
    }
    
    public Module getModule() {
        return this.module;
    }
    
    public boolean getValueBoolean() {
        return this.valueBoolean;
    }
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setValBoolean(final boolean bval) {
        this.bval = bval;
    }
    
    public ArrayList getOptions() {
        return this.options;
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public float getMinValue() {
        return this.minValue;
    }
    
    public void setValFloat(final float n) {
        this.dval = n;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
}
