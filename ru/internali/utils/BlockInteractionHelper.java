//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.block.state.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class BlockInteractionHelper
{
    private static final Minecraft mc;
    public static final List blackList;
    public static final List blackList2;
    
    public static float[] getRotationsForPosition(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n - n4;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        double degrees;
        if (n9 < 0.0 && n7 < 0.0) {
            degrees = 90.0 + Math.toDegrees(Math.atan(n9 / n7));
        }
        else if (n9 < 0.0 && n7 > 0.0) {
            degrees = -90.0 + Math.toDegrees(Math.atan(n9 / n7));
        }
        else {
            degrees = Math.toDegrees(-Math.atan(n7 / n9));
        }
        final double n10 = -Math.toDegrees(Math.atan(n8 / Math.sqrt(n7 * n7 + n9 * n9)));
        final double v = wrapAngleTo180((float)degrees);
        final double v2 = wrapAngleTo180((float)n10);
        return new float[] { (float)(Double.isNaN(v) ? 0.0 : v), (float)(Double.isNaN(v2) ? 0.0 : v2) };
    }
    
    public static ValidResult valid(final BlockPos blockPos, final boolean b) {
        if (BlockInteractionHelper.mc.world == null) {
            return ValidResult.NoEntityCollision;
        }
        if (!BlockInteractionHelper.mc.world.checkNoEntityCollision(new AxisAlignedBB(blockPos)) && !b) {
            return ValidResult.NoEntityCollision;
        }
        if (!checkForNeighbours(blockPos)) {
            return ValidResult.NoNeighbors;
        }
        if (BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.up(), blockPos.down() };
            for (int length = array.length, i = 0; i < length; ++i) {
                if (BlockInteractionHelper.mc.world.getBlockState(array[i]).getBlock() != Blocks.AIR) {
                    final EnumFacing[] values = EnumFacing.values();
                    for (int length2 = values.length, j = 0; j < length2; ++j) {
                        final BlockPos offset = blockPos.offset(values[j]);
                        if (BlockInteractionHelper.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockInteractionHelper.mc.world.getBlockState(offset), false)) {
                            return ValidResult.Ok;
                        }
                    }
                }
            }
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }
    
    private static Vec3d getEyesPos() {
        return new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ);
    }
    
    public static float[] getFacingRotations(final int n, final int n2, final int n3, final EnumFacing enumFacing) {
        return getFacingRotations(n, n2, n3, enumFacing, 1.0);
    }
    
    public static boolean IsLiquidOrAir(final BlockPos blockPos) {
        final IBlockState getBlockState = BlockInteractionHelper.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock() == Blocks.WATER || getBlockState.getBlock() == Blocks.LAVA || getBlockState.getBlock() == Blocks.AIR;
    }
    
    public static float[] getFacingRotations(final int n, final int n2, final int n3, final EnumFacing enumFacing, final double n4) {
        return getRotationsForPosition(n + 0.5 + enumFacing.getDirectionVec().getX() * n4 / 2.0, n2 + 0.5 + enumFacing.getDirectionVec().getY() * n4 / 2.0, n3 + 0.5 + enumFacing.getDirectionVec().getZ() * n4 / 2.0);
    }
    
    public static float[] getRotationsForPosition(final double n, final double n2, final double n3) {
        return getRotationsForPosition(n, n2, n3, BlockInteractionHelper.mc.player.posX, BlockInteractionHelper.mc.player.posY + BlockInteractionHelper.mc.player.getEyeHeight(), BlockInteractionHelper.mc.player.posZ);
    }
    
    public static ValidResult valid(final BlockPos blockPos) {
        return valid(blockPos, false);
    }
    
    public static float wrapAngleTo180(float n) {
        for (n %= 360.0f; n >= 180.0f; n -= 360.0f) {}
        while (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    private static PlayerControllerMP getPlayerController() {
        return Minecraft.getMinecraft().playerController;
    }
    
    public static PlaceResult place(final BlockPos blockPos, final float n, final boolean b, final boolean b2) {
        return place(blockPos, n, b, b2, false);
    }
    
    private static IBlockState getState(final BlockPos blockPos) {
        return Minecraft.getMinecraft().world.getBlockState(blockPos);
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - Minecraft.getMinecraft().player.rotationYaw), Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - Minecraft.getMinecraft().player.rotationPitch) };
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        blackList2 = Arrays.asList(Items.SLIME_BALL);
        mc = Minecraft.getMinecraft();
    }
    
    private static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(Minecraft.getMinecraft().player, BlockInteractionHelper.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
    }
    
    public static List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static PlaceResult place(final BlockPos blockPos, final float n, final boolean b, final boolean b2, final boolean b3) {
        final IBlockState getBlockState = BlockInteractionHelper.mc.world.getBlockState(blockPos);
        final boolean isReplaceable = getBlockState.getMaterial().isReplaceable();
        final boolean b4 = getBlockState.getBlock() instanceof BlockSlab;
        if (!isReplaceable && !b4) {
            return PlaceResult.NotReplaceable;
        }
        if (!checkForNeighbours(blockPos)) {
            return PlaceResult.Neighbors;
        }
        if (!b4 && valid(blockPos) != ValidResult.Ok && !isReplaceable) {
            return PlaceResult.CantPlace;
        }
        if (b2 && b4 && !getBlockState.isFullCube()) {
            return PlaceResult.CantPlace;
        }
        final Vec3d vec3d = new Vec3d(BlockInteractionHelper.mc.player.posX, BlockInteractionHelper.mc.player.posY + BlockInteractionHelper.mc.player.getEyeHeight(), BlockInteractionHelper.mc.player.posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing getOpposite = enumFacing.getOpposite();
            if (BlockInteractionHelper.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockInteractionHelper.mc.world.getBlockState(offset), false)) {
                final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
                if (vec3d.distanceTo(add) <= n) {
                    final Block getBlock = BlockInteractionHelper.mc.world.getBlockState(offset).getBlock();
                    final boolean onBlockActivated = getBlock.onBlockActivated((World)BlockInteractionHelper.mc.world, blockPos, BlockInteractionHelper.mc.world.getBlockState(blockPos), (EntityPlayer)BlockInteractionHelper.mc.player, EnumHand.MAIN_HAND, enumFacing, 0.0f, 0.0f, 0.0f);
                    if (BlockInteractionHelper.blackList.contains(getBlock) || onBlockActivated) {
                        BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractionHelper.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    if (b) {
                        faceVectorPacketInstant(add);
                    }
                    if (BlockInteractionHelper.mc.playerController.processRightClickBlock(BlockInteractionHelper.mc.player, BlockInteractionHelper.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND) != EnumActionResult.FAIL) {
                        if (b3) {
                            BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                        }
                        else {
                            BlockInteractionHelper.mc.player.swingArm(EnumHand.MAIN_HAND);
                        }
                        if (onBlockActivated) {
                            BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractionHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        }
                        return PlaceResult.Placed;
                    }
                }
            }
        }
        return PlaceResult.CantPlace;
    }
    
    public static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!Minecraft.getMinecraft().world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
    
    private static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] legitRotations = getLegitRotations(vec3d);
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], legitRotations[1], Minecraft.getMinecraft().player.onGround));
    }
    
    public static boolean checkForNeighbours(final BlockPos blockPos) {
        if (!hasNeighbour(blockPos)) {
            final EnumFacing[] values = EnumFacing.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                if (hasNeighbour(blockPos.offset(values[i]))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public enum PlaceResult
    {
        CantPlace("CantPlace", 2), 
        NotReplaceable("NotReplaceable", 0), 
        Placed("Placed", 3);
        
        private static final PlaceResult[] $VALUES;
        
        Neighbors("Neighbors", 1);
        
        private PlaceResult(final String name, final int ordinal) {
        }
        
        static {
            $VALUES = new PlaceResult[] { PlaceResult.NotReplaceable, PlaceResult.Neighbors, PlaceResult.CantPlace, PlaceResult.Placed };
        }
    }
    
    public enum ValidResult
    {
        AlreadyBlockThere("AlreadyBlockThere", 1), 
        Ok("Ok", 3);
        
        private static final ValidResult[] $VALUES;
        
        NoEntityCollision("NoEntityCollision", 0), 
        NoNeighbors("NoNeighbors", 2);
        
        static {
            $VALUES = new ValidResult[] { ValidResult.NoEntityCollision, ValidResult.AlreadyBlockThere, ValidResult.NoNeighbors, ValidResult.Ok };
        }
        
        private ValidResult(final String name, final int ordinal) {
        }
    }
}
