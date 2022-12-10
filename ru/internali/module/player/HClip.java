//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import ru.internali.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import ru.internali.utils.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class HClip extends Module
{
    public static List Modess;
    
    static {
        HClip.Modess = new ArrayList();
    }
    
    public void onEnable() {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "CLIP").getValDouble();
        final float f = (float)CatClient.instance.settingsManager.getSettingByName(this, "FwDistance").getValDouble();
        if (Objects.equals(valString, "UP")) {
            Clip.mc.player.setPosition(Clip.mc.player.posX, Clip.mc.player.posY + n, Clip.mc.player.posZ);
            new CPacketPlayer.Position();
        }
        else if (Objects.equals(valString, "DOWN")) {
            Clip.mc.player.setPosition(Clip.mc.player.posX, Clip.mc.player.posY - n, Clip.mc.player.posZ);
            new CPacketPlayer.Position();
        }
        else if (Objects.equals(valString, "Forward")) {
            final double double1 = Double.parseDouble(String.valueOf(f));
            final double[] direction = MathUtils.getDirection(HClip.mc.player.rotationYaw);
            Clip.mc.player.setPosition(Clip.mc.player.posX + direction[0] * double1, Clip.mc.player.posY, Clip.mc.player.posZ + direction[1] * double1);
        }
        this.setToggled(false);
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    public HClip() {
        super("HClip", "Dont use horse", Category.PLAYER);
        CatClient.instance.settingsManager.rSetting(new Setting("CLIP", this, 90.0, 1.0, 255.0, true));
        HClip.Modess.add("UP");
        HClip.Modess.add("DOWN");
        HClip.Modess.add("Forward");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "DOWN", (ArrayList)HClip.Modess));
        CatClient.instance.settingsManager.rSetting(new Setting("FwDistance", this, 1.0, -20.0, 255.0, true));
    }
}
