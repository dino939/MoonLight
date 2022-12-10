//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.clickgui;

import net.minecraft.client.gui.*;
import ru.internali.module.*;
import java.util.*;
import java.io.*;
import org.lwjgl.input.*;
import java.awt.*;
import ru.internali.*;
import org.lwjgl.opengl.*;
import ru.internali.utils.*;

public class ClickGuiScreen extends GuiScreen
{
    int posX;
    int dragY;
    int HEIGHT;
    float modScroll;
    public Mod currentMod;
    ArrayList panels;
    int posY;
    public Panel currentPanel;
    int dragX;
    boolean draging;
    int WIGHT;
    
    public void initGui() {
        super.initGui();
    }
    
    public ClickGuiScreen() {
        this.modScroll = 0.0f;
        this.panels = new ArrayList();
        this.WIGHT = 300;
        this.HEIGHT = 200;
        this.draging = false;
        final Category[] values = Category.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            this.panels.add(new Panel(values[i]));
        }
    }
    
    public boolean ishover(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n3 && n6 >= n2 && n6 <= n4;
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        if (new Component().ishover((float)this.posX, (float)this.posY, (float)(this.posX + this.WIGHT), (float)(this.posY + 6), n, n2) && n3 == 0) {
            this.draging = true;
            this.dragX = n - this.posX;
            this.dragY = n2 - this.posY;
        }
        int n4 = 3;
        for (final Panel panel : this.panels) {
            panel.mouseClicked(this.posX + n4, this.posY + 7, n, n2, n3);
            n4 += (int)(panel.getWidth() + 5.0f);
        }
        if (this.currentPanel != null) {
            int n5 = (int)(25.0f + this.modScroll);
            for (final Mod mod : this.currentPanel.mods) {
                mod.mouseClicked(this.posX + 3, this.posY + n5, n, n2, n3);
                n5 += (int)(mod.getHeight() + 3.0f);
            }
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (this.draging) {
            if (!Mouse.isButtonDown(0)) {
                this.draging = false;
            }
            this.posX = n - this.dragX;
            this.posY = n2 - this.dragY;
        }
        RenderUtil.drawSmoothRect((float)this.posX, (float)this.posY, (float)(this.posX + this.WIGHT), (float)(this.posY + this.HEIGHT), new Color(30, 30, 30).getRGB());
        RenderUtil.drawSmoothRect((float)this.posX, (float)this.posY, (float)(this.posX + this.WIGHT), (float)(this.posY + 2), CatClient.getColor());
        int wight = 3;
        for (final Panel panel : this.panels) {
            panel.drawScreen(this.posX + wight, this.posY + 7, n, n2, n3);
            wight += (int)(panel.getWidth() + 5.0f);
        }
        RenderUtil.drawSmoothRect((float)this.posX, (float)(this.posY + 25), (float)(this.posX + 80), (float)(this.posY + this.HEIGHT), new Color(35, 35, 35).getRGB());
        GL11.glEnable(3089);
        RenderUtils.scissorRect((float)this.posX, (float)(this.posY + 25), (float)(this.posX + 80), this.posY + this.HEIGHT);
        this.WIGHT = wight;
        for (final Panel panel2 : this.panels) {
            if (panel2 != this.currentPanel) {
                final Iterator iterator3 = panel2.mods.iterator();
                while (iterator3.hasNext()) {
                    iterator3.next().reset();
                }
            }
        }
        if (this.ishover((float)this.posX, (float)this.posY, (float)(this.posX + this.WIGHT - 80), (float)(this.posY + this.HEIGHT), n, n2)) {
            final int dWheel = Mouse.getDWheel();
            if (dWheel > 0) {
                this.modScroll += 3.0f;
            }
            else if (dWheel < 0) {
                this.modScroll -= 3.0f;
            }
        }
        if (this.currentPanel != null) {
            this.currentPanel.mods.get(0).anim = MathUtils.lerp(this.currentPanel.mods.get(0).anim, 1.0f, 0.3f);
            for (int i = 0; i < this.currentPanel.mods.size() - 1; ++i) {
                if (((Mod)this.currentPanel.mods.get(i)).anim > 0.95) {
                    ((Mod)this.currentPanel.mods.get(i + 1)).anim = MathUtils.lerp(((Mod)this.currentPanel.mods.get(i + 1)).anim, 1.0f, 0.3f);
                }
            }
            int n4 = (int)(25.0f + this.modScroll);
            for (final Mod mod : this.currentPanel.mods) {
                mod.drawScreen(this.posX + 3, this.posY + n4, n, n2, n3);
                n4 += (int)(mod.getHeight() + 3.0f);
            }
        }
        GL11.glDisable(3089);
    }
}
