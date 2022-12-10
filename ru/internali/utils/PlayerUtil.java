//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;

public class PlayerUtil
{
    public static void swapInventoryItems(final int n, final int n2) {
        final short getNextTransactionID = Minecraft.getMinecraft().player.inventoryContainer.getNextTransactionID(Minecraft.getMinecraft().player.inventory);
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketClickWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, Minecraft.getMinecraft().player.inventoryContainer.slotClick(n, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player), getNextTransactionID));
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketClickWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId, n2, 0, ClickType.PICKUP, Minecraft.getMinecraft().player.inventoryContainer.slotClick(n2, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player), getNextTransactionID));
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketClickWindow(Minecraft.getMinecraft().player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, Minecraft.getMinecraft().player.inventoryContainer.slotClick(n, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player), getNextTransactionID));
        Minecraft.getMinecraft().playerController.updateController();
    }
    
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(MinecraftHelper.mc.player.posX), Math.floor(MinecraftHelper.mc.player.posY), Math.floor(MinecraftHelper.mc.player.posZ));
    }
    
    public static double[] getPlayerMoveVec() {
        float rotationYaw = Minecraft.getMinecraft().player.rotationYaw;
        final float moveForward = Minecraft.getMinecraft().player.moveForward;
        final float moveStrafing = Minecraft.getMinecraft().player.moveStrafing;
        if (moveForward == 0.0f && moveStrafing == 0.0f) {
            return new double[] { 0.0, 0.0 };
        }
        if (moveForward > 0.0f) {
            if (moveStrafing > 0.0f) {
                rotationYaw -= 45.0f;
            }
            else if (moveStrafing < 0.0f) {
                rotationYaw += 45.0f;
            }
        }
        else if (moveForward < 0.0f) {
            rotationYaw -= 180.0f;
            if (moveStrafing > 0.0f) {
                rotationYaw += 45.0f;
            }
            else if (moveStrafing < 0.0f) {
                rotationYaw -= 45.0f;
            }
        }
        else if (moveStrafing > 0.0f) {
            rotationYaw -= 90.0f;
        }
        else if (moveStrafing < 0.0f) {
            rotationYaw += 90.0f;
        }
        return new double[] { -Math.sin(Math.toRadians(rotationYaw)), Math.cos(Math.toRadians(rotationYaw)) };
    }
    
    public static void swapItems(final int n, final int n2) {
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, n2, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.updateController();
    }
    
    public static boolean isPlayerTrapped() {
        final BlockPos getLocalPlayerPosFloored = GetLocalPlayerPosFloored();
        for (final BlockPos blockPos : new BlockPos[] { getLocalPlayerPosFloored.down(), getLocalPlayerPosFloored.up().up(), getLocalPlayerPosFloored.north(), getLocalPlayerPosFloored.south(), getLocalPlayerPosFloored.east(), getLocalPlayerPosFloored.west(), getLocalPlayerPosFloored.north().up(), getLocalPlayerPosFloored.south().up(), getLocalPlayerPosFloored.east().up(), getLocalPlayerPosFloored.west().up() }) {
            if (MinecraftHelper.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN && MinecraftHelper.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
}
