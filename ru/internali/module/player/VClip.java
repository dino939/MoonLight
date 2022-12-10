//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import ru.internali.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class VClip extends Module
{
    public void onEnable() {
        final float f = (float)CatClient.instance.settingsManager.getSettingByName(this, "FwDistance").getValDouble();
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double double1 = Double.parseDouble(String.valueOf(f));
        final float n = (float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw);
        final double n2 = -MathHelper.sin(n) * double1;
        final double n3 = MathHelper.cos(n) * double1;
        final Object o = getMinecraft.player.isRiding() ? getMinecraft.player.getRidingEntity() : getMinecraft.player;
        if (o != null) {
            ((Entity)o).setPosition(((Entity)o).posX + n2, ((Entity)o).posY, ((Entity)o).posZ + n3);
            getMinecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(((Entity)o).posX + n2, ((Entity)o).posY, ((Entity)o).posZ + n3, true));
            getMinecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(((Entity)o).posX + n2, ((Entity)o).posY, ((Entity)o).posZ + n3, false));
        }
        this.setToggled(false);
    }
    
    public VClip() {
        super("VClip", "VClip", Category.PLAYER);
        CatClient.instance.settingsManager.rSetting(new Setting("FwDistance", this, 1.0, -20.0, 255.0, true));
    }
}
