//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package clickgui;

import ru.internali.module.*;
import ru.internali.*;
import clickgui.setting.*;
import clickgui.comp.*;
import java.io.*;
import java.awt.*;
import ru.internali.module.HUD.*;
import new_gui.util.*;
import ru.internali.module.render.*;
import net.minecraft.client.network.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import ru.internali.utils.*;
import net.minecraft.entity.*;
import ru.internali.utils.friend.*;
import org.lwjgl.input.*;

public class Clickgui extends GuiScreen
{
    private float curAlpha;
    public Category selectedCategory;
    public double width;
    public ArrayList comps;
    float anim;
    public int modeIndex;
    public double dragX;
    public double x;
    public double posX;
    public double posY;
    public double height;
    public double dragY;
    public boolean dragging;
    public double y;
    private boolean editing;
    private boolean binding;
    private Module selectedModule;
    
    public void setBinding(final boolean binding) {
        this.binding = binding;
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        if (this.isInside(n, n2, this.posX, this.posY - 10.0, this.width, this.posY) && n3 == 0) {
            this.dragging = true;
            this.dragX = n - this.posX;
            this.dragY = n2 - this.posY;
        }
        int n4 = 0;
        for (final Category selectedCategory : Category.values()) {
            if (this.isInside(n, n2, this.posX, this.posY + 1.0 + n4, this.posX + 60.0, this.posY + 15.0 + n4) && n3 == 0) {
                this.selectedCategory = selectedCategory;
            }
            n4 += 15;
        }
        int n5 = 0;
        for (final Module selectedModule : CatClient.instance.moduleManager.getModules(this.selectedCategory)) {
            if (this.isInside(n, n2, this.posX + 65.0, this.posY + 1.0 + n5, this.posX + 125.0, this.posY + 15.0 + n5)) {
                if (n3 == 0) {
                    selectedModule.toggle();
                }
                if (n3 == 1) {
                    int n6 = 3;
                    this.comps.clear();
                    if (CatClient.instance.settingsManager.getSettingsByMod(selectedModule) != null) {
                        for (final Setting setting : CatClient.instance.settingsManager.getSettingsByMod(selectedModule)) {
                            this.selectedModule = selectedModule;
                            if (setting.isCombo()) {
                                this.comps.add(new Combo(275.0, n6, this, this.selectedModule, setting));
                                n6 += 15;
                            }
                            if (setting.isCheck()) {
                                this.comps.add(new CheckBox(275.0, n6, this, this.selectedModule, setting));
                                n6 += 13;
                            }
                            if (setting.isSlider()) {
                                this.comps.add(new Slider(275.0, n6, this, this.selectedModule, setting));
                                n6 += 18;
                            }
                        }
                    }
                }
            }
            n5 += 15;
        }
        final Iterator<Comp> iterator3 = (Iterator<Comp>)this.comps.iterator();
        while (iterator3.hasNext()) {
            iterator3.next().mouseClicked(n, n2, n3);
        }
    }
    
    public boolean isInside(final int n, final int n2, final double n3, final double n4, final double n5, final double n6) {
        return n > n3 && n < n5 && n2 > n4 && n2 < n6;
    }
    
    public void initGui() {
        super.initGui();
        this.anim = 0.0f;
        this.dragging = false;
    }
    
