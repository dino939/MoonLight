//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.buttons;

import net.minecraft.client.gui.*;
import ru.internali.module.*;
import ru.internali.*;
import new_gui.buttons.setting.*;
import java.util.*;
import net.minecraft.client.*;
import ru.internali.settings.*;
import new_gui.buttons.setting.settings.*;
import java.io.*;

public class CSModButton extends CSButton
{
    public static boolean binding;
    public ScaledResolution sr;
    public Module mod;
    public static boolean first;
    public static String old_name;
    public ArrayList settings;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        int n4 = this.isHovered(n, n2) ? CatClient.getClientColor().getRGB() : -1;
        if (this.mod.isToggled()) {
            n4 = CatClient.getClientColor().darker().getRGB();
        }
        if (this.isCurrentMod()) {
            n4 = CatClient.getClientColor().getRGB();
        }
        this.fr.drawString(this.displayString, this.x, this.y, n4);
        for (final CSSetting csSetting : this.settings) {
            if (this.isCurrentMod()) {
                csSetting.drawScreen(n, n2, n3);
            }
        }
        super.drawScreen(n, n2, n3);
    }
    
    private boolean isCurrentMod() {
        return CatClient.instance.csgui.currentCategory != null && CatClient.instance.csgui.currentCategory.currentMod != null && CatClient.instance.csgui.currentCategory.currentMod == this;
    }
    
    public CSModButton(final int n, final int n2, final int n3, final int n4, final int n5, final String s, final Module mod) {
        super(n, n2, n3, n4, n5, s);
        this.sr = new ScaledResolution(Minecraft.getMinecraft());
        this.settings = new ArrayList();
        this.mod = mod;
        this.initSettings();
    }
    
    public void initButton() {
        this.initSettings();
        super.initButton();
    }
    
    static {
        CSModButton.first = false;
    }
    
    private void initSettings() {
        int n = 70;
        int n2 = this.x + 100;
        for (int i = 0; i < CatClient.instance.settingsManager.getSettings().size(); ++i) {
            final Setting setting = CatClient.instance.settingsManager.getSettings().get(i);
            if (setting.getParentMod() == this.mod && n > 100 + this.sr.getScaledWidth() - 220) {
                n = 0;
                n2 += this.mc.fontRenderer.getStringWidth(setting.getName()) + 50;
            }
        }
        this.settings.add(new KeyBind(n2, n, 70, this.mc.fontRenderer.FONT_HEIGHT + 2, this.mod));
    }
    
    public void setBinding(final boolean binding) {
        CSModButton.binding = binding;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        if (this.isHovered(n, n2)) {
            if (n3 == 0 && this.isHovered(n, n2) && CatClient.instance.csgui.currentCategory != null && CatClient.instance.csgui.currentCategory.category == this.mod.getCategory()) {
                this.mod.toggle();
            }
            else if (n3 == 2 && this.isHovered(n, n2) && CatClient.instance.csgui.currentCategory != null && CatClient.instance.csgui.currentCategory.category == this.mod.getCategory()) {
                this.setBinding(true);
            }
            else if (n3 == 1) {}
        }
        for (final CSSetting csSetting : this.settings) {
            if (this.isCurrentMod()) {
                csSetting.mouseClicked(n, n2, n3);
            }
        }
        super.mouseClicked(n, n2, n3);
    }
    
    public boolean isHovered(final int n, final int n2) {
        final boolean b = n > this.x && n < this.x + this.width;
        final boolean b2 = n2 > this.y && n2 < this.y + this.height;
        return b && b2;
    }
}
