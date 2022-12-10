//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import java.nio.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.vertex.*;
import org.lwjgl.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.entity.*;
import java.awt.image.*;
import java.awt.*;
import ru.internali.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import ru.internali.utils.friend.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.client.model.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class RenderUtil implements MinecraftHelper
{
    private static boolean bind;
    private static boolean texture;
    public static RenderItem itemRender;
    private static final FloatBuffer modelView;
    private static boolean override;
    private static final FloatBuffer projection;
    private static final IntBuffer viewport;
    private static HashMap shadowCache;
    private static final FloatBuffer screenCoords;
    private static boolean depth;
    private static final double DOUBLE_PI = 6.283185307179586;
    private static final AxisAlignedBB DEFAULT_AABB;
    private static final Frustum frustrum;
    public static ICamera camera;
    private static boolean clean;
    
    public static void drawSolidBox() {
        drawSolidBox(RenderUtil.DEFAULT_AABB);
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static void drawImage(final ResourceLocation resourceLocation, final int n, final int n2, final int n3, final int n4) {
        GL11.glEnable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, n3, n4, (float)n3, (float)n4);
        disableGL2D();
        GL11.glPopMatrix();
    }
    
    public static void drawOctagon(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7) {
        final float convertColor = convertColor(24, n7);
        final float convertColor2 = convertColor(16, n7);
        final float convertColor3 = convertColor(8, n7);
        final float convertColor4 = convertColor(0, n7);
        glRenderStart();
        GL11.glColor4f(convertColor2, convertColor3, convertColor4, convertColor);
        GL11.glBegin(9);
        GL11.glVertex2d((double)(n + n5), (double)n2);
        GL11.glVertex2d((double)(n + n3 - n5), (double)n2);
        GL11.glVertex2d((double)(n + n3 - n5), (double)n2);
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4 / 2.0f - n6));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4 / 2.0f - n6));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4 / 2.0f + n6));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4 / 2.0f + n6));
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n5), (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n5), (double)(n2 + n4));
        GL11.glVertex2d((double)n, (double)(n2 + n4 / 2.0f + n6));
        GL11.glVertex2d((double)n, (double)(n2 + n4 / 2.0f + n6));
        GL11.glVertex2d((double)n, (double)(n2 + n4 / 2.0f - n6));
        GL11.glVertex2d((double)n, (double)(n2 + n4 / 2.0f - n6));
        GL11.glVertex2d((double)(n + n5), (double)n2);
        GL11.glEnd();
        glRenderStop();
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getInstance.draw();
    }
    
    public static void drawBorderedRect(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final int n7) {
        drawRect(n, n2, n3, n4, n7);
        final float n8 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n6 & 0xFF) / 255.0f;
        glRenderStart();
        GL11.glColor4f(n9, n10, n11, n8);
        GL11.glLineWidth(n5);
        GL11.glEnable(2848);
        GL11.glBegin(1);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n3, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glEnd();
        glRenderStop();
    }
    
    public static void drawCircle228(final float n, final float n2, final float n3, final int n4, final int n5, final int n6, final int n7) {
        drawCircle228(n, n2, n3, n6, n7);
    }
    
    public static void draw2DTriangleDown(final double n, final double n2, final double n3, final double n4, final float n5, final float n6, final float n7, final float n8) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.color(n5, n6, n7, n8);
        getBuffer.begin(4, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n2, 0.0).endVertex();
        getBuffer.pos(n + n3 / 2.0, n2 + n4, 0.0).endVertex();
        getBuffer.pos(n + n3, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawCircle222(final float n, final float n2, final float n3, final int n4, final int n5) {
        final float n6 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n4 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glLineWidth(2.5f);
        GL11.glBegin(3);
        for (int i = 0; i <= n5; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
    
    public static double interpolate(final double n, final double n2, final double n3) {
        return n2 + (n - n2) * n3;
    }
    
    public static void color228(final int n) {
        GL11.glColor4ub((byte)(n >> 16 & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF), (byte)(n >> 24 & 0xFF));
    }
    
    public static void drawPolygonPart(final double n, final double n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n, n2, 0.0).color(n8, n9, n10, n7).endVertex();
        for (int i = n4 * 90; i <= n4 * 90 + 90; ++i) {
            final double n15 = 6.283185307179586 * i / 360.0 + Math.toRadians(180.0);
            getBuffer.pos(n + Math.sin(n15) * n3, n2 + Math.cos(n15) * n3, 0.0).color(n12, n13, n14, n11).endVertex();
        }
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static float convertColor(final int n, final int n2) {
        return (n2 >> n & 0xFF) / 255.0f;
    }
    
    static {
        RenderUtil.itemRender = RenderUtil.mc.getRenderItem();
        RenderUtil.camera = (ICamera)new Frustum();
        DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        RenderUtil.depth = GL11.glIsEnabled(2896);
        RenderUtil.texture = GL11.glIsEnabled(3042);
        RenderUtil.clean = GL11.glIsEnabled(3553);
        RenderUtil.bind = GL11.glIsEnabled(2929);
        RenderUtil.override = GL11.glIsEnabled(2848);
        screenCoords = BufferUtils.createFloatBuffer(3);
        viewport = BufferUtils.createIntBuffer(16);
        modelView = BufferUtils.createFloatBuffer(16);
        projection = BufferUtils.createFloatBuffer(16);
        frustrum = new Frustum();
        RenderUtil.shadowCache = new HashMap();
    }
    
    public static void drawFilledCircle(final EntityPlayerSP entityPlayerSP, final float n, final float n2, final int n3, final float n4, final int n5) {
    }
    
    public static void drawRect(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawFadeESP(final Entity entity, final ColorUtils colorUtils, final ColorUtils colorUtils2) {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(3);
        for (int i = 0; i <= 360; ++i) {
            ColorUtils.glColor(Color.green);
            GL11.glVertex3d(entity.posX, entity.posY, entity.posZ);
            ColorUtils.getColor(Color.darkGray);
            GL11.glVertex3d(entity.posX - Math.sin(Math.toRadians(i)), entity.posY, entity.posZ + Math.cos(Math.toRadians(i)));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void renderEntityBoundingBox(final Entity entity, final float n, final float n2, final float n3, final float n4) {
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final Vec3d interpolateEntity = MathUtils.interpolateEntity(entity);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(interpolateEntity.x - entity.width / 2.0f, interpolateEntity.y, interpolateEntity.z - entity.width / 2.0f, interpolateEntity.x + entity.width / 2.0f, interpolateEntity.y + entity.height, interpolateEntity.z + entity.width / 2.0f).offset(-getRenderManager.viewerPosX, -getRenderManager.viewerPosY, -getRenderManager.viewerPosZ), n, n2, n3, n4);
    }
    
    public static void draw2DRect(final double n, final double n2, final double n3, final double n4, final float n5, final float n6, final float n7, final float n8) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.color(n5, n6, n7, n8);
        getBuffer.begin(5, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n2, 0.0).endVertex();
        getBuffer.pos(n, n2 + n4, 0.0).endVertex();
        getBuffer.pos(n + n3, n2, 0.0).endVertex();
        getBuffer.pos(n + n3, n2 + n4, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void renderBlurredShadow(double n, double n2, double clamp, double clamp2, final int n3, final Color color) {
        GlStateManager.alphaFunc(516, 0.01f);
        final float n4 = (float)((n -= n3) - 0.25);
        final float n5 = (float)((n2 -= n3) + 0.25);
        final int i = (int)((clamp += n3 * 2) * (clamp2 += n3 * 2) + clamp + color.hashCode() * n3 + n3);
        GL11.glEnable(3553);
        GL11.glDisable(2884);
        GL11.glEnable(3008);
        GL11.glEnable(3042);
        final int j = -1;
        if (RenderUtil.shadowCache.containsKey(i)) {
            GlStateManager.bindTexture((int)RenderUtil.shadowCache.get(i));
        }
        else {
            clamp = MathHelper.clamp(clamp, 0.01, clamp);
            clamp2 = MathHelper.clamp(clamp2, 0.01, clamp2);
            final Graphics graphics = new BufferedImage((int)clamp, (int)clamp2, 2).getGraphics();
            graphics.setColor(color);
            graphics.fillRect(n3, n3, (int)clamp - n3 * 2, (int)clamp2 - n3 * 2);
            graphics.dispose();
            RenderUtil.shadowCache.put(i, j);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(n4, n5);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(n4, n5 + (int)clamp2);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(n4 + (int)clamp, n5 + (int)clamp2);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(n4 + (int)clamp, n5);
        GL11.glEnd();
        GL11.glDisable(3553);
    }
    
    public static Color injectAlpha(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static void glRenderStart() {
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
    }
    
    public static void drawRoundedRect(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n + n3;
        final int n7 = n2 + n4;
        drawRect(n + 1, n2, n6 - 1, n7, n5);
        drawRect(n, n2 + 1, n6, n7 - 1, n5);
    }
    
    public static void drawSolidBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(7);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glEnd();
    }
    
    public static void drawBlockESP(final Vec3dSimple vec3dSimple, final float n, final float n2, final float n3) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glTranslated(-Minecraft.getMinecraft().getRenderManager().viewerPosX, -Minecraft.getMinecraft().getRenderManager().viewerPosY, -Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        GL11.glTranslated(vec3dSimple.x, vec3dSimple.y, vec3dSimple.z);
        GL11.glColor4f(n, n2, n3, 0.3f);
        drawSolidBox();
        GL11.glColor4f(n, n2, n3, 0.7f);
        drawOutlinedBox();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawLinesAroundPlayer(final Entity entity, final double n, final float n2, final int n3, final double n4, final int n5, final float n6) {
        GL11.glPushMatrix();
        enableGL2D3();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glDisable(2929);
        GL11.glLineWidth((float)n4);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glBegin(3);
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final double n7 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n2 - getRenderManager.viewerPosX;
        final double n8 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n2 - getRenderManager.viewerPosY + n6;
        final double n9 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n2 - getRenderManager.viewerPosZ;
        color228(n5);
        for (int i = 0; i <= n3; ++i) {
            GL11.glVertex3d(n7 + n * Math.cos(i * 6.283185307179586 / n3), n8, n9 + n * Math.sin(i * 6.283185307179586 / n3));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        disableGL2D3();
        GL11.glPopMatrix();
    }
    
    public static void glColor(final int n) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static void relativeRect(float n, float n2, float n3, float n4, final int n5) {
        if (n < n3) {
            final float n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final float n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos((double)n, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawCircle(float n, double n2, float n3, final int n4, int raindbow) {
        GL11.glPushMatrix();
        n *= 2.0f;
        n2 *= 2.0;
        GlStateManager.glLineWidth(6.0f);
        final float n5 = (raindbow >> 24 & 0xFF) / 255.0f;
        final float n6 = (raindbow >> 16 & 0xFF) / 255.0f;
        final float n7 = (raindbow >> 8 & 0xFF) / 255.0f;
        final float n8 = (raindbow & 0xFF) / 255.0f;
        final float n9 = 0.017453292f;
        final float n10 = (float)Math.cos(n9);
        final float n11 = (float)Math.sin(n9);
        float n12;
        n3 = (n12 = n3 * 2.0f);
        float n13 = 0.0f;
        GL11.glEnable(2848);
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(n6, n7, n8, n5);
        GL11.glPointSize(6.0f);
        GL11.glBegin(3);
        final int[] array = { 1 };
        for (int i = 0; i < 360 - n4; ++i) {
            raindbow = raindbow(array[0] * 10);
            GL11.glColor4f((raindbow >> 16 & 0xFF) / 255.0f, (raindbow >> 8 & 0xFF) / 255.0f, (raindbow & 0xFF) / 255.0f, (raindbow >> 24 & 0xFF) / 255.0f);
            GL11.glVertex2f(n12 + n, (float)(n13 + n2));
            final float n14 = n12;
            n12 = n10 * n12 - n11 * n13;
            n13 = n11 * n14 + n10 * n13;
            final int[] array2 = array;
            final int n15 = 0;
            ++array2[n15];
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.glLineWidth(1.0f);
        GL11.glPopMatrix();
    }
    
    public static void GLPre(final float n) {
        RenderUtil.depth = GL11.glIsEnabled(2896);
        RenderUtil.texture = GL11.glIsEnabled(3042);
        RenderUtil.clean = GL11.glIsEnabled(3553);
        RenderUtil.bind = GL11.glIsEnabled(2929);
        RenderUtil.override = GL11.glIsEnabled(2848);
        GLPre(RenderUtil.depth, RenderUtil.texture, RenderUtil.clean, RenderUtil.bind, RenderUtil.override, n);
    }
    
    public static void drawGradientSideways(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(n8, n9, n10, n7);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glColor4f(n12, n13, n14, n11);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void renderEntityBoundingBox(final Entity entity, final RGBSetting rgbSetting, final float n) {
        renderEntityBoundingBox(entity, rgbSetting.getRed() / 255.0f, rgbSetting.getGreen() / 255.0f, rgbSetting.getBlue() / 255.0f, n);
    }
    
    public static void drawColorBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
    }
    
    public static void blockESP(final BlockPos blockPos) {
        GL11.glPushMatrix();
        final double n = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        final double n2 = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        final double n3 = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        RenderGlobal.renderFilledBox(new AxisAlignedBB(n, n2, n3, n + 1.0, n2 + 1.0, n3 + 1.0), 0.0f, 1.0f, 0.0f, 0.5f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void blockEspFrame(final BlockPos blockPos, final double n, final double n2, final double n3) {
        final double n4 = blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX;
        final double n5 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        final double n6 = n5 - RenderUtil.mc.getRenderManager().viewerPosY;
        final double n7 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        final double n8 = n7 - RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(n, n2, n3, 0.5);
        drawSelectionBoundingBox(new AxisAlignedBB(n4, n6, n8, n4 + 1.0, n6 + 1.0, n8 + 1.0));
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void renderEntityFilledBoundingBox(final Entity entity, final RGBSetting rgbSetting, final float n) {
        renderEntityFilledBoundingBox(entity, rgbSetting.getRed() / 255.0f, rgbSetting.getGreen() / 255.0f, rgbSetting.getBlue() / 255.0f, n);
    }
    
    public static void drawNewRect(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBorderedRect(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7, final boolean b) {
        drawRect(n - (b ? 0.0 : n5), n2 - (b ? 0.0 : n5), n3 + (b ? 0.0 : n5), n4 + (b ? 0.0 : n5), n7);
        drawRect(n + (b ? n5 : 0.0), n2 + (b ? n5 : 0.0), n3 - (b ? n5 : 0.0), n4 - (b ? n5 : 0.0), n6);
    }
    
    public static void rectangleBordered(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        drawRect(n + n5, n2 + n5, n3 - n5, n4 - n5, n6);
        drawRect(n + n5, n2, n3 - n5, n2 + n5, n7);
        drawRect(n, n2, n + n5, n4, n7);
        drawRect(n3 - n5, n2, n3, n4, n7);
        drawRect(n + n5, n4 - n5, n3 - n5, n4, n7);
    }
    
    private static void GLPost(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        GlStateManager.depthMask(true);
        if (!b5) {
            GL11.glDisable(2848);
        }
        if (b4) {
            GL11.glEnable(2929);
        }
        if (b3) {
            GL11.glEnable(3553);
        }
        if (!b2) {
            GL11.glDisable(3042);
        }
        if (b) {
            GL11.glEnable(2896);
        }
    }
    
    public static void blockEsp(final BlockPos blockPos, final Color color, final double n, final double n2) {
        final double n3 = blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX;
        final double n4 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        final double n5 = n4 - RenderUtil.mc.getRenderManager().viewerPosY;
        final double n6 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        final double n7 = n6 - RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), 0.15);
        drawColorBox(new AxisAlignedBB(n3, n5, n7, n3 + n2, n5 + 1.0, n7 + n), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        drawSelectionBoundingBox(new AxisAlignedBB(n3, n5, n7, n3 + n2, n5 + 1.0, n7 + n));
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawESPBoxes(final List list, final int n, final float n2) {
        GL11.glLineWidth(2.0f);
        for (final Entity entity : list) {
            GL11.glPushMatrix();
            final Vec3d interpolatedPos = EntityUtil.getInterpolatedPos(entity, n2);
            GL11.glTranslated(interpolatedPos.x, interpolatedPos.y, interpolatedPos.z);
            GL11.glScaled(entity.width + 0.1, entity.height + 0.1, entity.width + 0.1);
            if (entity instanceof EntityPlayer && FriendManager.isFriend(entity.getName())) {
                GL11.glColor4f(0.9f, 0.2f, 1.0f, 0.5f);
            }
            else if (entity instanceof EntityItem) {
                GL11.glColor4f(0.5f, 0.5f, 1.0f, 0.5f);
            }
            else {
                final float n3 = Minecraft.getMinecraft().player.getDistance(entity) / 20.0f;
                GL11.glColor4f(2.0f - n3, n3, 0.0f, 0.5f);
            }
            GL11.glCallList(n);
            GL11.glPopMatrix();
        }
    }
    
    public static void drawUnfilledCircle(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n5 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 24 & 0xFF) / 255.0f;
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GlStateManager.enableBlend();
        GL11.glColor4f(n6, n7, n8, n9);
        GL11.glLineWidth(n4);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
        GlStateManager.disableBlend();
    }
    
    public static void drawRectWithEdge(final double n, final double n2, final double n3, final double n4, final Color color, final Color color2) {
        drawRect(n, n2, n + n3, n2 + n4, color.getRGB());
        final int rgb = color2.getRGB();
        drawRect(n - 1.0, n2, n, n2 + n4, rgb);
        drawRect(n + n3, n2, n + n3 + 1.0, n2 + n4, rgb);
        drawRect(n - 1.0, n2 - 1.0, n + n3 + 1.0, n2, rgb);
        drawRect(n - 1.0, n2 + n4, n + n3 + 1.0, n2 + n4 + 1.0, rgb);
    }
    
    public static void drawRoundedRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        drawRect(n + 0.5, n2, n3 - 0.5, n2 + 0.5, n6);
        drawRect(n + 0.5, n4 - 0.5, n3 - 0.5, n4, n6);
        drawRect(n, n2 + 0.5, n3, n4 - 0.5, n6);
    }
    
    public static void drawOutlinedBox() {
        drawOutlinedBox();
    }
    
    public static float[][] getBipedRotations(final ModelBiped modelBiped) {
        return new float[][] { { modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ }, { modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ }, { modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ }, { modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ }, { modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ } };
    }
    
    public static void drawGradientSidewaysRGB(final double n, final double n2, final double n3, final double n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void draw2DTriangleRight(final double n, final double n2, final double n3, final double n4, final float n5, final float n6, final float n7, final float n8) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.color(n5, n6, n7, n8);
        getBuffer.begin(4, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n2, 0.0).endVertex();
        getBuffer.pos(n, n2 + n4, 0.0).endVertex();
        getBuffer.pos(n + n3, n2 + n4 / 2.0, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(1);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glEnd();
    }
    
    public static final void drawSmoothRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        drawRect(n, n2, n3, n4, n5);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect(n * 2.0f - 1.0f, n2 * 2.0f, n * 2.0f, n4 * 2.0f - 1.0f, n5);
        drawRect(n * 2.0f, n2 * 2.0f - 1.0f, n3 * 2.0f, n2 * 2.0f, n5);
        drawRect(n3 * 2.0f, n2 * 2.0f, n3 * 2.0f + 1.0f, n4 * 2.0f - 1.0f, n5);
        drawRect(n * 2.0f, n4 * 2.0f - 1.0f, n3 * 2.0f, n4 * 2.0f, n5);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void renderItem(final ItemStack itemStack, final int n, final int n2) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        RenderUtil.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
        RenderUtil.mc.getRenderItem().renderItemOverlays(RenderUtil.mc.fontRenderer, itemStack, n, n2);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }
    
    public static void drawOutline(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        GL11.glPushMatrix();
        GL11.glLineWidth(n);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getInstance.draw();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
    }
    
    public static void drawFilledCircle(final int n, final int n2, final float n3, final Color color) {
        final int n4 = 6;
        final double n5 = 6.283185307179586 / n4;
        GL11.glPushAttrib(8192);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glBegin(6);
        for (int i = 0; i < n4; ++i) {
            final float n6 = (float)(n3 * Math.sin(i * n5));
            final float n7 = (float)(n3 * Math.cos(i * n5));
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glVertex2f(n + n6, n2 + n7);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }
    
    public static void disableGL2D3() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void enableGL2D3() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void drawCircle228(final float n, final float n2, final float n3, final int n4, final int n5) {
        final float n6 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n4 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glLineWidth(2.5f);
        GL11.glBegin(3);
        for (int i = 0; i <= n5; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
    
    public static void drawGlow(final double n, final double n2, final double n3, final double n4, final int n5, final double n6) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        drawVGradientRect((float)(int)n, (float)(int)n2, (float)(int)n3, (float)(int)(n2 + (n4 - n2) / 2.0), injectAlpha(new Color(n5), 0).getRGB(), injectAlpha(new Color(n5), (int)n6).getRGB());
        drawVGradientRect((float)(int)n, (float)(int)(n2 + (n4 - n2) / 2.0), (float)(int)n3, (float)(int)n4, injectAlpha(new Color(n5), (int)n6).getRGB(), injectAlpha(new Color(n5), 0).getRGB());
        final int n7 = (int)((n4 - n2) / 2.0);
        drawPolygonPart(n, n2 + (n4 - n2) / 2.0, n7, 0, injectAlpha(new Color(n5), (int)n6).getRGB(), injectAlpha(new Color(n5), 0).getRGB());
        drawPolygonPart(n, n2 + (n4 - n2) / 2.0, n7, 1, injectAlpha(new Color(n5), (int)n6).getRGB(), injectAlpha(new Color(n5), 0).getRGB());
        drawPolygonPart(n3, n2 + (n4 - n2) / 2.0, n7, 2, injectAlpha(new Color(n5), (int)n6).getRGB(), injectAlpha(new Color(n5), 0).getRGB());
        drawPolygonPart(n3, n2 + (n4 - n2) / 2.0, n7, 3, injectAlpha(new Color(n5), (int)n6).getRGB(), injectAlpha(new Color(n5), 0).getRGB());
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void renderEntityFilledBoundingBox(final Entity entity, final float n, final float n2, final float n3, final float n4) {
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final Vec3d interpolateEntity = MathUtils.interpolateEntity(entity);
        RenderGlobal.renderFilledBox(new AxisAlignedBB(interpolateEntity.x - entity.width / 2.0f, interpolateEntity.y, interpolateEntity.z - entity.width / 2.0f, interpolateEntity.x + entity.width / 2.0f, interpolateEntity.y + entity.height, interpolateEntity.z + entity.width / 2.0f).offset(-getRenderManager.viewerPosX, -getRenderManager.viewerPosY, -getRenderManager.viewerPosZ), n, n2, n3, n4);
    }
    
    public static void drawBoundingBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(7);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glEnd();
    }
    
    public static void customScaledObject2D(final float n, final float n2, final float n3, final float n4, final float n5) {
        GL11.glTranslated((double)(n3 / 2.0f), (double)(n4 / 2.0f), 1.0);
        GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0f * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0f * -n5), 1.0);
        GL11.glScaled((double)n5, (double)n5, 0.0);
    }
    
    public static void glRenderStop() {
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    private static boolean isInViewFrustrum(final AxisAlignedBB axisAlignedBB) {
        final Entity getRenderViewEntity = RenderUtil.mc.getRenderViewEntity();
        RenderUtil.frustrum.setPosition(getRenderViewEntity.posX, getRenderViewEntity.posY, getRenderViewEntity.posZ);
        return RenderUtil.frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final int n4) {
        final float n5 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n6 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n4 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n6, n7, n8, n5);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
    
    public static void drawFilledCircleNoGL(final int n, final int n2, final double n3, final int n4, final int n5) {
        GL11.glColor4f((n4 >> 16 & 0xFF) / 255.0f, (n4 >> 8 & 0xFF) / 255.0f, (n4 & 0xFF) / 255.0f, (n4 >> 24 & 0xFF) / 255.0f);
        GL11.glBegin(6);
        for (int i = 0; i <= 360 / n5; ++i) {
            GL11.glVertex2d(n + Math.sin(i * n5 * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * n5 * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
    }
    
    public static void drawESPTracers(final List list) {
        final Vec3d add = new Vec3d(Minecraft.getMinecraft().getRenderManager().viewerPosX, Minecraft.getMinecraft().getRenderManager().viewerPosY + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().getRenderManager().viewerPosZ).add(Minecraft.getMinecraft().player.getLookVec());
        GL11.glLineWidth(2.0f);
        GL11.glBegin(1);
        for (final Entity entity : list) {
            final Vec3d getCenter = entity.getEntityBoundingBox().getCenter();
            if (entity instanceof EntityPlayer && FriendManager.isFriend(entity.getName())) {
                GL11.glColor4f(0.9f, 0.2f, 1.0f, 0.5f);
            }
            else if (entity instanceof EntityItem) {
                GL11.glColor4f(0.5f, 0.5f, 1.0f, 0.5f);
            }
            else {
                final float n = Minecraft.getMinecraft().player.getDistance(entity) / 20.0f;
                GL11.glColor4f(2.0f - n, n, 0.0f, 0.5f);
            }
            GL11.glVertex3d(add.x, add.y, add.z);
            GL11.glVertex3d(getCenter.x, getCenter.y, getCenter.z);
        }
        GL11.glEnd();
    }
    
    public static void renderBlurredShadow(final Color color, final double n, final double n2, final double n3, final double n4, final int n5) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2884);
        renderBlurredShadow(n, n2, n3, n4, n5, color);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glEnable(3008);
        GL11.glEnable(3553);
        GL11.glEnable(3042);
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void ShulkerESP(final BlockPos blockPos) {
        GL11.glPushMatrix();
        final double n = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        final double n2 = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        final double n3 = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        RenderGlobal.renderFilledBox(new AxisAlignedBB(n, n2, n3, n + 1.0, n2 + 1.0, n3 + 1.0), 0.0f, 0.0f, 1.0f, 0.5f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    private static void GLPre(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final float n) {
        if (b) {
            GL11.glDisable(2896);
        }
        if (!b2) {
            GL11.glEnable(3042);
        }
        GL11.glLineWidth(n);
        if (b3) {
            GL11.glDisable(3553);
        }
        if (b4) {
            GL11.glDisable(2929);
        }
        if (!b5) {
            GL11.glEnable(2848);
        }
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint(3154, 4354);
        GlStateManager.depthMask(false);
    }
    
    public static void GlPost() {
        GLPost(RenderUtil.depth, RenderUtil.texture, RenderUtil.clean, RenderUtil.bind, RenderUtil.override);
    }
    
    public static void draw2DRectLines(final double n, final double n2, final double n3, final double n4, final float n5, final float n6, final float n7, final float n8) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.color(n5, n6, n7, n8);
        getBuffer.begin(2, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n2, 0.0).endVertex();
        getBuffer.pos(n, n2 + n4, 0.0).endVertex();
        getBuffer.pos(n + n3, n2 + n4, 0.0).endVertex();
        getBuffer.pos(n + n3, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBorderedCircle(final float n, final float n2, final float n3, final int n4, final int n5, final int n6) {
        drawCircle(n, n2, n3, n6);
        drawUnfilledCircle(n, n2, n3, (float)n4, n5);
    }
    
    public static void drawBlockESP(final BlockPos blockPos, final float n, final float n2, final float n3) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glTranslated(-Minecraft.getMinecraft().getRenderManager().viewerPosX, -Minecraft.getMinecraft().getRenderManager().viewerPosY, -Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        GL11.glTranslated((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
        GL11.glColor4f(n, n2, n3, 0.3f);
        drawSolidBox();
        GL11.glColor4f(n, n2, n3, 0.7f);
        drawOutlinedBox();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawRectOpacity(double n, double n2, double n3, double n4, final float n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(0.1f, 0.1f, 0.1f, n5);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), (double)(color.getAlpha() / 255.0f));
    }
    
    public static void prepareScissorBox(final float n, final float n2, final float n3, final float n4) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int getScaleFactor = scaledResolution.getScaleFactor();
        GL11.glScissor((int)(n * getScaleFactor), (int)((scaledResolution.getScaledHeight() - n4) * getScaleFactor), (int)((n3 - n) * getScaleFactor), (int)((n4 - n2) * getScaleFactor));
    }
    
    public static void drawLinesAroundPlayer(final Entity entity, final double n, final float n2, final int n3, final float n4, final int n5) {
        GL11.glPushMatrix();
        enableGL2D3();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glDisable(2929);
        GL11.glLineWidth(n4);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glBegin(3);
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final double n6 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n2 - getRenderManager.viewerPosX;
        final double n7 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n2 - getRenderManager.viewerPosY;
        final double n8 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n2 - getRenderManager.viewerPosZ;
        color228(n5);
        for (int i = 0; i <= n3; ++i) {
            GL11.glVertex3d(n6 + n * Math.cos(i * 6.283185307179586 / n3), n7, n8 + n * Math.sin(i * 6.283185307179586 / n3));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        disableGL2D3();
        GL11.glPopMatrix();
    }
    
    public static void drawShadowRect(final int n, final int n2, final int n3, final int n4, final int n5) {
    }
    
    public static int raindbow(final int n) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 20L)) % 360.0 / 360.0), 0.5f, 1.0f).getRGB();
    }
    
    public static void drawVGradientRect(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawTracer(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5) {
        final double viewerPosX = Minecraft.getMinecraft().getRenderManager().viewerPosX;
        final double viewerPosY = Minecraft.getMinecraft().getRenderManager().viewerPosY;
        final double viewerPosZ = Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        final double n6 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n5 - viewerPosX;
        final double n7 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n5 + entity.height / 2.0f - viewerPosY;
        final double n8 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n5 - viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(n, n2, n3, n4);
        final Vec3d vec3d = null;
        GL11.glBegin(1);
        GL11.glVertex3d(vec3d.x, Minecraft.getMinecraft().player.getEyeHeight() + vec3d.y, vec3d.z);
        GL11.glVertex3d(n6, n7, n8);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDepthMask(true);
    }
}
