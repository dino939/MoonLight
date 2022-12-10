//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.setting;

import java.util.*;
import ru.internali.module.*;

public class Setting
{
    private boolean bval;
    private double min;
    private boolean onlyint;
    private String mode;
    private ArrayList options;
    private String name;
    public double standarddval;
    private double max;
    private String sval;
    public String standardsval;
    public String displayName;
    private double dval;
    private Module parent;
    public boolean standardbval;
    
    public void setValDouble(final double dval) {
        this.dval = dval;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public Setting(final String name, final String displayName, final Module parent, final String s, final ArrayList options) {
        this.onlyint = false;
        this.name = name;
        this.displayName = displayName;
        this.parent = parent;
        this.sval = s;
        this.standardsval = s;
        this.options = options;
        this.mode = "Combo";
    }
    
    public boolean getValBoolean() {
        return this.bval;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public void setValString(final String sval) {
        this.sval = sval;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public Setting(final String name, final String displayName, final Module parent, final double n, final double min, final double max, final boolean onlyint) {
        this.onlyint = false;
        this.name = name;
        this.displayName = displayName;
        this.parent = parent;
        this.dval = n;
        this.standarddval = n;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public double getMin() {
        return this.min;
    }
    
    public ArrayList getOptions() {
        return this.options;
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
    
    public Setting(final String name, final String displayName, final Module parent, final boolean b) {
        this.onlyint = false;
        this.name = name;
        this.displayName = displayName;
        this.standardbval = b;
        this.parent = parent;
        this.bval = b;
        this.mode = "Check";
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setValBoolean(final boolean bval) {
        this.bval = bval;
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
}
