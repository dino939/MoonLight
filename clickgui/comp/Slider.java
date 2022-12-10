//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package clickgui.comp;

import java.math.*;
import java.awt.*;
import ru.internali.utils.*;
import net.minecraft.client.*;
import clickgui.*;
import ru.internali.module.*;
import clickgui.setting.*;

public class Slider extends Comp
{
    private double renderWidth;
    private double renderWidth2;
    private boolean dragging;
    
    private double roundToPlace(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isInside(n, n2, this.parent.posX + this.x - 70.0, this.parent.posY + this.y + 10.0, this.parent.posX + this.x - 70.0 + this.renderWidth2, this.parent.posY + this.y + 16.0) && n3 == 0) {
            this.dragging = true;
        }
    }
    
    public void drawScreen(final int n, final int n2) {
        super.drawScreen(n, n2);
        final double min = this.setting.getMin();
        final double max = this.setting.getMax();
        final double a = 90.0;
        this.renderWidth = a * (this.setting.getValDouble() - min) / (max - min);
        this.renderWidth2 = a * (this.setting.getMax() - min) / (max - min);
        final double min2 = Math.min(a, Math.max(0.0, n - (this.parent.posX + this.x - 70.0)));
        if (this.dragging) {
            if (min2 == 0.0) {
                this.setting.setValDouble(this.setting.getMin());
            }
            else {
                this.setting.setValDouble(this.roundToPlace(min2 / a * (max - min) + min, 1));
            }
        }
        RenderUtil.drawRect(this.parent.posX + this.x - 70.0, this.parent.posY + this.y + 10.0, this.parent.posX + this.x - 70.0 + this.renderWidth2, this.parent.posY + this.y + 16.0, new Color(10, 131, 230).darker().getRGB());
        RenderUtil.drawRect(this.parent.posX + this.x - 70.0, this.parent.posY + this.y + 10.0, this.parent.posX + this.x - 70.0 + this.renderWidth, this.parent.posY + this.y + 16.0, new Color(10, 164, 230).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(new StringBuilder().append(this.setting.getName()).append(": ").append(this.setting.getValDouble())), (int)(this.parent.posX + this.x - 70.0), (int)(this.parent.posY + this.y), -1);
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        super.mouseReleased(n, n2, n3);
        this.dragging = false;
    }
    
    public Slider(final double x, final double y, final Clickgui parent, final Module module, final Setting setting) {
        this.dragging = false;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }
}
