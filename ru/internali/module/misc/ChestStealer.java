//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import ru.internali.utils.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class ChestStealer extends Module
{
    public TimerUtils timer;
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Speed").getValDouble();
        if (ChestStealer.mc.player.openContainer != null && ChestStealer.mc.player.openContainer instanceof ContainerChest) {
            final ContainerChest containerChest = (ContainerChest)ChestStealer.mc.player.openContainer;
            for (int i = 0; i < containerChest.inventorySlots.size(); ++i) {
                if (containerChest.getLowerChestInventory().getStackInSlot(i).getItem() != Item.getItemById(0) && this.timer.isDeley((long)n)) {
                    ChestStealer.mc.playerController.windowClick(containerChest.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)ChestStealer.mc.player);
                    this.timer.reset();
                }
                else if (this.empty((Container)containerChest)) {
                    ChestStealer.mc.player.closeScreen();
                }
            }
        }
    }
    
    public boolean empty(final Container container) {
        boolean b = true;
        for (int n = (container.inventorySlots.size() == 90) ? 54 : 27, i = 0; i < n; ++i) {
            if (container.getSlot(i).getHasStack()) {
                b = false;
            }
        }
        return b;
    }
    
    public ChestStealer() {
        super("ChestStealer", "ChestStealer", Category.OUTHER);
        this.timer = new TimerUtils();
        CatClient.instance.settingsManager.rSetting(new Setting("Speed", this, 40.0, 1.0, 1000.0, true));
    }
}
