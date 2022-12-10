//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import ru.internali.module.*;
import java.util.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoHeal extends Module
{
    public AutoHeal() {
        super("AutoHeal", "AutoHeal", Category.COMBAT);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Bandage");
        list.add("Syringe");
        list.add("Fish");
        list.add("Steak");
        CatClient.instance.settingsManager.rSetting(new Setting("HealItem", this, "Bandage", list));
        CatClient.instance.settingsManager.rSetting(new Setting("Health", this, 10.0, 1.0, 20.0, true));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("Health").getValDouble();
        for (int i = 0; i < 9; ++i) {
            final EnumDyeColor byDyeDamage = EnumDyeColor.byDyeDamage(AutoHeal.mc.player.inventory.getStackInSlot(i).getMetadata());
            if (CatClient.instance.settingsManager.getSettingByName("HealItem").getValString().equalsIgnoreCase("Bandage") && EnumDyeColor.GREEN == byDyeDamage && AutoHeal.mc.player.getHealth() < n) {
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(i));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(AutoHeal.mc.player.inventory.currentItem));
            }
            if (CatClient.instance.settingsManager.getSettingByName("HealItem").getValString().equalsIgnoreCase("Syringe") && EnumDyeColor.GRAY == byDyeDamage && AutoHeal.mc.player.getHealth() < n) {
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(i));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(AutoHeal.mc.player.inventory.currentItem));
            }
            if (CatClient.instance.settingsManager.getSettingByName("HealItem").getValString().equalsIgnoreCase("Steak") && EnumDyeColor.MAGENTA == byDyeDamage && AutoHeal.mc.player.getHealth() < n) {
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(i));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(AutoHeal.mc.player.inventory.currentItem));
            }
            if (CatClient.instance.settingsManager.getSettingByName("HealItem").getValString().equalsIgnoreCase("Fish") && AutoHeal.mc.player.inventory.getStackInSlot(i).getItem() == Items.GOLD_NUGGET && AutoHeal.mc.player.getHealth() < n) {
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(i));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoHeal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(AutoHeal.mc.player.inventory.currentItem));
            }
        }
    }
}
