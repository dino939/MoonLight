//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.HudEditor;

import ru.internali.module.*;
import java.awt.*;
import ru.internali.utils.*;
import org.lwjgl.input.*;
import java.io.*;

public class HudComponent
{
    int dragX;
    boolean draging;
    Module module;
    int dragY;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        RenderUtils.drawRect((float)this.module.getPosX(), (float)this.module.getPosY(), (float)(this.module.getPosX() + this.module.getSizeX()), (float)(this.module.getPosY() + this.module.getSizeY()), new Color(30, 30, 30, 200).getRGB());
        MinecraftHelper.mc.fontRenderer.drawString(this.module.getName(), this.module.getPosX() + this.module.getSizeX() / 2, this.module.getPosY() + 2, -1);
        if (this.draging) {
            this.module.setPosX(n - this.dragX);
            this.module.setPosY(n2 - this.dragY);
        }
        if (!Mouse.isButtonDown(0)) {
            this.draging = false;
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        if (this.ishover((float)this.module.getPosX(), (float)this.module.getPosY(), (float)(this.module.getPosX() + this.module.getSizeX()), (float)(this.module.getPosY() + this.module.getSizeY()), n, n2) && n3 == 0) {
            this.draging = !this.draging;
            this.dragX = n - this.module.getPosX();
            this.dragY = n2 - this.module.getPosY();
        }
    }
    
    public HudComponent(final Module module) {
        this.draging = false;
        this.dragX = 0;
        this.dragY = 0;
        this.module = module;
    }
    
    public boolean ishover(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n3 && n6 >= n2 && n6 <= n4;
    }
    
    public Module getModule() {
        return this.module;
    }
}
