//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.clickgui;

import ru.internali.*;
import ru.internali.module.*;
import java.util.*;
import java.io.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import ru.internali.utils.*;

public class Panel extends Component
{
    public Category category;
    public ArrayList mods;
    
    public float getHeight() {
        return 15.0f;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public Panel(final Category category) {
        this.mods = new ArrayList();
        this.category = category;
        final Iterator<Module> iterator = CatClient.instance.moduleManager.getModules(category).iterator();
        while (iterator.hasNext()) {
            this.mods.add(new Mod((Module)iterator.next()));
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)n2, n + this.getWidth(), n2 + this.getHeight(), n3, n4)) {
            CatClient.instance.clickGui.currentPanel = this;
            CatClient.instance.clickGui.modScroll = 0.0f;
        }
    }
    
    public float getWidth() {
        return (float)(this.fr.getStringWidth(this.category.name()) + 2);
    }
    
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        GL11.glPushMatrix();
        if (CatClient.instance.clickGui.currentPanel == this) {
            this.anim = MathUtils.lerp(this.anim, 1.1f, 0.1f);
        }
        else {
            this.anim = MathUtils.lerp(this.anim, 1.0f, 0.1f);
        }
        RenderUtils.customScaledObject2D((float)n, (float)n2, this.getWidth(), this.getHeight(), this.anim);
        RenderUtil.drawSmoothRect((float)n, (float)n2, n + this.getWidth(), n2 + this.getHeight(), new Color(45, 45, 45).getRGB());
        this.fr.drawString(this.category.name(), n + 1, (int)(n2 + this.getHeight() / 2.0f - this.fr.FONT_HEIGHT / 2), (CatClient.instance.clickGui.currentPanel == this) ? -1 : new Color(200, 200, 200).getRGB());
        GL11.glPopMatrix();
    }
    
    public void initGui() {
        super.initGui();
    }
    
    public void reset() {
        super.reset();
    }
}
