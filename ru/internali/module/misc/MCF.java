//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import ru.internali.utils.fov.*;
import org.lwjgl.input.*;
import ru.internali.utils.friend.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class MCF extends Module
{
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
        if (objectMouseOver == null) {
            return;
        }
        final Entity entityHit;
        if (objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && (entityHit = objectMouseOver.entityHit) instanceof EntityPlayer && !(entityHit instanceof EntityArmorStand) && !Minecraft.getMinecraft().player.isDead && Minecraft.getMinecraft().player.canEntityBeSeen(entityHit)) {
            final String playerName = UtilsForFov.getPlayerName((EntityPlayer)entityHit);
            if (Mouse.isButtonDown(2) && Minecraft.getMinecraft().currentScreen == null) {
                FriendManager.addFriend(playerName);
            }
            else if (Mouse.isButtonDown(1) && Minecraft.getMinecraft().currentScreen == null) {
                FriendManager.removeFriend(playerName);
            }
        }
    }
    
    public MCF() {
        super("MCF", "MCF", Category.OUTHER);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
}
