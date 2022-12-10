//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.buttons.setting.settings;

import new_gui.buttons.setting.*;
import org.lwjgl.input.*;
import ru.internali.module.*;
import java.io.*;

public class KeyBind extends CSSetting
{
    private boolean binding;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        String value = String.valueOf(new StringBuilder().append("KeyBind: ").append(Keyboard.getKeyName(this.mod.getKey())));
        if (this.binding) {
            value = "Listen... ";
            this.fr.drawString(value, this.x, this.y, Integer.MAX_VALUE);
        }
        this.fr.drawString(value, this.x, this.y, Integer.MAX_VALUE);
        this.fr.getStringWidth(value);
        if (this.binding && Keyboard.getEventKey() != 0 && Keyboard.getEventKeyState()) {
            this.mod.setKey(Keyboard.getEventKey());
            this.binding = false;
        }
        super.drawScreen(n, n2, n3);
    }
    
    public void setBinding(final boolean binding) {
        this.binding = binding;
    }
    
    public KeyBind(final int n, final int n2, final int n3, final int n4, final Module module) {
        super(n, n2, n3, n4, module);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        if (this.isHovered(n, n2) && n3 == 0) {
            this.setBinding(true);
        }
        super.mouseClicked(n, n2, n3);
    }
    
    private boolean isHovered(final int n, final int n2) {
        final int getStringWidth = this.fr.getStringWidth(this.displayString);
        final boolean b = n > this.x + 15 && n < this.x + getStringWidth + 35;
        final boolean b2 = n2 > this.y && n2 < this.y + 10;
        return b && b2;
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
    }
}
