//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package clickgui.comp;

import clickgui.*;
import ru.internali.module.*;
import clickgui.setting.*;
import java.awt.*;
import ru.internali.utils.*;
import net.minecraft.client.*;

public class CheckBox extends Comp
{
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isInside(n, n2, this.parent.posX + this.x - 70.0, this.parent.posY + this.y, this.parent.posX + this.x + 10.0 - 70.0, this.parent.posY + this.y + 8.5) && n3 == 0) {
            this.setting.setValBoolean(!this.setting.getValBoolean());
        }
    }
    
    public CheckBox(final double x, final double y, final Clickgui parent, final Module module, final Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }
    
    @Override
    public void drawScreen(final int n, final int n2) {
        super.drawScreen(n, n2);
        RenderUtil.drawRect(this.parent.posX + this.x - 70.0, this.parent.posY + this.y, this.parent.posX + this.x + 10.0 - 70.0, this.parent.posY + this.y + 8.5, this.setting.getValBoolean() ? new Color(10, 179, 230).getRGB() : new Color(61, 61, 61).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawString(this.setting.getName(), (int)(this.parent.posX + this.x - 55.0), (int)(this.parent.posY + this.y + 0.5), new Color(200, 200, 200).getRGB());
    }
}
