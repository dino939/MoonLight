//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package click.component;

import ru.internali.*;
import ru.internali.module.*;
import click.component.components.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public class Frame
{
    public int dragY;
    public int dragX;
    private boolean isDragging;
    public ArrayList components;
    private boolean open;
    public Category category;
    private int x;
    private int barHeight;
    private int width;
    private int y;
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public Frame(final Category category) {
        this.components = new ArrayList();
        this.category = category;
        this.width = 95;
        this.x = 5;
        this.y = 5;
        this.barHeight = 15;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int barHeight = this.barHeight;
        final Iterator<Module> iterator = CatClient.instance.moduleManager.getModulesInCategory(this.category).iterator();
        while (iterator.hasNext()) {
            this.components.add(new Button((Module)iterator.next(), this, barHeight));
            barHeight += 12;
        }
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void renderFrame(final FontRenderer fontRenderer) {
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.barHeight, Color.DARK_GRAY.getRGB());
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        fontRenderer.drawStringWithShadow(this.category.name(), (float)((this.x + 2) * 2 + 5), (this.y + 2.5f) * 2.0f + 5.0f, 15252223);
        fontRenderer.drawStringWithShadow(this.open ? "-" : "+", (float)((this.x + this.width - 10) * 2 + 5), (this.y + 2.5f) * 2.0f + 5.0f, -1);
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            Gui.drawRect(this.x, this.y + this.barHeight, this.x + 1, this.y + this.barHeight + 12 * this.components.size(), new Color(200, 0, 0, 150).getRGB());
            Gui.drawRect(this.x, this.y + this.barHeight + 12 * this.components.size(), this.x + this.width, this.y + this.barHeight + 12 * this.components.size() + 1, new Color(200, 0, 0, 150).getRGB());
            Gui.drawRect(this.x + this.width, this.y + this.barHeight, this.x + this.width - 1, this.y + this.barHeight + 12 * this.components.size(), new Color(200, 0, 0, 150).getRGB());
            final Iterator<Component> iterator = this.components.iterator();
            while (iterator.hasNext()) {
                iterator.next().renderComponent();
            }
        }
    }
    
    public void updatePosition(final int n, final int n2) {
        if (this.isDragging) {
            this.setX(n - this.dragX);
            this.setY(n2 - this.dragY);
        }
    }
    
    public int getY() {
        return this.y;
    }
    
    public void refresh() {
        int barHeight = this.barHeight;
        for (final Component component : this.components) {
            component.setOff(barHeight);
            barHeight += component.getHeight();
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public ArrayList getComponents() {
        return this.components;
    }
    
    public int getX() {
        return this.x;
    }
    
    public boolean isWithinHeader(final int n, final int n2) {
        return n >= this.x && n <= this.x + this.width && n2 >= this.y && n2 <= this.y + this.barHeight;
    }
    
    public void setDrag(final boolean isDragging) {
        this.isDragging = isDragging;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
}
