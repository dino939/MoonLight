//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import ru.internali.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import ru.internali.utils.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class Clip extends Module
{
    public static List Modess;
    
    static {
        Clip.Modess = new ArrayList();
    }
    
    public void onEnable() {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float f = (float)CatClient.instance.settingsManager.getSettingByName(this, "FwDistance").getValDouble();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "CLIP").getValDouble();
        if (Clip.mc.player.getRidingEntity() != null) {
            if (Objects.equals(valString, "UP")) {
                new CPacketPlayer.Position();
                Objects.requireNonNull(Clip.mc.player.getRidingEntity()).setPosition(Clip.mc.player.getRidingEntity().posX, Clip.mc.player.getRidingEntity().posY + n, Clip.mc.player.getRidingEntity().posZ);
            }
            else if (Objects.equals(valString, "DOWN")) {
                new CPacketPlayer.Position();
                Objects.requireNonNull(Clip.mc.player.getRidingEntity()).setPosition(Clip.mc.player.getRidingEntity().posX, Clip.mc.player.getRidingEntity().posY - n, Clip.mc.player.getRidingEntity().posZ);
            }
            else if (Objects.equals(valString, "Forward")) {
                new CPacketPlayer.Position();
                Objects.requireNonNull(Clip.mc.player.getRidingEntity()).setPosition(Clip.mc.player.getRidingEntity().posX, Clip.mc.player.getRidingEntity().posY - n, Clip.mc.player.getRidingEntity().posZ);
                final double double1 = Double.parseDouble(String.valueOf(f));
                final double[] direction = MathUtils.getDirection(Clip.mc.player.rotationYaw);
                Objects.requireNonNull(Clip.mc.player.getRidingEntity()).setPosition(Clip.mc.player.getRidingEntity().posX + direction[0] * double1, Clip.mc.player.getRidingEntity().posY, Clip.mc.player.getRidingEntity().posZ + direction[1] * double1);
            }
        }
        this.setToggled(false);
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    public Clip() {
        super("Clip", "Clip", Category.PLAYER);
        CatClient.instance.settingsManager.rSetting(new Setting("CLIP", this, 90.0, 1.0, 255.0, true));
        Clip.Modess.add("UP");
        Clip.Modess.add("DOWN");
        Clip.Modess.add("Forward");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "DOWN", (ArrayList)Clip.Modess));
        CatClient.instance.settingsManager.rSetting(new Setting("FwDistance", this, 1.0, -20.0, 255.0, true));
    }
}
