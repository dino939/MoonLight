//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package click.font;

import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class GlyphPageFontRenderer
{
    private float blue;
    private float alpha;
    private boolean underlineStyle;
    private int textColor;
    private GlyphPage boldItalicGlyphPage;
    private int[] colorCode;
    private float posY;
    private boolean italicStyle;
    private boolean randomStyle;
    private float posX;
    private float green;
    private GlyphPage italicGlyphPage;
    private boolean strikethroughStyle;
    public Random fontRandom;
    private boolean boldStyle;
    private GlyphPage boldGlyphPage;
    private float red;
    private GlyphPage regularGlyphPage;
    
    private GlyphPage getCurrentGlyphPage() {
        if (this.boldStyle && this.italicStyle) {
            return this.boldItalicGlyphPage;
        }
        if (this.boldStyle) {
            return this.boldGlyphPage;
        }
        if (this.italicStyle) {
            return this.italicGlyphPage;
        }
        return this.regularGlyphPage;
    }
    
    public String trimStringToWidth(final String s, final int n, final boolean b) {
        final StringBuilder obj = new StringBuilder();
        final int n2 = 0;
        final int n3 = 1;
        int n4 = 0;
        for (int i = n2; i < s.length(); i += n3) {
            if (i >= n) {
                break;
            }
            s.charAt(i);
            final char char1 = s.charAt(i);
            if (i > (n4 += (int)((this.getCurrentGlyphPage().getWidth() - 8.0f) / 2.0f))) {
                break;
            }
            obj.append(char1);
        }
        return String.valueOf(obj);
    }
    
    public static GlyphPageFontRenderer create(final String s, final int n, final boolean b, final boolean b2, final boolean b3) {
        final char[] array = new char[256];
        int n2 = 0;
        while (true) {
            final int length = array.length;
            array[n2] = (char)n2;
            ++n2;
        }
    }
    
    private void renderStringAtPos(final String s, final boolean b) {
        this.getCurrentGlyphPage();
        GL11.glPushMatrix();
        GL11.glScaled(0.5, 0.5, 0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableTexture2D();
        GL11.glTexParameteri(3553, 10240, 9729);
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '§' && i + 1 < s.length()) {
                int index = "0123456789abcdefklmnor".indexOf(s.toLowerCase(Locale.ENGLISH).charAt(i + 1));
                if (index < 16) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (index < 0) {
                        index = 15;
                    }
                    if (b) {
                        index += 16;
                    }
                    final int n = this.textColor = this.colorCode[index];
                    GlStateManager.color((n >> 16) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, this.alpha);
                }
                else if (index == 16) {
                    this.randomStyle = true;
                }
                else if (index == 17) {
                    this.boldStyle = true;
                }
                else if (index == 18) {
                    this.strikethroughStyle = true;
                }
                else if (index == 19) {
                    this.underlineStyle = true;
                }
                else if (index == 20) {
                    this.italicStyle = true;
                }
                else {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    GlStateManager.color(this.red, this.blue, this.green, this.alpha);
                }
                ++i;
            }
            else {
                this.getCurrentGlyphPage();
            }
        }
        GL11.glPopMatrix();
    }
    
    public GlyphPageFontRenderer(final GlyphPage regularGlyphPage, final GlyphPage boldGlyphPage, final GlyphPage italicGlyphPage, final GlyphPage boldItalicGlyphPage) {
        this.fontRandom = new Random();
        this.colorCode = new int[32];
        this.regularGlyphPage = regularGlyphPage;
        this.boldGlyphPage = boldGlyphPage;
        this.italicGlyphPage = italicGlyphPage;
        this.boldItalicGlyphPage = boldItalicGlyphPage;
        int n = 0;
        while (true) {
            final int n2 = (n >> 3 & 0x1) * 85;
            this.colorCode[n] = (((n >> 2 & 0x1) * 170 + n2 & 0xFF) << 16 | ((n >> 1 & 0x1) * 170 + n2 & 0xFF) << 8 | ((n & 0x1) * 170 + n2 & 0xFF));
            ++n;
        }
    }
    
    public int getStringWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            s.charAt(i);
            s.charAt(i);
            n += (int)(this.getCurrentGlyphPage().getWidth() - 8.0f);
        }
        return n / 2;
    }
    
    public int drawString(final String s, final float n, final float n2, final int n3, final boolean b) {
        GlStateManager.enableAlpha();
        this.resetStyles();
        int n4;
        if (b) {
            n4 = Math.max(this.renderString(s, n + 1.0f, n2 + 1.0f, n3, true), this.renderString(s, n, n2, n3, false));
        }
        else {
            n4 = this.renderString(s, n, n2, n3, false);
        }
        return n4;
    }
    
    private int renderString(final String s, final float n, final float n2, int n3, final boolean b) {
        if (s == null) {
            return 0;
        }
        if ((n3 & 0xFC000000) == 0x0) {
            n3 |= 0xFF000000;
        }
        if (b) {
            n3 = ((n3 & 0xFCFCFC) >> 2 | (n3 & 0xFF000000));
        }
        this.red = (n3 >> 16 & 0xFF) / 255.0f;
        this.blue = (n3 >> 8 & 0xFF) / 255.0f;
        this.green = (n3 & 0xFF) / 255.0f;
        this.alpha = (n3 >> 24 & 0xFF) / 255.0f;
        GlStateManager.color(this.red, this.blue, this.green, this.alpha);
        this.posX = n * 2.0f;
        this.posY = n2 * 2.0f;
        this.renderStringAtPos(s, b);
        return (int)(this.posX / 4.0f);
    }
    
    public String trimStringToWidth(final String s, final int n) {
        return this.trimStringToWidth(s, n, false);
    }
    
    public int getFontHeight() {
        return this.regularGlyphPage.getHeight() / 2;
    }
    
    private void resetStyles() {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }
    
    private void doDraw(final float n, final GlyphPage glyphPage) {
        if (this.strikethroughStyle) {
            final Tessellator getInstance = Tessellator.getInstance();
            final BufferBuilder getBuffer = getInstance.getBuffer();
            GlStateManager.disableTexture2D();
            getBuffer.begin(7, DefaultVertexFormats.POSITION);
            getBuffer.pos((double)this.posX, (double)(this.posY + glyphPage.getHeight() / 2), 0.0).endVertex();
            getBuffer.pos((double)(this.posX + n), (double)(this.posY + glyphPage.getHeight() / 2), 0.0).endVertex();
            getBuffer.pos((double)(this.posX + n), (double)(this.posY + glyphPage.getHeight() / 2 - 1.0f), 0.0).endVertex();
            getBuffer.pos((double)this.posX, (double)(this.posY + glyphPage.getHeight() / 2 - 1.0f), 0.0).endVertex();
            getInstance.draw();
            GlStateManager.enableTexture2D();
        }
        if (this.underlineStyle) {
            final Tessellator getInstance2 = Tessellator.getInstance();
            final BufferBuilder getBuffer2 = getInstance2.getBuffer();
            GlStateManager.disableTexture2D();
            getBuffer2.begin(7, DefaultVertexFormats.POSITION);
            final int n2 = this.underlineStyle ? -1 : 0;
            getBuffer2.pos((double)(this.posX + n2), (double)(this.posY + glyphPage.getHeight()), 0.0).endVertex();
            getBuffer2.pos((double)(this.posX + n), (double)(this.posY + glyphPage.getHeight()), 0.0).endVertex();
            getBuffer2.pos((double)(this.posX + n), (double)(this.posY + glyphPage.getHeight() - 1.0f), 0.0).endVertex();
            getBuffer2.pos((double)(this.posX + n2), (double)(this.posY + glyphPage.getHeight() - 1.0f), 0.0).endVertex();
            getInstance2.draw();
            GlStateManager.enableTexture2D();
        }
        this.posX += n;
    }
}
