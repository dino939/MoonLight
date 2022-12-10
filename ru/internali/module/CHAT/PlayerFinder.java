//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.CHAT;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import ru.internali.utils.*;
import net.minecraft.world.storage.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.item.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import ru.internali.module.movement.*;
import java.util.*;

public class PlayerFinder extends Module
{
    public static int deley;
    
    public PlayerFinder() {
        super("PlayerFinder", "PlayerFinder", Category.CHAT);
        CatClient.instance.settingsManager.rSetting(new Setting("amountPerTick", this, 2.0, 0.0, 5.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("DeleySpam", this, 50.0, 1.0, 200.0, true));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final PacketEvent packetEvent) {
        if (PacketEvent.getPacket() instanceof SPacketMaps) {
            ((SPacketMaps)PacketEvent.getPacket()).setMapdataTo(new MapData("haha i get ur coords"));
        }
        if (PacketEvent.getPacket() instanceof SPacketEntityVelocity || PacketEvent.getPacket() instanceof SPacketEntityTeleport) {
            packetEvent.setCanceled(true);
        }
        if (PacketEvent.getPacket() instanceof CPacketConfirmTeleport || PacketEvent.getPacket() instanceof CPacketPlayerTryUseItem) {
            packetEvent.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "DeleySpam").getValDouble();
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "amountPerTick").getValBoolean();
        if (PlayerFinder.mc.player.getRidingEntity() instanceof EntityBoat) {
            if (PlayerFinder.mc.player.inventory.getCurrentItem().getItem().equals(Items.MAP)) {
                PlayerFinder.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(PlayerFinder.mc.player.getPosition(), EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, -1337.77f, 0.0f));
            }
            int n2 = 0;
            while (valBoolean) {
                PlayerFinder.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PlayerFinder.mc.player.posX, -1337.77, PlayerFinder.mc.player.posZ, false));
                PlayerFinder.mc.player.connection.sendPacket((Packet)new CPacketSteerBoat(false, true));
                ++n2;
            }
        }
        for (final Entity entity : PlayerFinder.mc.world.playerEntities) {
            ++PlayerFinder.deley;
            if (PlayerFinder.deley == 0 && !entity.getName().equalsIgnoreCase(PlayerFinder.mc.player.getName())) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append("Found A Player ").append(entity.getName()).append(" ").append(entity.getPosition())));
            }
            if (PlayerFinder.deley >= n) {
                PlayerFinder.deley = -1;
            }
        }
    }
}
