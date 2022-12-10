//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui;

import new_gui.buttons.*;
import ru.internali.*;
import java.awt.*;
import ru.internali.module.HUD.*;
import new_gui.util.*;
import ru.internali.utils.*;
import net.minecraft.client.gui.*;
import java.util.*;
import java.io.*;
import net.minecraft.client.*;
import ru.internali.module.*;
import ru.internali.utils.friend.*;
import org.lwjgl.input.*;

public class CSGOGui extends GuiScreen
{
    public CSCategoryButton currentCategory;
    public int x;
    public int height;
    private float curAlpha;
    private float anim;
    public int width;
    public int y;
    public ScaledResolution sr;
    public ArrayList buttons;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        final float n4 = 150.0f;
        final int n5 = (int)(n4 / 150.0f);
        if (this.curAlpha < n4 - n5) {
            this.curAlpha += n5;
        }
        else if (this.curAlpha > n4 - n5 && this.curAlpha != n4) {
            this.curAlpha = (float)(int)n4;
        }
        else if (this.curAlpha != n4) {
            this.curAlpha = (float)(int)n4;
        }
        final Color color = new Color(CatClient.getClientColor().getRed(), CatClient.getClientColor().getGreen(), CatClient.getClientColor().getBlue(), (int)this.curAlpha);
        this.drawGradientRect(0, 0, this.getScaledRes().getScaledWidth(), this.getScaledRes().getScaledHeight(), new Color(0, 0, 0, 0).getRGB(), HUD.getRainbowChams(6000, -17));
        this.anim = MathUtils.lerp(this.anim, 1.0f, 0.1f);
        RenderHelper.customScaledObject2D((float)this.x, (float)this.y, (float)this.width, 15.0f, this.anim, 1.0f);
        RenderUtil.drawNewRect((double)this.x, (double)this.height, (double)this.width, this.height + 0.5, HUD.getRainbowChams(6000, -17));
        RenderUtil.drawNewRect((double)this.width, (double)(this.y - this.fontRenderer.FONT_HEIGHT * 2), this.width + 0.5, (double)this.height, CatClient.getClientColor().getRGB());
        Gui.drawRect(this.x, this.y - this.fontRenderer.FONT_HEIGHT * 2, this.width, this.height, new Color(0, 0, 0, 150).getRGB());
        final Iterator<CSCategoryButton> iterator = this.buttons.iterator();
        while (iterator.hasNext()) {
            iterator.next().drawScreen(n, n2, n3);
        }
        Gui.drawRect(this.x + 65, this.y, this.x + 67, this.height, CatClient.getClientColor().getRGB());
        Gui.drawRect(this.x + 165, this.y, this.x + 167, this.height, CatClient.getClientColor().getRGB());
        this.drawinfo(n, n2);
        super.drawScreen(n, n2, n3);
    }
    
    protected void keyTyped(final char c, final int n) throws IOException {
        final Iterator<CSCategoryButton> iterator = this.buttons.iterator();
        while (iterator.hasNext()) {
            iterator.next().keyTyped(c, n);
        }
        super.keyTyped(c, n);
    }
    
    public CSGOGui() {
        this.buttons = new ArrayList();
        this.sr = new ScaledResolution(Minecraft.getMinecraft());
        this.x = 100;
        this.y = 70;
        this.width = this.sr.getScaledWidth() - 140;
        this.height = this.sr.getScaledHeight() - 140;
        this.x = 100;
        this.y = 70;
        this.width = this.sr.getScaledWidth() - 140;
        this.height = this.sr.getScaledHeight() - 140;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    protected void mouseReleased(final int n, final int n2, final int n3) {
        final Iterator<CSCategoryButton> iterator = this.buttons.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseReleased(n, n2, n3);
        }
        super.mouseReleased(n, n2, n3);
    }
    
    public void initGui() {
        this.initButtons();
        this.anim = 0.0f;
        this.x = 100;
        this.y = 70;
        this.width = 355;
        this.height = 320;
        final Iterator<CSCategoryButton> iterator = this.buttons.iterator();
        while (iterator.hasNext()) {
            iterator.next().initButton();
        }
        super.initGui();
    }
    
    private void initButtons() {
        this.buttons.clear();
        final int n = 110;
        int n2 = 70;
        for (final Category category : Category.values()) {
            final String value = String.valueOf(new StringBuilder().append(category.name().substring(0, 1).toUpperCase()).append(category.name().substring(1, category.name().length()).toLowerCase()));
            this.buttons.add(new CSCategoryButton(n, n2, this.mc.fontRenderer.getStringWidth(value), this.mc.fontRenderer.FONT_HEIGHT, -1, value, category));
            n2 += 15;
        }
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        for (final CSCategoryButton currentCategory : this.buttons) {
            if (currentCategory.isHovered(n, n2) && n3 == 0) {
                this.currentCategory = currentCategory;
            }
            currentCategory.mouseClicked(n, n2, n3);
        }
        super.mouseClicked(n, n2, n3);
    }
    
    public void drawinfo(final int n, final int n2) {
        final int n3 = 600;
        final int n4 = 100;
        int n5 = 0;
        this.fontRenderer.drawStringWithShadow("Friends", (float)(n3 + this.fontRenderer.getStringWidth("Friends") / 2), (float)(n4 - this.fontRenderer.FONT_HEIGHT), CatClient.getClientColor().getRGB());
        RenderUtil.drawRect((double)n3, (double)n4, (double)(n3 + 100), (double)(n4 + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), new Color(50, 50, 50, 190).getRGB());
        RenderUtil.drawNewRect((double)(n3 - 1), (double)n4, (double)n3, (double)(n4 + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), CatClient.getClientColor().getRGB());
        RenderUtil.drawNewRect((double)(n3 + 100 - 1), (double)n4, (double)(n3 + 100), (double)(n4 + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), CatClient.getClientColor().getRGB());
        RenderUtil.drawGlow((double)n3, (double)(n4 - 1), (double)(n3 + 100), (double)n4, CatClient.getClientColor().getRGB(), 250.0);
        RenderUtil.drawGlow((double)n3, (double)(n4 + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT - 1), (double)(n3 + 100), (double)(n4 + FriendManager.FRIENDS.size() * this.fontRenderer.FONT_HEIGHT), CatClient.getClientColor().getRGB(), 250.0);
        for (final String s : FriendManager.friendsList) {
            final int n6 = n3;
            final int n7 = 100 + n3;
            final int n8 = n5 + n4;
            final int n9 = n5 + this.fontRenderer.FONT_HEIGHT + n4;
            if (n > n6 && n < n7 && n2 > n8 && n2 < n9) {
                this.fontRenderer.drawString(s, n3 + 50 - this.fontRenderer.getStringWidth(s) / 2, n5 + n4, CatClient.getClientColor().getRGB());
                if (Mouse.isButtonDown(1)) {
                    FriendManager.removeFriend(s);
                    return;
                }
            }
            else {
                this.fontRenderer.drawString(s, n3 + 50 - this.fontRenderer.getStringWidth(s) / 2, n5 + n4, Color.white.getRGB());
            }
            n5 += this.fontRenderer.FONT_HEIGHT;
        }
    }
    
    public ScaledResolution getScaledRes() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }
}
