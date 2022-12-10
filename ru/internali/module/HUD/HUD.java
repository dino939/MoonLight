//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.entity.*;
import ru.internali.module.*;
import net.minecraft.client.*;
import ru.internali.*;
import ru.internali.settings.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;
import com.google.common.base.*;
import ru.internali.utils.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;

public class HUD extends Module
{
    public int oYpos;
    float animX;
    public int oHeight;
    float animY;
    public static List Modes;
    private static RenderItem kappita;
    public int oWidth;
    TimerUtils timer;
    public int width;
    private Entity target;
    private static final RenderItem itemRender;
    private int y;
    public int oXpos;
    public int height;
    private final FontRenderer fr;
    private List modulesSorted;
    BooleanSetting info;
    String text;
    private float anima;
    float animYMot;
    private int x;
    float animXMot;
    
    public HUD() {
        super("HUD", "modules on screen", Category.HUD);
        this.animX = 0.0f;
        this.animY = 1.0f;
        this.animXMot = 1.4f;
        this.animYMot = 1.4f;
        this.text = "connecting";
        this.timer = new TimerUtils();
        this.fr = Minecraft.getMinecraft().fontRenderer;
        HUD.Modes.add("Default");
        HUD.Modes.add("Bibr");
        HUD.Modes.add("Skeet");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Skeet", (ArrayList)HUD.Modes));
        CatClient.instance.settingsManager.rSetting(new Setting("InfoWorld", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Horizontal", this, 30.0, 0.0, 810.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Vertical", this, 60.0, 0.0, 490.0, true));
    }
    
