//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package click.font;

class GlyphPage
{
    private int y;
    private int width;
    private int height;
    private int x;
    
    static int access$302(final GlyphPage glyphPage, final int y) {
        return glyphPage.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    static int access$100(final GlyphPage glyphPage) {
        return glyphPage.height;
    }
    
    public int getY() {
        return this.y;
    }
    
    GlyphPage(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    static int access$102(final GlyphPage glyphPage, final int height) {
        return glyphPage.height = height;
    }
    
    static int access$202(final GlyphPage glyphPage, final int x) {
        return glyphPage.x = x;
    }
    
    static int access$002(final GlyphPage glyphPage, final int width) {
        return glyphPage.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    static int access$000(final GlyphPage glyphPage) {
        return glyphPage.width;
    }
    
    GlyphPage() {
    }
    
    static int access$300(final GlyphPage glyphPage) {
        return glyphPage.y;
    }
    
    static int access$200(final GlyphPage glyphPage) {
        return glyphPage.x;
    }
}
