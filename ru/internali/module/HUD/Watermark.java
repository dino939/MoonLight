//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraftforge.client.event.*;
import ru.internali.*;
import java.text.*;
import java.util.*;
import net.minecraft.client.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import ru.internali.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class Watermark extends Module
{
    public static List Modes;
    private final FontRenderer fr;
    
    static {
        Watermark.Modes = new ArrayList();
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (Objects.equals(valString, "Anim")) {
                final int[] rainbow = getRainbow(5, 0.1f);
                RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
                final String format = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String str = "MoonLight";
                switch ((int)(System.currentTimeMillis() / 700L % 42L)) {
                    case 1: {
                        str = "  ";
                        break;
                    }
                    case 2: {
                        str = " | ";
                        break;
                    }
                    case 3: {
                        str = " |/ ";
                        break;
                    }
                    case 4: {
                        str = " |// ";
                        break;
                    }
                    case 5: {
                        str = "M ";
                        break;
                    }
                    case 6: {
                        str = "M3 ";
                        break;
                    }
                    case 7: {
                        str = "Mo ";
                        break;
                    }
                    case 8: {
                        str = "Moo\\ ";
                        break;
                    }
                    case 9: {
                        str = "Moo\\/ ";
                        break;
                    }
                    case 10: {
                        str = "Moon ";
                        break;
                    }
                    case 11: {
                        str = "Moon3 ";
                        break;
                    }
                    case 12: {
                        str = "Moonl| ";
                        break;
                    }
                    case 13: {
                        str = "Moonl|2 ";
                        break;
                    }
                    case 14: {
                        str = "Moonl|_ ";
                        break;
                    }
                    case 15: {
                        str = "Moonli ";
                        break;
                    }
                    case 16: {
                        str = "Moonli/ ";
                        break;
                    }
                    case 17: {
                        str = "Moonlig ";
                        break;
                    }
                    case 18: {
                        str = "Moonlig@# ";
                        break;
                    }
                    case 19: {
                        str = "Moonligh ";
                        break;
                    }
                    case 20: {
                        str = "Moonligh|/ ";
                        break;
                    }
                    case 21: {
                        str = "Moonlight ";
                        break;
                    }
                    case 22: {
                        str = "Moonligh|/ ";
                        break;
                    }
                    case 23: {
                        str = "Moonligh ";
                        break;
                    }
                    case 24: {
                        str = "Moonlig@# ";
                        break;
                    }
                    case 25: {
                        str = "Moonlig ";
                        break;
                    }
                    case 26: {
                        str = "Moonli/ ";
                        break;
                    }
                    case 27: {
                        str = "Moonli ";
                        break;
                    }
                    case 28: {
                        str = "Moonl|_ ";
                        break;
                    }
                    case 29: {
                        str = "Moonl|2 ";
                        break;
                    }
                    case 30: {
                        str = "Moonl| ";
                        break;
                    }
                    case 31: {
                        str = "Moon3 ";
                        break;
                    }
                    case 32: {
                        str = "Moon ";
                        break;
                    }
                    case 33: {
                        str = "Moo\\/ ";
                        break;
                    }
                    case 34: {
                        str = "Moo\\ ";
                        break;
                    }
                    case 35: {
                        str = "Mo ";
                        break;
                    }
                    case 36: {
                        str = "M3 ";
                        break;
                    }
                    case 37: {
                        str = "M ";
                        break;
                    }
                    case 38: {
                        str = " |// ";
                        break;
                    }
                    case 39: {
                        str = " |/ ";
                        break;
                    }
                    case 40: {
                        str = " | ";
                        break;
                    }
                    case 41: {
                        str = "   ";
                        break;
                    }
                }
                final String value = String.valueOf(new StringBuilder().append(str).append(" | ").append(format).append(" |  Fps ").append(Minecraft.getDebugFPS()));
                Gui.drawRect(5, 5, this.fr.getStringWidth(value) + 25, 21, new Color(37, 37, 37, 255).getRGB());
                Gui.drawRect(6, 6, this.fr.getStringWidth(value) + 24, 8, CatClient.getClientColor().getRGB());
                Gui.drawRect(5, 9, this.fr.getStringWidth(value) + 25, 9, new Color(20, 20, 20, 100).getRGB());
                this.fr.drawStringWithShadow(value, 8.0f, 11.0f, -1);
            }
            else if (Objects.equals(valString, "Static")) {
                final int[] rainbow2 = getRainbow(5, 0.1f);
                RGBtoHex(rainbow2[0], rainbow2[1], rainbow2[2]);
                final String format2 = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String str2 = "MoonLight";
                switch ((int)(System.currentTimeMillis() / 700L % 15L)) {
                    case 1: {
                        str2 = "MoonLight";
                        break;
                    }
                }
                String lowerCase;
                if (Watermark.mc.isSingleplayer()) {
                    lowerCase = "localhost";
                }
                else {
                    lowerCase = Watermark.mc.getCurrentServerData().serverIP.toLowerCase();
                }
                final String value2 = String.valueOf(new StringBuilder().append(str2).append(" | ").append(format2).append(" |  Fps ").append(Minecraft.getDebugFPS()).append("  | ").append(lowerCase));
                Gui.drawRect(5, 5, this.fr.getStringWidth(value2) + 21, 21, new Color(37, 37, 37, 255).getRGB());
                Gui.drawRect(5, 9, this.fr.getStringWidth(value2) + 21, 9, new Color(20, 20, 20, 100).getRGB());
                Gui.drawRect(6, 17, this.fr.getStringWidth(value2) + 20, 20, CatClient.getClientColor().getRGB());
                this.fr.drawStringWithShadow(value2, 8.0f, 8.0f, -1);
            }
            else if (Objects.equals(valString, "Skeet")) {
                final int[] rainbow3 = getRainbow(5, 0.1f);
                RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
                final StringBuilder obj = new StringBuilder(String.valueOf(new StringBuilder().append((Object)new StringBuilder(String.valueOf(new StringBuilder().append((Object)new StringBuilder(String.valueOf(new StringBuilder().append((Object)new StringBuilder().append("MoonLight").append(" ").append("Beta")).append(" | ").append(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())).append(" | Fps ").append(Minecraft.getDebugFPS())))).append(" | ").append(Watermark.mc.isSingleplayer() ? "localhost" : Watermark.mc.getCurrentServerData().serverIP.toLowerCase())))).append("  ")));
                final Minecraft mc = Watermark.mc;
                final String value3 = String.valueOf(obj);
                final float n = (float)(Minecraft.getMinecraft().fontRenderer.getStringWidth(value3) + 6);
                final int n2 = 20;
                final int n3 = 2;
                final int n4 = 2;
                RenderUtil.drawRect(n3, n4, n3 + n + 2.0f, n4 + n2, new Color(5, 5, 5, 255).getRGB());
                RenderUtil.drawBorderedRect(n3 + 0.5, n4 + 0.5, n3 + n + 1.5, n4 + n2 - 0.5, 0.5, new Color(40, 40, 40, 255).getRGB(), new Color(60, 60, 60, 255).getRGB(), true);
                RenderUtil.drawBorderedRect(n3 + 2, n4 + 2, n3 + n, n4 + n2 - 2, 0.5, new Color(22, 22, 22, 255).getRGB(), new Color(60, 60, 60, 255).getRGB(), true);
                RenderUtil.drawRect(n3 + 2.5, n4 + 2.5, n3 + n - 0.5, n4 + 4.5, new Color(9, 9, 9, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0, n4 + 3, 4.0f + n / 3.0f, n4 + 4, new Color(81, 149, 219, 255).getRGB(), new Color(180, 49, 218, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0f + n / 3.0f, n4 + 3, 4.0f + n / 3.0f * 2.0f, n4 + 4, new Color(180, 49, 218, 255).getRGB(), new Color(236, 93, 128, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0f + n / 3.0f * 2.0f, n4 + 3, n / 3.0f * 3.0f + 1.0f, n4 + 4, new Color(236, 93, 128, 255).getRGB(), new Color(235, 255, 0, 255).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(value3, (float)(4 + n3), (float)(7 + n4), -1);
            }
            else if (Objects.equals(valString, "OnetapOLD")) {
                final String value4 = String.valueOf(new StringBuilder().append("MoonLight | ").append(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())).append(" |  Fps ").append(Minecraft.getDebugFPS()));
                Gui.drawRect(5, 7, this.fr.getStringWidth(value4) + 25, 19, new Color(37, 37, 37, 111).getRGB());
                Gui.drawRect(5, 6, this.fr.getStringWidth(value4) + 25, 8, ColorUtils.astolfoColors((int)(Math.abs(System.currentTimeMillis() / 50L) / 100.0 + 0.16999999999999998), 5));
                Gui.drawRect(5, 9, this.fr.getStringWidth(value4) + 25, 9, new Color(20, 20, 20, 87).getRGB());
                this.fr.drawStringWithShadow(value4, 8.0f, 9.1f, -1);
            }
        }
    }
    
    public static int RGBtoHex(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }
    
    public Watermark() {
        super("Watermark", "Watermark", Category.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        Watermark.Modes.add("Anim");
        Watermark.Modes.add("Static");
        Watermark.Modes.add("Skeet");
        Watermark.Modes.add("OnetapOLD");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Skeet", (ArrayList)Watermark.Modes));
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
}
