//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class BoatFly extends Module
{
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "extraCalc").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "staticY").getValBoolean();
        final boolean valBoolean3 = CatClient.instance.settingsManager.getSettingByName(this, "hover").getValBoolean();
        final boolean valBoolean4 = CatClient.instance.settingsManager.getSettingByName(this, "bypass").getValBoolean();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "speed").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "verticalSpeed").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName(this, "glideSpeed").getValDouble();
        if (BoatFly.mc.player == null || BoatFly.mc.world == null || BoatFly.mc.player.getRidingEntity() == null) {
            return;
        }
        final Entity getRidingEntity = BoatFly.mc.player.getRidingEntity();
        if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown()) {
            getRidingEntity.motionY = n2;
        }
        else if (valBoolean2) {
            getRidingEntity.motionY = 0.0;
        }
        else {
            getRidingEntity.motionY = ((valBoolean3 && BoatFly.mc.player.ticksExisted % 2 == 0) ? n3 : ((double)(-n3)));
        }
        if (MovementUtil.isMoving()) {
            if (!valBoolean) {
                final double[] forward = MovementUtil.forward(n);
                getRidingEntity.motionX = forward[0];
                getRidingEntity.motionZ = forward[1];
            }
            else {
                final float direction2 = MovementUtil.getDirection2();
                final EntityPlayerSP player = BoatFly.mc.player;
                player.motionX -= MathHelper.sin(direction2) * n;
                final EntityPlayerSP player2 = BoatFly.mc.player;
                player2.motionZ += MathHelper.cos(direction2) * n;
            }
        }
        else {
            getRidingEntity.motionX = 0.0;
            getRidingEntity.motionZ = 0.0;
        }
        if (valBoolean4 && BoatFly.mc.player.ticksExisted % 4 == 0 && BoatFly.mc.player.getRidingEntity() instanceof EntityBoat) {
            BoatFly.mc.playerController.interactWithEntity((EntityPlayer)BoatFly.mc.player, BoatFly.mc.player.getRidingEntity(), EnumHand.MAIN_HAND);
        }
    }
    
    public BoatFly() {
        super("BoatFly", "BoatFly", Category.OUTHER);
        CatClient.instance.settingsManager.rSetting(new Setting("speed", this, 2.0, 0.0, 10.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("verticalSpeed", this, 1.0, 0.0, 10.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("glideSpeed", this, 0.0, -10.0, 10.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("extraCalc", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("staticY", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("hover", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("bypass", this, true));
    }
}
