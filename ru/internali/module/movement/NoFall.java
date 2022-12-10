//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoFall extends Module
{
    public static List Modes;
    
    static {
        NoFall.Modes = new ArrayList();
    }
    
    public NoFall() {
        super("NoFall", "NoFall", Category.MOVEMENT);
        NoFall.Modes.add("Def");
        NoFall.Modes.add("Verus");
        NoFall.Modes.add("Old");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Def", (ArrayList)NoFall.Modes));
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent clientTickEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (Objects.equals(valString, "Def")) {
            if (NoFall.mc.player.fallDistance >= 2.2f) {
                NoFall.mc.player.motionX = 0.03999999910593033;
                NoFall.mc.player.motionY = -55.0;
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
            }
        }
        else if (Objects.equals(valString, "Verus")) {
            if (NoFall.mc.player.fallDistance >= 2.0f) {
                NoFall.mc.player.motionX = 0.01;
                NoFall.mc.player.motionY = -0.0010101001323131;
                NoFall.mc.player.motionX = 0.05;
                NoFall.mc.player.motionY = -0.0010101001323131;
                NoFall.mc.player.motionX = 0.0;
                NoFall.mc.player.motionY = -0.0010101001323131;
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(false));
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(false));
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
            }
        }
        else if (Objects.equals(valString, "Old")) {
            if (NoFall.mc.player.fallDistance > 5.0f) {
                NoFall.mc.player.capabilities.isFlying = true;
                NoFall.mc.getConnection().sendPacket((Packet)new CPacketPlayer(true));
            }
            else {
                NoFall.mc.player.capabilities.isFlying = false;
            }
        }
    }
}