    public void setEditing(final boolean editing) {
        this.editing = editing;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (this.dragging) {
            this.posX = n - this.dragX;
            this.posY = n2 - this.dragY;
        }
        this.width = this.posX + 456.0;
        this.height = this.posY + 380.0;
        final float n4 = 255.0f;
        final int n5 = (int)(n4 / 255.0f);
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
        this.anim = MathUtils.lerp(this.anim, 1.0f, 0.13f);
        RenderHelper.customScaledObject2D((float)this.x, (float)this.y, (float)this.width, 15.0f, this.anim, 1.0f);
        RenderUtil.drawRect(this.posX, this.posY - 6.0, this.width, this.posY, HUD.getRainbowChams(6000, -15));
        RenderUtil.drawRect(this.posX, this.posY, this.width, this.height, new Color(45, 45, 45, 249).getRGB());
        int n6 = 0;
        for (final Category category : Category.values()) {
            RenderUtil.drawRect(this.posX, this.posY + 1.0 + n6, this.posX + 60.0, this.posY + 15.0 + n6, category.equals((Object)this.selectedCategory) ? new Color(46, 85, 243, 156).getRGB() : new Color(28, 28, 28, 250).getRGB());
            this.fontRenderer.drawString(category.name(), (int)this.posX + 2, (int)(this.posY + 5.0) + n6, new Color(170, 170, 170).getRGB());
            n6 += 15;
        }
        int n7 = 0;
        for (final Module module : CatClient.instance.moduleManager.getModules(this.selectedCategory)) {
            RenderUtil.drawRect(this.posX + 65.0, this.posY + 1.0 + n7, this.posX + 150.0, this.posY + 15.0 + n7, module.isToggled() ? new Color(46, 85, 234, 155).getRGB() : new Color(28, 28, 28, 249).getRGB());
            this.fontRenderer.drawString(module.getName(), (int)this.posX + 67, (int)(this.posY + 5.0) + n7, new Color(170, 170, 170).getRGB());
            n7 += 15;
        }
        Gui.drawRect((int)(this.posX + 455.0), (int)(this.posY + 360.0), (int)(this.posX + 323.0), (int)(this.posY + 320.5), new Color(31, 31, 31, 250).getRGB());
        this.mc.fontRenderer.drawStringWithShadow("MoonLight", (float)(this.posX + 353.0), (float)(this.posY + 327.0), Chams.getRainbowChams(8000, -17));
        Gui.drawRect((int)(this.posX + 300.0), (int)(this.posY + 370.0), (int)(this.posX + 200.0), (int)(this.posY + 2.0), new Color(31, 31, 31, 250).getRGB());
        this.mc.fontRenderer.drawStringWithShadow(CatClient.UID(), (float)(this.posX + 353.0), (float)(this.posY + 337.5), Color.white.getRGB());
        this.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Status: ").append("Beta")), (float)(this.posX + 353.0), (float)(this.posY + 347.5), Color.white.getRGB());
        this.mc.fontRenderer.drawStringWithShadow("Build: 0.3", (float)(this.posX + 410.0), (float)(this.posY + 370.0), Color.white.getRGB());
        try {
            this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin());
            Gui.drawScaledCustomSizeModalRect((int)(this.posX + 328.0), (int)(this.posY + 330.0), 8.0f, 8.0f, 8, 8, 22, 22, 64.0f, 64.0f);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final Iterator<Comp> iterator2 = (Iterator<Comp>)this.comps.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().drawScreen(n, n2);
        }
        this.drawinfo(n, n2);
        this.player();
    }
    
    public ScaledResolution getScaledRes() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }
    
    public boolean isEditing() {
        return this.editing;
    }
    
    public void player() {
        try {
            this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin());
            RenderUtils.renderEntity((EntityLivingBase)this.mc.player, 70, (int)(this.posX + 375.0), (int)(this.posY + 230.0));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void mouseReleased(final int n, final int n2, final int n3) {
        super.mouseReleased(n, n2, n3);
        this.dragging = false;
        final Iterator<Comp> iterator = this.comps.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseReleased(n, n2, n3);
        }
    }
    
    protected void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
        final Iterator<Comp> iterator = this.comps.iterator();
        while (iterator.hasNext()) {
            iterator.next().keyTyped(c, n);
        }
    }
    
    public Clickgui() {
        this.anim = 0.0f;
        this.comps = new ArrayList();
        this.dragging = false;
        this.posX = this.getScaledRes().getScaledWidth() / 2 - 150;
        this.posY = this.getScaledRes().getScaledHeight() / 2 - 100;
        this.width = this.posX + 456.0;
        this.height += 380.0;
        this.selectedCategory = Category.COMBAT;
        this.x = 100.0;
        this.y = 200.0;
    }
    
    public void drawinfo(final int n, final int n2) {
        final int n3 = 800;
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
}
