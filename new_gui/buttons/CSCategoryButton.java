//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.buttons;

import ru.internali.*;
import ru.internali.module.*;
import java.util.*;
import java.io.*;

public class CSCategoryButton extends CSButton
{
    public CSModButton currentMod;
    public ArrayList buttons;
    public Category category;
    public static boolean binding;
    
    public void initButton() {
        this.buttons.clear();
        final int n = this.x + 65;
        int n2 = 70;
        int index = 0;
        while (true) {
            final int n3 = index;
            final ModuleManager moduleManager = CatClient.instance.moduleManager;
            if (n3 >= ModuleManager.modules.size()) {
                break;
            }
            final ModuleManager moduleManager2 = CatClient.instance.moduleManager;
            final Module module = ModuleManager.modules.get(index);
            if (module.getCategory() == this.category) {
                this.buttons.add(new CSModButton(n, n2, this.fr.getStringWidth(module.getName()), this.fr.FONT_HEIGHT, -1, module.getName(), module));
                n2 += 10;
            }
            ++index;
        }
        super.initButton();
    }
    
    public boolean isHovered(final int n, final int n2) {
        final boolean b = n > this.x && n < this.x + this.width;
        final boolean b2 = n2 > this.y && n2 < this.y + this.height;
        return b && b2;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        for (final CSModButton currentMod : this.buttons) {
            currentMod.mouseClicked(n, n2, n3);
            if (n3 == 1 && currentMod.isHovered(n, n2)) {
                this.currentMod = currentMod;
            }
            if (n3 == 2 && currentMod.isHovered(n, n2)) {
                this.setBinding(true);
            }
        }
        super.mouseClicked(n, n2, n3);
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        int n4;
        if (this.isHovered(n, n2) || CatClient.instance.csgui.currentCategory == this) {
            final CatClient instance = CatClient.instance;
            n4 = CatClient.getClientColor().getRGB();
        }
        else {
            n4 = this.color;
        }
        this.fr.drawString(this.displayString, this.x, this.y, n4);
        for (final CSModButton csModButton : this.buttons) {
            if (CatClient.instance.csgui.currentCategory == this) {
                csModButton.drawScreen(n, n2, n3);
            }
        }
        super.drawScreen(n, n2, n3);
    }
    
    public void setBinding(final boolean binding) {
        CSCategoryButton.binding = binding;
    }
    
    public CSCategoryButton(final int n, final int n2, final int n3, final int n4, final int n5, final String s, final Category category) {
        super(n, n2, n3, n4, n5, s);
        this.buttons = new ArrayList();
        this.category = category;
    }
    
    private boolean isCurrentPanel() {
        return CatClient.instance.csgui.currentCategory == this;
    }
    
    public void onKeyPress(final int n, final int n2) {
    }
}
