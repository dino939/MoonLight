//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import ru.internali.module.*;
import java.util.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class WaterFast extends Module
{
    public WaterFast() {
        super("WaterFast", "WaterFast", Category.MOVEMENT);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Up");
        list.add("Down");
        CatClient.instance.settingsManager.rSetting(new Setting("WaterMode", this, "Up", list));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final Block getBlock = WaterFast.mc.world.getBlockState(new BlockPos(WaterFast.mc.player.posX, WaterFast.mc.player.posY + 0.2, WaterFast.mc.player.posZ)).getBlock();
        if (WaterFast.mc.player.isInWater() && Block.getIdFromBlock(getBlock) != 0) {
            WaterFast.mc.player.onGround = false;
            WaterFast.mc.player.jump();
            WaterFast.mc.player.onGround = false;
            if (CatClient.instance.settingsManager.getSettingByName("WaterMode").getValString().equalsIgnoreCase("Up")) {
                WaterFast.mc.player.motionY = 0.4;
            }
            if (CatClient.instance.settingsManager.getSettingByName("WaterMode").getValString().equalsIgnoreCase("Down")) {
                WaterFast.mc.player.motionY = -0.4;
            }
        }
        WaterFast.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)WaterFast.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
    }
}
