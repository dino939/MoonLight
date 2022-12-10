//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.block.*;
import net.minecraft.init.*;
import org.lwjgl.opengl.*;
import ru.internali.module.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TrajectoriesMod extends Module
{
    public static List listA;
    private transient int BOX;
    
    static {
        TrajectoriesMod.listA = new ArrayList();
    }
    
    public static boolean isCollidable(final Block block) {
        return block != Blocks.AIR && block != Blocks.BEETROOTS && block != Blocks.CARROTS && block != Blocks.DEADBUSH && block != Blocks.DOUBLE_PLANT && block != Blocks.FLOWING_LAVA && block != Blocks.FLOWING_WATER && block != Blocks.LAVA && block != Blocks.MELON_STEM && block != Blocks.NETHER_WART && block != Blocks.POTATOES && block != Blocks.PUMPKIN_STEM && block != Blocks.RED_FLOWER && block != Blocks.RED_MUSHROOM && block != Blocks.REDSTONE_TORCH && block != Blocks.TALLGRASS && block != Blocks.TORCH && block != Blocks.UNLIT_REDSTONE_TORCH && block != Blocks.YELLOW_FLOWER && block != Blocks.VINE && block != Blocks.WATER && block != Blocks.WEB && block != Blocks.WHEAT;
    }
    
    public static void drawSolidBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(7);
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
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glEnd();
    }
    
    public TrajectoriesMod() {
        super("Trajectories", "Show how will fly peral or arrow", Category.RENDER);
        this.BOX = 0;
    }
    
    boolean predictHit(final Vec3d vec3d, Vec3d add, Vec3d vec3d2, final double n) {
        boolean b = false;
        for (int i = 0; i < 250; ++i) {
            add = add.add(vec3d2.scale(0.4));
            vec3d2 = new Vec3d(vec3d2.x * 0.996, vec3d2.y * 0.996 - n * 4.0, vec3d2.z * 0.996);
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity instanceof EntityLiving && entity.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(add)) {
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            }
            for (final EntityPlayer entityPlayer : Minecraft.getMinecraft().world.playerEntities) {
                if (entityPlayer != Minecraft.getMinecraft().player && entityPlayer.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(add)) {
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            }
            if (isCollidable(Minecraft.getMinecraft().world.getBlockState(new BlockPos(add)).getBlock())) {
                break;
            }
        }
        return b;
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent renderWorldLastEvent) {
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        final ItemStack getCurrentItem = ((EntityPlayer)player).inventory.getCurrentItem();
        if (getCurrentItem == null) {
            return;
        }
        final Item getItem = getCurrentItem.getItem();
        if (!(getItem instanceof ItemBow) && !(getItem instanceof ItemSnowball) && !(getItem instanceof ItemEgg) && !(getItem instanceof ItemEnderPearl) && !(getItem instanceof ItemSplashPotion) && !(getItem instanceof ItemLingeringPotion) && !(getItem instanceof ItemFishingRod)) {
            return;
        }
        final boolean b = getCurrentItem.getItem() instanceof ItemBow;
        double n = ((EntityPlayer)player).lastTickPosX + (((EntityPlayer)player).posX - ((EntityPlayer)player).lastTickPosX) * TrajectoriesMod.mc.getRenderPartialTicks() - Math.cos((float)Math.toRadians(((EntityPlayer)player).rotationYaw)) * 0.07999999821186066;
        double n2 = ((EntityPlayer)player).lastTickPosY + (((EntityPlayer)player).posY - ((EntityPlayer)player).lastTickPosY) * TrajectoriesMod.mc.getRenderPartialTicks() + ((EntityPlayer)player).getEyeHeight() - 0.04;
        double n3 = ((EntityPlayer)player).lastTickPosZ + (((EntityPlayer)player).posZ - ((EntityPlayer)player).lastTickPosZ) * TrajectoriesMod.mc.getRenderPartialTicks() - Math.sin((float)Math.toRadians(((EntityPlayer)player).rotationYaw)) * 0.07999999821186066;
        final float n4 = b ? 1.0f : 0.4f;
        final float n5 = (float)Math.toRadians(((EntityPlayer)player).rotationYaw);
        final float n6 = (float)Math.toRadians(((EntityPlayer)player).rotationPitch);
        final double n7 = -Math.sin(n5) * Math.cos(n6) * n4;
        final double n8 = -Math.sin(n6) * n4;
        final double n9 = Math.cos(n5) * Math.cos(n6) * n4;
        final double sqrt = Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
        final double n10 = n7 / sqrt;
        final double n11 = n8 / sqrt;
        final double n12 = n9 / sqrt;
        double n16;
        double n17;
        double n18;
        if (b) {
            final float n13 = (72000 - ((EntityPlayer)player).getItemInUseCount()) / 20.0f;
            float n14 = (n13 * n13 + n13 * 2.0f) / 3.0f;
            if (n14 > 1.0f || n14 <= 0.1f) {
                n14 = 1.0f;
            }
            final float n15 = n14 * 3.0f;
            n16 = n10 * n15;
            n17 = n11 * n15;
            n18 = n12 * n15;
        }
        else {
            n16 = n10 * 1.5;
            n17 = n11 * 1.5;
            n18 = n12 * 1.5;
        }
        GL11.glPushAttrib(24837);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final double n19 = b ? 0.005 : ((getItem instanceof ItemPotion) ? 0.04 : ((getItem instanceof ItemFishingRod) ? 0.015 : 0.003));
        final Vec3d vec3d = new Vec3d(((EntityPlayer)player).posX, ((EntityPlayer)player).posY + ((EntityPlayer)player).getEyeHeight(), ((EntityPlayer)player).posZ);
        int n20 = 0;
        final boolean predictHit = this.predictHit(vec3d, new Vec3d(n, n2, n3), new Vec3d(n16, n17, n18), n19);
        if (predictHit) {
            GL11.glColor4f(0.9f, 0.2f, 0.1f, 0.5f);
        }
        else {
            GL11.glColor4f(0.0f, 0.9f, 0.8f, 0.5f);
        }
        GL11.glBegin(3);
        for (int i = 0; i < 1000; ++i) {
            if (Minecraft.getMinecraft().world.rayTraceBlocks(vec3d, new Vec3d(n, n2, n3)) != null) {
                if (predictHit) {
                    GL11.glColor4f(0.3f, 0.1f, 0.1f, 0.5f);
                }
                else {
                    GL11.glColor4f(0.1f, 0.3f, 0.3f, 0.5f);
                }
            }
            else if (predictHit) {
                GL11.glColor4f(0.9f, 0.2f, 0.1f, 0.5f);
            }
            else {
                GL11.glColor4f(0.0f, 0.9f, 0.8f, 0.5f);
            }
            GL11.glVertex3d(n - getRenderManager.viewerPosX, n2 - getRenderManager.viewerPosY, n3 - getRenderManager.viewerPosZ);
            n += n16 * 0.1;
            n2 += n17 * 0.1;
            n3 += n18 * 0.1;
            n16 *= 0.999;
            final double n21 = n17 * 0.999;
            n18 *= 0.999;
            n17 = n21 - n19;
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity instanceof EntityLiving && entity.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(new Vec3d(n, n2, n3))) {
                    n20 = 1;
                    break;
                }
            }
            if (n20 != 0) {
                break;
            }
            for (final EntityPlayer entityPlayer : Minecraft.getMinecraft().world.playerEntities) {
                if (entityPlayer != Minecraft.getMinecraft().player && entityPlayer.getEntityBoundingBox().grow(0.35, 0.35, 0.35).contains(new Vec3d(n, n2, n3))) {
                    n20 = 1;
                    break;
                }
            }
            if (n20 != 0) {
                break;
            }
            if (isCollidable(Minecraft.getMinecraft().world.getBlockState(new BlockPos(new Vec3d(n, n2, n3))).getBlock())) {
                break;
            }
        }
        GL11.glEnd();
        GL11.glPushMatrix();
        GL11.glTranslated(n - getRenderManager.viewerPosX, n2 - getRenderManager.viewerPosY, n3 - getRenderManager.viewerPosZ);
        GL11.glCallList(this.BOX);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
    
    public void onEnable() {
        super.onEnable();
        GL11.glNewList(this.BOX = GL11.glGenLists(1), 4864);
        drawSolidBox(new AxisAlignedBB(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5));
        GL11.glEndList();
    }
}
