//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.block.*;
import java.util.function.*;
import net.minecraft.item.*;
import net.minecraft.init.*;

public class InventoryUtil
{
    private static final Minecraft mc;
    
    public static int getBlocks(final Block block) {
        return getItems(Item.getItemFromBlock(block));
    }
    
    public static int getItems(final Item item) {
        return InventoryUtil.mc.player.inventory.mainInventory.stream().filter(InventoryUtil::lambda$getItems$0).mapToInt(ItemStack::getCount).sum() + InventoryUtil.mc.player.inventory.offHandInventory.stream().filter(InventoryUtil::lambda$getItems$1).mapToInt(ItemStack::getCount).sum();
    }
    
    private static boolean lambda$getItems$1(final Item item, final ItemStack itemStack) {
        return itemStack.getItem() == item;
    }
    
    private static boolean lambda$getItems$0(final Item item, final ItemStack itemStack) {
        return itemStack.getItem() == item;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public static int findItemInHotbar(final Item obj) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (getStackInSlot.getItem() instanceof Item) {
                    if (getStackInSlot.getItem().equals(obj)) {
                        n = i;
                        break;
                    }
                }
            }
        }
        return n;
    }
    
    public static int findBlockInHotbarObiEchestRandom() {
        final int n = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    if (((ItemBlock)getStackInSlot.getItem()).getBlock().equals(Blocks.OBSIDIAN)) {
                        return i;
                    }
                }
            }
        }
        for (int j = 0; j < 9; ++j) {
            final ItemStack getStackInSlot2 = InventoryUtil.mc.player.inventory.getStackInSlot(j);
            if (getStackInSlot2 != ItemStack.EMPTY) {
                if (getStackInSlot2.getItem() instanceof ItemBlock) {
                    if (((ItemBlock)getStackInSlot2.getItem()).getBlock().equals(Blocks.ENDER_CHEST)) {
                        return j;
                    }
                }
            }
        }
        for (int k = 0; k < 9; ++k) {
            final ItemStack getStackInSlot3 = InventoryUtil.mc.player.inventory.getStackInSlot(k);
            if (getStackInSlot3 != ItemStack.EMPTY && getStackInSlot3.getItem() instanceof ItemBlock) {
                ((ItemBlock)getStackInSlot3.getItem()).getBlock();
                return k;
            }
        }
        return n;
    }
    
    public static int findBlockInHotbar(final Block obj) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    if (((ItemBlock)getStackInSlot.getItem()).getBlock().equals(obj)) {
                        n = i;
                        break;
                    }
                }
            }
        }
        return n;
    }
}
