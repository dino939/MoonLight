//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package click;

import net.minecraft.client.gui.*;
import click.component.*;
import java.util.*;
import java.io.*;
import ru.internali.*;
import ru.internali.module.*;

public class ClickGui extends GuiScreen
{
    public int color;
    public int height;
    public int y;
    public int width;
    public int x;
    public static ArrayList frames;
    
    public boolean doesGuiPauseGame() {
        return true;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        for (final Frame frame : ClickGui.frames) {
            frame.renderFrame(this.mc.fontRenderer);
            frame.updatePosition(n, n2);
            final Iterator iterator2 = frame.getComponents().iterator();
            while (iterator2.hasNext()) {
                iterator2.next().updateComponent(n, n2);
            }
        }
    }
    
    public void initGui() {
    }
    
    protected void keyTyped(final char c, final int n) {
        for (final Frame frame : ClickGui.frames) {
            if (frame.isOpen() && n != 1 && !frame.getComponents().isEmpty()) {
                final Iterator iterator2 = frame.getComponents().iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().keyTyped(c, n);
                }
            }
        }
        if (n == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        for (final Frame frame : ClickGui.frames) {
            if (frame.isWithinHeader(n, n2) && n3 == 0) {
                frame.setDrag(true);
                frame.dragX = n - frame.getX();
                frame.dragY = n2 - frame.getY();
            }
            if (frame.isWithinHeader(n, n2) && n3 == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                final Iterator iterator2 = frame.getComponents().iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().mouseClicked(n, n2, n3);
                }
            }
        }
    }
    
    protected void mouseReleased(final int n, final int n2, final int n3) {
        final Iterator<Frame> iterator = ClickGui.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().setDrag(false);
        }
        for (final Frame frame : ClickGui.frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                final Iterator iterator3 = frame.getComponents().iterator();
                while (iterator3.hasNext()) {
                    iterator3.next().mouseReleased(n, n2, n3);
                }
            }
        }
    }
    
    public ClickGui() {
        this.color = CatClient.getClientColor().getRGB();
        ClickGui.frames = new ArrayList();
        int x = 10;
        final Category[] values = Category.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final Frame e = new Frame(values[i]);
            e.setX(x);
            ClickGui.frames.add(e);
            x += e.getWidth() + 2;
        }
    }
}
