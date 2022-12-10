//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.xray;

public class XRayData
{
    private int blue;
    private int red;
    private int id;
    private int green;
    private int meta;
    
    public void setRed(final int red) {
        this.red = red;
    }
    
    public int getRed() {
        return this.red;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public int getMeta() {
        return this.meta;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setGreen(final int green) {
        this.green = green;
    }
    
    public int getBlue() {
        return this.blue;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
    }
    
    public XRayData(final int id, final int meta, final int red, final int green, final int blue) {
        this.id = id;
        this.meta = meta;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
