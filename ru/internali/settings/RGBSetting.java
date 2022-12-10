//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.settings;

public class RGBSetting extends Setting
{
    private int blue;
    private int red;
    private int green;
    
    public int getBlue() {
        return this.blue;
    }
    
    public void setGreen(final int green) {
        this.green = green;
    }
    
    public int getRed() {
        return this.red;
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public RGBSetting(final String s, final String s2, final int red, final int green, final int blue) {
        super(s, s2);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public void setRed(final int red) {
        this.red = red;
    }
}
