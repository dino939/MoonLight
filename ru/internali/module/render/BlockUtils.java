//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.client.*;
import java.util.function.*;
import net.minecraft.entity.*;
import java.util.*;
import ru.internali.utils.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;

public class BlockUtils
{
    private static Minecraft mc;
    
    public static List getBlocksInDistance(final float n) {
        final ArrayList<Object> list = new ArrayList<Object>();
        final Iterable getAllInBox = BlockPos.getAllInBox(new BlockPos(BlockUtils.mc.player.posX - n, BlockUtils.mc.player.posY - n, BlockUtils.mc.player.posZ - n), new BlockPos(BlockUtils.mc.player.posX + n, BlockUtils.mc.player.posY + n, BlockUtils.mc.player.posZ + n));
        final ArrayList<Object> list2 = list;
        list2.getClass();
        getAllInBox.forEach(list2::add);
        return list;
    }
    
    public static LinkedList findBlocksNearEntity(final EntityLivingBase entityLivingBase, final int n, final int n2, final int n3) {
        final LinkedList<BlockPos> list = new LinkedList<BlockPos>();
        for (int i = (int)Wrapper.INSTANCE.player().posX - n3; i <= (int)Wrapper.INSTANCE.player().posX + n3; ++i) {
            for (int j = (int)Wrapper.INSTANCE.player().posZ - n3; j <= (int)Wrapper.INSTANCE.player().posZ + n3; ++j) {
                for (int getHeight = Wrapper.INSTANCE.world().getHeight(i, j), k = 0; k <= getHeight; ++k) {
                    final BlockPos blockPos = new BlockPos(i, k, j);
                    final IBlockState getBlockState = Wrapper.INSTANCE.world().getBlockState(blockPos);
                    if (n == -1 || n2 == -1) {
                        list.add(blockPos);
                    }
                    else {
                        final int getIdFromBlock = Block.getIdFromBlock(getBlockState.getBlock());
                        final int getMetaFromState = getBlockState.getBlock().getMetaFromState(getBlockState);
                        if (getIdFromBlock == n && getMetaFromState == n2) {
                            list.add(blockPos);
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public float[] getRotations(final BlockPos blockPos) {
        final double x = blockPos.getX() - BlockUtils.mc.player.posX;
        final double y = blockPos.getZ() - BlockUtils.mc.player.posZ;
        final double n = blockPos.getY() - (BlockUtils.mc.player.posY + BlockUtils.mc.player.getEyeHeight());
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float n2 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793);
        return new float[] { n2, (float)(-(Math.atan2(n2, x2) * 180.0 / 3.141592653589793)) };
    }
    
    static {
        BlockUtils.mc = Minecraft.getMinecraft();
    }
}
