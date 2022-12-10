//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.clickgui;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import ru.internali.settings.*;
import ru.internali.module.*;
import java.io.*;

public class Component
{
    public Minecraft mc;
    public int width;
    float anim;
    public String displayString;
    protected FontRenderer fr;
    public int height;
    public int x;
    public Setting set;
    public int y;
    public Module mod;
    
    public void initGui() {
    }
    
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
    }
    
    public Component() {
        this.anim = 0.0f;
        this.mc = Minecraft.getMinecraft();
        this.fr = Minecraft.getMinecraft().fontRenderer;
    }
    
    public float getWidth() {
        return 100.0f;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
    }
    
    public void reset() {
        this.anim = 0.0f;
    }
    
    public boolean ishover(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n3 && n6 >= n2 && n6 <= n4;
    }
    
    public float getHeight() {
        return 10.0f;
    }
}
