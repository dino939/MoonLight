//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package click.component.components;

import ru.internali.module.*;
import click.component.*;
import java.util.*;
import ru.internali.*;
import ru.internali.settings.*;
import click.component.components.sub.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import ru.internali.utils.*;

public class Button extends Component
{
    private int height;
    public Module mod;
    public int offset;
    private boolean isHovered;
    private ArrayList subcomponents;
    public Frame parent;
    public boolean open;
    
    public void updateComponent(final int n, final int n2) {
        this.isHovered = this.isMouseOnButton(n, n2);
        if (!this.subcomponents.isEmpty()) {
            final Iterator<Component> iterator = this.subcomponents.iterator();
            while (iterator.hasNext()) {
                iterator.next().updateComponent(n, n2);
            }
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 0) {
            this.mod.toggle();
        }
        if (this.isMouseOnButton(n, n2) && n3 == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        final Iterator<Component> iterator = this.subcomponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseClicked(n, n2, n3);
        }
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
        int off = this.offset + 12;
        final Iterator<Component> iterator = (Iterator<Component>)this.subcomponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().setOff(off);
            off += 12;
        }
    }
    
    public int getHeight() {
        if (this.open) {
            return 12 * (this.subcomponents.size() + 1);
        }
        return 12;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        final Iterator<Component> iterator = this.subcomponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseReleased(n, n2, n3);
        }
    }
    
    public boolean isMouseOnButton(final int n, final int n2) {
        return n > this.parent.getX() && n < this.parent.getX() + this.parent.getWidth() && n2 > this.parent.getY() + this.offset && n2 < this.parent.getY() + 12 + this.offset;
    }
    
    public Button(final Module mod, final Frame parent, final int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList();
        this.open = false;
        this.height = 12;
        int n = offset + 12;
        if (CatClient.instance.settingsManager.getSettingsByMod(mod) != null) {
            for (final Setting setting : CatClient.instance.settingsManager.getSettingsByMod(mod)) {
                if (setting.isCombo()) {
                    this.subcomponents.add(new ModeButton(setting, this, mod, n));
                    n += 12;
                }
                if (setting.isSlider()) {
                    this.subcomponents.add(new Slider(setting, this, n));
                    n += 12;
                }
                if (setting.isCheck()) {
                    this.subcomponents.add(new Checkbox(setting, this, n));
                    n += 12;
                }
            }
        }
        this.subcomponents.add(new Keybind(this, n));
        this.subcomponents.add(new VisibleButton(this, mod, n));
    }
    
    public void renderComponent() {
        Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 16 + this.offset, this.isHovered ? (this.mod.isToggled() ? new Color(-6881025).darker().getRGB() : -6881025) : (this.mod.isToggled() ? new Color(14, 14, 14).getRGB() : -15658735));
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        MinecraftHelper.mc.fontRenderer.drawStringWithShadow(this.mod.getName(), (float)((this.parent.getX() + 2) * 2), (float)((this.parent.getY() + this.offset + 2) * 2 + 4), this.mod.isToggled() ? 12411647 : -1);
        if (this.subcomponents.size() > 3) {
            MinecraftHelper.mc.fontRenderer.drawStringWithShadow(this.open ? "<" : ">", (float)((this.parent.getX() + this.parent.getWidth() - 10) * 2), (float)((this.parent.getY() + this.offset + 2) * 2 + 4), -1);
        }
        GL11.glPopMatrix();
        if (this.open && !this.subcomponents.isEmpty()) {
            final Iterator<Component> iterator = this.subcomponents.iterator();
            while (iterator.hasNext()) {
                iterator.next().renderComponent();
            }
            Gui.drawRect(this.parent.getX() + 2, this.parent.getY() + this.offset + 12, this.parent.getX() + 3, this.parent.getY() + this.offset + (this.subcomponents.size() + 1) * 12, Color.white.getRGB());
        }
    }
    
    public void keyTyped(final char c, final int n) {
        final Iterator<Component> iterator = this.subcomponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().keyTyped(c, n);
        }
    }
}
