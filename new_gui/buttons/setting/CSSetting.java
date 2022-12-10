//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.buttons.setting;

import net.minecraft.client.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraft.client.gui.*;
import java.io.*;

public class CSSetting
{
    public Minecraft mc;
    public int height;
    public Module mod;
    public String displayString;
    public Setting set;
    public int y;
    public int width;
    public int x;
    public FontRenderer fr;
    
    public void mouseClicked(final int n, final int n2, final int n3) throws IOException {
    }
    
    public CSSetting(final int x, final int y, final int width, final int height, final Module mod) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mod = mod;
        this.displayString = mod.getName();
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
    }
    
    public void onKeyPress(final int n, final int n2) {
    }
    
    public CSSetting(final int x, final int y, final int width, final int height, final Setting set) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.set = set;
        this.displayString = set.getName();
    }
    
    public void initButton() {
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
    }
}
