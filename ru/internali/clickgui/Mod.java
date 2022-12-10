//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.clickgui;

import ru.internali.module.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import ru.internali.utils.*;
import java.io.*;

public class Mod extends Component
{
    Module module;
    
    public void reset() {
        super.reset();
    }
    
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D((float)n, (float)n2, this.getWidth(), this.getHeight(), this.anim, this.anim);
        RenderUtil.drawSmoothRect((float)n, (float)n2, n + this.getWidth(), n2 + this.getHeight(), new Color(45, 45, 45).getRGB());
        this.fr.drawString(this.module.getName(), n + 1, (int)(n2 + this.getHeight() / 2.0f - this.fr.FONT_HEIGHT / 2), this.module.isToggled() ? -1 : new Color(200, 200, 200).getRGB());
        GL11.glPopMatrix();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)n2, n + this.getWidth(), n2 + this.getHeight(), n3, n4)) {
            switch (n5) {
                case 0: {
                    this.module.toggle();
                }
            }
        }
    }
    
    public Mod(final Module module) {
        this.module = module;
    }
    
    public float getHeight() {
        return 15.0f;
    }
    
    public float getWidth() {
        return (float)(this.fr.getStringWidth(this.module.getName()) + 2);
    }
}
