//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import ru.internali.utils.fov.*;
import java.util.*;
import ru.internali.utils.friend.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class Command extends Module
{
    public static List Modes;
    
    static {
        Command.Modes = new ArrayList();
    }
    
    @SubscribeEvent
    public void FriendModule(final ClientChatEvent clientChatEvent) {
        final RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
        if (objectMouseOver == null) {
            return;
        }
        final Entity entityHit;
        if (objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && (entityHit = objectMouseOver.entityHit) instanceof EntityPlayer && !(entityHit instanceof EntityArmorStand) && !Minecraft.getMinecraft().player.isDead && Minecraft.getMinecraft().player.canEntityBeSeen(entityHit)) {
            final String playerName = UtilsForFov.getPlayerName((EntityPlayer)entityHit);
            final String message = clientChatEvent.getMessage();
            if (Objects.equals(message, String.valueOf(new StringBuilder().append(".Friend add").append(playerName)))) {
                FriendManager.addFriend(playerName);
            }
            else if (Objects.equals(message, String.valueOf(new StringBuilder().append(".Friend remove").append(playerName)))) {
                FriendManager.removeFriend(playerName);
            }
        }
    }
    
    public Command() {
        super("Command", "Command", Category.HUD);
    }
}
