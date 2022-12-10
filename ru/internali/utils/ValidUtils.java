//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class ValidUtils
{
    public static boolean isLowHealth(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return entityLivingBase2 == null || entityLivingBase.getHealth() < entityLivingBase2.getHealth();
    }
    
    public static boolean isInAttackRange(final EntityLivingBase entityLivingBase, final float n) {
        return entityLivingBase.getDistance((Entity)Wrapper.INSTANCE.player()) <= n;
    }
    
    public static boolean isInAttackFOV(final EntityLivingBase entityLivingBase, final int n) {
        return Utils.getDistanceFromMouse(entityLivingBase) <= n;
    }
    
    public static boolean isValidEntity(final EntityLivingBase entityLivingBase) {
        return !(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityLiving);
    }
    
    public static boolean isClosest(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return entityLivingBase2 == null || Wrapper.INSTANCE.player().getDistance((Entity)entityLivingBase) < Wrapper.INSTANCE.player().getDistance((Entity)entityLivingBase2);
    }
}
