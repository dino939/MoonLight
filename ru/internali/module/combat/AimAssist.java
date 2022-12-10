//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import ru.internali.*;
import net.minecraft.entity.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import ru.internali.utils.*;

public class AimAssist extends Module
{
    public static List Priority;
    public static List Pos;
    
    private static int lambda$getSortEntities$1(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return (int)(entityLivingBase.getHealth() - entityLivingBase2.getHealth());
    }
    
    public static EntityLivingBase getSortEntities() {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("Range").getValDouble();
        final String valString = CatClient.instance.settingsManager.getSettingByName("Priority").getValString();
        final ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        for (final Entity entity : AimAssist.mc.world.loadedEntityList) {
            final EntityLivingBase entityLivingBase;
            if (entity instanceof EntityLivingBase && AimAssist.mc.player.getDistance((Entity)(entityLivingBase = (EntityLivingBase)entity)) < n) {
                if (!canAssist(entityLivingBase)) {
                    continue;
                }
                if (entityLivingBase.getHealth() > 0.0f) {
                    list.add(entityLivingBase);
                }
                else {
                    list.remove(entityLivingBase);
                }
            }
        }
        if (valString.equalsIgnoreCase("Angle")) {
            list.sort(AimAssist::lambda$getSortEntities$0);
        }
        else if (valString.equalsIgnoreCase("HArmor")) {
            list.sort(Comparator.comparing((Function<? super Object, ? extends Comparable>)EntityLivingBase::getTotalArmorValue).reversed());
        }
        else if (valString.equalsIgnoreCase("LArmor")) {
            list.sort(Comparator.comparing((Function<? super EntityLivingBase, ? extends Comparable>)EntityLivingBase::getTotalArmorValue));
        }
        else if (valString.equalsIgnoreCase("Health")) {
            list.sort(AimAssist::lambda$getSortEntities$1);
        }
        else if (valString.equalsIgnoreCase("Distance")) {
            final ArrayList<EntityLivingBase> list2 = list;
            final EntityPlayerSP player = AimAssist.mc.player;
            player.getClass();
            list2.sort(Comparator.comparingDouble((ToDoubleFunction<? super Object>)player::getDistance));
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    private float[] getRotation(final Entity entity, final float n) {
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName("rotYawRandom").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName("rotPitchRandom").getValDouble();
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Pos").getValString();
        float n4 = 0.0f;
        if (valString.equalsIgnoreCase("Head")) {
            n4 = 0.0f;
        }
        else if (valString.equalsIgnoreCase("Chest")) {
            n4 = 0.5f;
        }
        else if (valString.equalsIgnoreCase("Leggings")) {
            n4 = 0.9f;
        }
        else if (valString.equalsIgnoreCase("Boots")) {
            n4 = 1.3f;
        }
        final double n5 = entity.posX + (entity.posX - entity.prevPosX) * n - AimAssist.mc.player.posX - AimAssist.mc.player.motionX * n;
        final double n6 = entity.posZ + (entity.posZ - entity.prevPosZ) * n - AimAssist.mc.player.posZ - AimAssist.mc.player.motionZ * n;
        return new float[] { AimAssist.mc.player.rotationYaw + GCDCalcHelper.getFixedRotation(MathHelper.wrapDegrees((float)(MathHelper.atan2(n6, n5) * 180.0 / 3.141592653589793 - 90.0) + MathematicHelper.randomizeFloat(-n2, n2) - AimAssist.mc.player.rotationYaw)), MathHelper.clamp(AimAssist.mc.player.rotationPitch + GCDCalcHelper.getFixedRotation(MathHelper.wrapDegrees((float)(-(MathHelper.atan2(entity.posY + entity.getEyeHeight() - (AimAssist.mc.player.posY + AimAssist.mc.player.getEyeHeight() + n4), (double)MathHelper.sqrt(n5 * n5 + n6 * n6)) * 180.0 / 3.141592653589793)) + MathematicHelper.randomizeFloat(-n3, n3) - AimAssist.mc.player.rotationPitch)), -90.0f, 90.0f) };
    }
    
    static {
        AimAssist.Pos = new ArrayList();
        AimAssist.Priority = new ArrayList();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent clientTickEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "click").getValBoolean();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("Range").getValDouble();
        final EntityLivingBase sortEntities = getSortEntities();
        if (sortEntities != null && AimAssist.mc.player.getDistance((Entity)sortEntities) <= n && sortEntities != AimAssist.mc.player) {
            final float[] rotationsForAssist = this.getRotationsForAssist(sortEntities);
            if (valBoolean && !AimAssist.mc.gameSettings.keyBindAttack.isKeyDown()) {
                return;
            }
            if (canAssist(sortEntities) && sortEntities.getHealth() > 0.0f) {
                AimAssist.mc.player.rotationYaw = rotationsForAssist[0];
                AimAssist.mc.player.rotationPitch = rotationsForAssist[1];
            }
        }
    }
    
    public AimAssist() {
        super("AimAssist", "aim to players", Category.COMBAT);
        CatClient.instance.settingsManager.rSetting(new Setting("Range", this, 300.0, 0.0, 300.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("predict", this, 3.5, 0.0, 15.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("mobs", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("players", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Nocked", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("invis", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("team", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Walls", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Fov", this, 40.0, 1.0, 360.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("click", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("rotYawRandom", this, 1.0, 0.0, 3.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("rotPitchRandom", this, 1.0, 0.0, 3.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("rotYawSpeed", this, 1.0, 0.1, 5.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("rotPitchSpeed", this, 1.0, 0.1, 5.0, false));
        AimAssist.Priority.add("Angle");
        AimAssist.Priority.add("HArmor");
        AimAssist.Priority.add("LArmor");
        AimAssist.Priority.add("Health");
        AimAssist.Priority.add("Distance");
        CatClient.instance.settingsManager.rSetting(new Setting("Priority", this, "Angle", (ArrayList)AimAssist.Priority));
        AimAssist.Pos.add("Head");
        AimAssist.Pos.add("Chest");
        AimAssist.Pos.add("Leggings");
        AimAssist.Pos.add("Boots");
        CatClient.instance.settingsManager.rSetting(new Setting("Pos", this, "Head", (ArrayList)AimAssist.Pos));
    }
    
    private static int lambda$getSortEntities$0(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return (int)(Math.abs(RotationHelper.getAngleEntity((Entity)entityLivingBase) - AimAssist.mc.player.rotationYaw) - Math.abs(RotationHelper.getAngleEntity((Entity)entityLivingBase2) - AimAssist.mc.player.rotationYaw));
    }
    
    public static double angleDifference(final double n, final double n2) {
        float n3 = (float)(Math.abs(n - n2) % 360.0);
        if (n3 > 180.0f) {
            n3 = 360.0f - n3;
        }
        return n3;
    }
    
    public static boolean canAssist(final EntityLivingBase entityLivingBase) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName("mobs").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName("players").getValBoolean();
        final boolean valBoolean3 = CatClient.instance.settingsManager.getSettingByName("Nocked").getValBoolean();
        final boolean valBoolean4 = CatClient.instance.settingsManager.getSettingByName("invis").getValBoolean();
        final boolean valBoolean5 = CatClient.instance.settingsManager.getSettingByName("team").getValBoolean();
        final boolean valBoolean6 = CatClient.instance.settingsManager.getSettingByName("walls").getValBoolean();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("Fov").getValDouble();
        if (entityLivingBase instanceof EntityPlayer || entityLivingBase instanceof EntityAnimal || entityLivingBase instanceof EntityMob || entityLivingBase instanceof EntityWaterMob || entityLivingBase instanceof EntityVillager) {
            if (entityLivingBase instanceof EntityPlayer && !valBoolean2) {
                return false;
            }
            if (entityLivingBase instanceof EntityAnimal && !valBoolean) {
                return false;
            }
            if (entityLivingBase instanceof EntityWaterMob && !valBoolean) {
                return false;
            }
            if (entityLivingBase instanceof EntityMob && !valBoolean) {
                return false;
            }
            if (entityLivingBase instanceof EntityVillager && !valBoolean) {
                return false;
            }
        }
        if (valBoolean3 && entityLivingBase instanceof EntityPlayer && EntityHelper.checkArmor((Entity)entityLivingBase)) {
            return false;
        }
        if (entityLivingBase.isInvisible() && !valBoolean4) {
            return false;
        }
        if (valBoolean3 && EntityHelper.checkArmor((Entity)entityLivingBase)) {
            return false;
        }
        if (!canSeeEntityAtFov((Entity)entityLivingBase, n * 2.0f)) {
            return false;
        }
        if (valBoolean5 && EntityHelper.isTeamWithYou(entityLivingBase)) {
            return false;
        }
        if (!entityLivingBase.canEntityBeSeen((Entity)AimAssist.mc.player)) {
            return valBoolean6;
        }
        return entityLivingBase != AimAssist.mc.player;
    }
    
    public static boolean canSeeEntityAtFov(final Entity entity, final float n) {
        return angleDifference((float)(Math.toDegrees(Math.atan2(entity.posZ - AimAssist.mc.player.posZ, entity.posX - AimAssist.mc.player.posX)) - 90.0), AimAssist.mc.player.rotationYaw) <= n;
    }
    
    private float[] getRotationsForAssist(final EntityLivingBase entityLivingBase) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("rotYawRandom").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName("rotPitchRandom").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName("predict").getValDouble();
        return new float[] { RotationHelper.updateRotation(GCDCalcHelper.getFixedRotation(AimAssist.mc.player.rotationYaw + MathematicHelper.randomizeFloat(-n, n)), this.getRotation((Entity)entityLivingBase, n3)[0], (float)CatClient.instance.settingsManager.getSettingByName("rotYawSpeed").getValDouble() * 10.0f), RotationHelper.updateRotation(GCDCalcHelper.getFixedRotation(AimAssist.mc.player.rotationPitch + MathematicHelper.randomizeFloat(-n2, n2)), this.getRotation((Entity)entityLivingBase, n3)[1], (float)CatClient.instance.settingsManager.getSettingByName("rotPitchSpeed").getValDouble() * 10.0f) };
    }
}
