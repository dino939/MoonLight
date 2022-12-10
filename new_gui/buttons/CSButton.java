//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.buttons;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.io.*;

public abstract class CSButton extends Gui
{
    public int width;
    public int x;
    public int y;
    public int color;
    public String displayString;
    public Minecraft mc;
    public int height;
    public FontRenderer fr;
    
    public void initButton() {
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) throws IOException {
    }
    
    public CSButton(final int x, final int y, final int width, final int height, final int color, final String displayString) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.displayString = displayString;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
    }
    
    public void onKeyPress(final int n, final int n2) {
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
}
