//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Airshoot extends Module
{
    public static List Modes;
    
    static {
        Airshoot.Modes = new ArrayList();
    }
    
    public Airshoot() {
        super("Airshoot", "AntiAim", Category.COMBAT);
        Airshoot.Modes.add("Bypass");
        Airshoot.Modes.add("Bypass V2");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Bypass V2", (ArrayList)Airshoot.Modes));
        CatClient.instance.settingsManager.rSetting(new Setting("slot", this, 1.0, 1.0, 9.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("slot2", this, 1.0, 1.0, 9.0, true));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "slot").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "slot2").getValDouble();
        if (Objects.equals(valString, "Bypass")) {
            if (Airshoot.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, Airshoot.mc.getRenderPartialTicks()) != 1.0f) {
                Airshoot.mc.player.inventory.currentItem = (int)n - 1;
            }
            if (Airshoot.mc.player.onGround && !Airshoot.mc.gameSettings.keyBindAttack.isKeyDown()) {
                Airshoot.mc.player.inventory.currentItem = (int)n2 - 1;
            }
        }
        else if (Objects.equals(valString, "Bypass V2") && (!Airshoot.mc.gameSettings.keyBindAttack.isKeyDown() || Airshoot.mc.player.swingProgress > 0.0f)) {
            if (Airshoot.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, Airshoot.mc.getRenderPartialTicks()) != 1.0f) {
                Airshoot.mc.player.inventory.currentItem = (int)n - 1;
            }
            if (Airshoot.mc.player.onGround && !Airshoot.mc.gameSettings.keyBindAttack.isKeyDown()) {
                Airshoot.mc.player.inventory.currentItem = (int)n2 - 1;
            }
        }
    }
}
