//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import ru.internali.utils.animation.*;

public final class Translate
{
    private double y;
    private double x;
    
    public final void interpolate(final double n, final double n2, final double n3) {
        this.x = AnimationUtil.animate(n, this.x, n3);
        this.y = AnimationUtil.animate(n2, this.y, n3);
    }
    
    public void animate(final double n, final double n2) {
        this.x = AnimationUtil.animate(this.x, n, 1.0);
        this.y = AnimationUtil.animate(this.y, n2, 1.0);
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public Translate(final float n, final float n2) {
        this.x = n;
        this.y = n2;
    }
}
