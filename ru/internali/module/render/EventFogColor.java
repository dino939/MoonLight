//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import ru.internali.utils.*;

public class EventFogColor extends Event
{
    public float red;
    public int alpha;
    public float blue;
    public float green;
    
    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }
    
    public int getAlpha() {
        return this.alpha;
    }
    
    public float getBlue() {
        return this.blue;
    }
    
    public float getGreen() {
        return this.green;
    }
    
    public static void setGreen(float n) {
        n = n;
    }
    
    public static void setRed(float n) {
        n = n;
    }
    
    public float getRed() {
        return this.red;
    }
    
    public static void setBlue(float n) {
        n = n;
    }
    
    public EventFogColor(final float red, final float green, final float blue, final int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
}