    public static void drawRectangleCorrectly(final int n, final int n2, final int n3, final int n4, final int n5) {
        GL11.glLineWidth(1.0f);
        Gui.drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static int RGBtoHex(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }
    
    private int rainbow(final long n, final float n2) {
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((System.nanoTime() + n) / 2.0E10f % 1.0f + 100000.0f, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f * n2, color.getGreen() / 255.0f * n2, color.getBlue() / 255.0f * n2, color.getAlpha() / 255.0f).getRGB();
    }
    
    public static int[] getRainbow(final int n, final float n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        final float n6 = 6.0f * ((System.currentTimeMillis() - Math.round(n2 * 1000.0f)) % (n * 1000)) / (n * 1000);
        final int round = Math.round(255.0f * (n6 - (float)Math.floor(n6)));
        if (n6 < 1.0f) {
            n3 = 255;
            n4 = round;
        }
        else if (n6 < 2.0f) {
            n3 = 255 - round;
            n4 = 255;
        }
        else if (n6 < 3.0f) {
            n4 = 255;
            n5 = round;
        }
        else if (n6 < 4.0f) {
            n4 = 255 - round;
            n5 = 255;
        }
        else if (n6 < 5.0f) {
            n3 = round;
            n5 = 255;
        }
        else if (n6 < 6.0f) {
            n3 = 255;
            n5 = 255 - round;
        }
        return new int[] { n3, n4, n5 };
    }
    
    public void sortList() {
        (this.modulesSorted = new ArrayList(CatClient.instance.moduleManager.getModuleList())).sort(HUD::lambda$sortList$0);
    }
    
    @SubscribeEvent
    public void InfoCOMP(final RenderGameOverlayEvent.Text text) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "InfoWorld").getValBoolean();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT && valBoolean) {
            final String format = String.format(String.valueOf(new StringBuilder().append("%.2f ").append(ChatFormatting.WHITE).append("blocks/sec")), MovementHelper.getSpeed() * 16.0f);
            final String value = String.valueOf(new StringBuilder().append("").append(Minecraft.getDebugFPS()));
            final String value2 = String.valueOf(new StringBuilder().append("").append(Math.round(HUD.mc.player.posX)));
            final String value3 = String.valueOf(new StringBuilder().append("").append(Math.round(HUD.mc.player.posY)));
            final String value4 = String.valueOf(new StringBuilder().append("").append(Math.round(HUD.mc.player.posZ)));
            HUD.mc.fontRenderer.drawStringWithShadow("X: ", n, n2, CatClient.getClientColor().getRGB());
            HUD.mc.fontRenderer.drawStringWithShadow(value2, n + 10.0f, n2, -1);
            HUD.mc.fontRenderer.drawStringWithShadow("Y: ", n + 30.0f + HUD.mc.fontRenderer.getStringWidth(value2) - 17.0f, n2, CatClient.getClientColor().getRGB());
            HUD.mc.fontRenderer.drawStringWithShadow(value3, n + 40.0f + HUD.mc.fontRenderer.getStringWidth(value2) - 17.0f, n2, -1);
            HUD.mc.fontRenderer.drawStringWithShadow("Z: ", n + 66.0f + HUD.mc.fontRenderer.getStringWidth(value2) - 23.0f + HUD.mc.fontRenderer.getStringWidth(value3) - 17.0f, n2, CatClient.getClientColor().getRGB());
            HUD.mc.fontRenderer.drawStringWithShadow(value4, n + 76.0f + HUD.mc.fontRenderer.getStringWidth(value2) - 23.0f + HUD.mc.fontRenderer.getStringWidth(value3) - 17.0f, n2, -1);
            HUD.mc.fontRenderer.drawStringWithShadow("FPS: ", n, n2 + 11.0f, CatClient.getClientColor().getRGB());
            HUD.mc.fontRenderer.drawStringWithShadow(value, n + 22.0f, n2 + 11.0f, -1);
            HUD.mc.fontRenderer.drawStringWithShadow(format, n + HUD.mc.fontRenderer.getStringWidth(value) + 25.0f, n2 + 11.0f, CatClient.getClientColor().getRGB());
        }
    }
    
    public static int raindbow(final int n) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 20L)) % 360.0 / 360.0), 0.5f, 1.0f).getRGB();
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (Objects.equals(valString, "Default")) {
            if (text.getType() != RenderGameOverlayEvent.ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }
            final int[] rainbow = getRainbow(5, 0.1f);
            RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
            this.sortList();
            final ArrayList<String> list = new ArrayList<String>();
            for (final Module module : this.modulesSorted) {
                if (!module.getName().equalsIgnoreCase("HUD") && module.isToggled() && module.visible) {
                    list.add(module.getName());
                }
            }
            final int FONT_HEIGHT = HUD.mc.fontRenderer.FONT_HEIGHT;
            final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            long n = 0L;
            int n2 = 0;
            final ScaledResolution scaledResolution2 = new ScaledResolution(HUD.mc);
            final int[] array = { 1 };
            int n3 = 0;
            for (int i = 0; i < list.size(); ++i) {
                n3 += HUD.mc.fontRenderer.FONT_HEIGHT + 5;
            }
            for (int j = 0; j < list.size(); ++j) {
                final String s = list.get(j);
                if (!Strings.isNullOrEmpty(s)) {
                    final int FONT_HEIGHT2 = HUD.mc.fontRenderer.FONT_HEIGHT;
                    final int n4 = scaledResolution2.getScaledWidth() - 2 - HUD.mc.fontRenderer.getStringWidth(s);
                    final int n5 = 1 + (FONT_HEIGHT2 + 2) * j;
                    final int astolfoColors = ColorUtils.astolfoColors(array[0] * 15, n3);
                    CatClient.color = new Color(astolfoColors);
                    if (j == 0) {}
                    Gui.drawRect(n4 + HUD.mc.fontRenderer.getStringWidth(s), n5 - 1, n4 + HUD.mc.fontRenderer.getStringWidth(s) + 1, n5 + 11, astolfoColors);
                    RenderUtil.drawRect(n4 - 2, n5, n4 + HUD.mc.fontRenderer.getStringWidth(s), n5 + 11, 1140850688);
                    HUD.mc.fontRenderer.getStringWidth(s);
                    if (j == this.modulesSorted.size() - 1) {
                        RenderUtil.drawGlow(n4 - 2, n5 + 10, n4 + HUD.mc.fontRenderer.getStringWidth(s), n5 + 11, astolfoColors, 1000.0);
                    }
                    HUD.mc.fontRenderer.drawString(s, n4, n5 + 1, astolfoColors);
                    ++n;
                    ++n2;
                    final int[] array2 = array;
                    final int n6 = 0;
                    ++array2[n6];
                }
            }
            this.modulesSorted.clear();
        }
        else if (Objects.equals(valString, "Bibr")) {
            if (text.getType() != RenderGameOverlayEvent.ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }
            this.sortList();
            final ArrayList<String> list2 = new ArrayList<String>();
            for (final Module module2 : this.modulesSorted) {
                if (!module2.getName().equalsIgnoreCase("HUD") && module2.isToggled() && module2.visible) {
                    list2.add(module2.getName());
                }
            }
            final int FONT_HEIGHT3 = HUD.mc.fontRenderer.FONT_HEIGHT;
            final ScaledResolution scaledResolution3 = new ScaledResolution(Minecraft.getMinecraft());
            long n7 = 0L;
            int n8 = 0;
            final ScaledResolution scaledResolution4 = new ScaledResolution(HUD.mc);
            final int[] array3 = { 1 };
            int n9 = 0;
            for (int k = 0; k < list2.size(); ++k) {
                n9 += HUD.mc.fontRenderer.FONT_HEIGHT + 5;
            }
            for (int l = 0; l < list2.size(); ++l) {
                final String s2 = list2.get(l);
                if (!Strings.isNullOrEmpty(s2)) {
                    final int FONT_HEIGHT4 = HUD.mc.fontRenderer.FONT_HEIGHT;
                    final int n10 = scaledResolution4.getScaledWidth() - 1 - HUD.mc.fontRenderer.getStringWidth(s2);
                    final int n11 = 1 + (FONT_HEIGHT4 + 2) * l;
                    final int astolfoColors2 = ColorUtils.astolfoColors(array3[0] * 15, n9);
                    CatClient.color = new Color(astolfoColors2);
                    if (l == 0) {}
                    Gui.drawRect(n10 + HUD.mc.fontRenderer.getStringWidth(s2), n11 - 1, n10 + HUD.mc.fontRenderer.getStringWidth(s2) + 1, n11 + 11, astolfoColors2);
                    RenderUtil.drawRect(n10 - 2, n11, n10 + HUD.mc.fontRenderer.getStringWidth(s2), n11 + 11, 1140850688);
                    HUD.mc.fontRenderer.getStringWidth(s2);
                    if (l == this.modulesSorted.size() - 1) {
                        RenderUtil.drawGlow(n10 - 2, n11 + 10, n10 + HUD.mc.fontRenderer.getStringWidth(s2), n11 + 11, astolfoColors2, 1000.0);
                    }
                    HUD.mc.fontRenderer.drawString(s2, n10, n11 + 1, astolfoColors2);
                    ++n7;
                    ++n8;
                    final int[] array4 = array3;
                    final int n12 = 0;
                    ++array4[n12];
                }
            }
            this.modulesSorted.clear();
        }
        else if (Objects.equals(valString, "Skeet")) {
            if (text.getType() != RenderGameOverlayEvent.ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }
            this.sortList();
            final ArrayList<String> list3 = new ArrayList<String>();
            for (final Module module3 : this.modulesSorted) {
                if (!module3.getName().equalsIgnoreCase("HUD") && module3.isToggled() && module3.visible) {
                    list3.add(module3.getName());
                }
            }
            final int FONT_HEIGHT5 = HUD.mc.fontRenderer.FONT_HEIGHT;
            final ScaledResolution scaledResolution5 = new ScaledResolution(Minecraft.getMinecraft());
            long n13 = 0L;
            int n14 = 0;
            int n15 = 0;
            int getStringWidth = 0;
            int n16 = 0;
            final ScaledResolution scaledResolution6 = new ScaledResolution(HUD.mc);
            for (int index = 0; index < list3.size(); ++index) {
                final String s3 = list3.get(index);
                if (!Strings.isNullOrEmpty(s3)) {
                    final int FONT_HEIGHT6 = HUD.mc.fontRenderer.FONT_HEIGHT;
                    final int n17 = scaledResolution6.getScaledWidth() - 1 - HUD.mc.fontRenderer.getStringWidth(s3);
                    final int n18 = 1 + (FONT_HEIGHT6 + 2) * index;
                    final int rainbowChams = getRainbowChams(6000, -15);
                    if (index == 0) {
                        Gui.drawRect(n17 - 2, n18 - 1, n17 + HUD.mc.fontRenderer.getStringWidth(s3), n18, rainbowChams);
                    }
                    Gui.drawRect(n17 - 2, n18, n17 + HUD.mc.fontRenderer.getStringWidth(s3), n18 + 11, 1140850688);
                    Gui.drawRect(n17 - 3, n18 - 1, n17 - 2, n18 + 11, rainbowChams);
                    Gui.drawRect(n15, n16, n15 + (getStringWidth - HUD.mc.fontRenderer.getStringWidth(s3)), n16 + 1, rainbowChams);
                    getStringWidth = HUD.mc.fontRenderer.getStringWidth(s3);
                    n15 = n17 - 2;
                    n16 = n18 + 10;
                    Gui.drawRect(n17 + HUD.mc.fontRenderer.getStringWidth(s3), n18 - 1, n17 + HUD.mc.fontRenderer.getStringWidth(s3) + 1, n18 + 11, rainbowChams);
                    if (index == this.modulesSorted.size() - 1) {
                        Gui.drawRect(n17 - 2, n18 + 10, n17 + HUD.mc.fontRenderer.getStringWidth(s3), n18 + 11, rainbowChams);
                    }
                    HUD.mc.fontRenderer.drawString(s3, n17, n18 + 1, rainbowChams);
                    ++n13;
                    ++n14;
                }
            }
            this.modulesSorted.clear();
        }
    }
    
    public static int getRainbowChams(final int n, final int n2) {
        return Color.getHSBColor((System.currentTimeMillis() * 1L + n2 / 2) % n * 2L / (float)n, 1.0f, 1.0f).getRGB();
    }
    
    private static int lambda$sortList$0(final Module module, final Module module2) {
        if (HUD.mc.fontRenderer.getStringWidth(module.getName()) < HUD.mc.fontRenderer.getStringWidth(module2.getName())) {
            return 1;
        }
        if (HUD.mc.fontRenderer.getStringWidth(module.getName()) > HUD.mc.fontRenderer.getStringWidth(module2.getName())) {
            return -1;
        }
        return 0;
    }
    
    public void drawitem(final ItemStack itemStack, final int n, final int n2) {
        GlStateManager.enableDepth();
        HUD.itemRender.zLevel = 200.0f;
        HUD.itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
        HUD.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, itemStack, n, n2, "");
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        if (itemStack.getCount() == 0 || itemStack.getCount() == 1) {
            this.fr.drawString("", n + 13, n2 + 10, Color.white.getRGB());
        }
        else {
            this.fr.drawString(String.valueOf(new StringBuilder().append("").append(itemStack.getCount())), n + 13, n2 + 10, Color.white.getRGB());
        }
    }
    
    static {
        HUD.Modes = new ArrayList();
        itemRender = Minecraft.getMinecraft().getRenderItem();
    }
}
