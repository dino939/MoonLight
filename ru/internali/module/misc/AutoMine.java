//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import ru.internali.utils.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class AutoMine extends Module
{
    private TimerUtils timer;
    
    public static float[] getNeededRotations(final Entity entity) {
        final double n = entity.posX - Minecraft.getMinecraft().player.posX;
        final double n2 = entity.posZ - Minecraft.getMinecraft().player.posZ;
        return new float[] { (float)(MathHelper.atan2(n2, n) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(MathHelper.atan2(entity.posY + entity.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY)), (double)MathHelper.sqrt(n * n + n2 * n2)) * 180.0 / 3.141592653589793)) };
    }
    
    public Entity getMetka() {
        for (final Entity entity : AutoMine.mc.world.loadedEntityList) {
            if (entity instanceof EntityArmorStand && AutoMine.mc.player.getDistance(entity) <= 3.3) {
                return entity;
            }
        }
        return null;
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final Entity metka = this.getMetka();
        if (metka == null) {
            return;
        }
        if (this.timer.hasReached(250.0f)) {
            final float[] neededRotations = getNeededRotations(metka);
            Rotation.setYaw(neededRotations[0]);
            Rotation.setPitch(neededRotations[1]);
            AutoMine.mc.playerController.attackEntity((EntityPlayer)AutoMine.mc.player, metka);
            AutoMine.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.timer.reset();
        }
    }
    
    public AutoMine() {
        super("AutoMine", "AutoMine", Category.OUTHER);
        this.timer = new TimerUtils();
    }
}
