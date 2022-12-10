//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import ru.internali.*;
import ru.internali.utils.*;

public class partical
{
    public int height;
    float y;
    float speed;
    float x;
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
    
    public void render() {
        RenderUtil.drawCircle(this.x, this.y, this.y / this.height * 3.0f, CatClient.getClientColor().getRGB());
    }
    
    public float getY() {
        return this.y;
    }
    
    public partical(final float x, final float y, final float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    
    public float getX() {
        return this.x;
    }
}
