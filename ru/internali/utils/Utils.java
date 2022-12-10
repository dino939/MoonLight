//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.entity.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import java.awt.datatransfer.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import java.math.*;
import net.minecraft.inventory.*;

public class Utils
{
    private static final Random RANDOM;
    public static float[] rotationsToBlock;
    public static boolean lookChanged;
    
    private static float[] getDirectionToEntity(final Entity entity) {
        return new float[] { getYaw(entity) + Wrapper.INSTANCE.player().rotationYaw, getPitch(entity) + Wrapper.INSTANCE.player().rotationPitch };
    }
    
    public static EntityLivingBase getWorldEntityByName(final String s) {
        EntityLivingBase entityLivingBase = null;
        for (final EntityLivingBase next : getEntityList()) {
            if (next instanceof EntityLivingBase) {
                final EntityLivingBase entityLivingBase2 = next;
                if (!entityLivingBase2.getName().contains(s)) {
                    continue;
                }
                entityLivingBase = entityLivingBase2;
            }
        }
        return entityLivingBase;
    }
    
    public static void assistFaceEntity(final Entity entity, final float n, final float n2) {
        if (entity == null) {
            return;
        }
        final double x = entity.posX - Wrapper.INSTANCE.player().posX;
        final double y = entity.posZ - Wrapper.INSTANCE.player().posZ;
        double y2;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            y2 = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight());
        }
        else {
            y2 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight());
        }
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float n3 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n4 = (float)(-(Math.atan2(y2, x2) * 180.0 / 3.141592653589793));
        if (n > 0.0f) {
            Wrapper.INSTANCE.player().rotationYaw = updateRotation(Wrapper.INSTANCE.player().rotationYaw, n3, n / 4.0f);
        }
        if (n2 > 0.0f) {
            Wrapper.INSTANCE.player().rotationPitch = updateRotation(Wrapper.INSTANCE.player().rotationPitch, n4, n2 / 4.0f);
        }
    }
    
    static {
        Utils.rotationsToBlock = null;
        RANDOM = new Random();
    }
    
    public static boolean screenCheck() {
        return !(Wrapper.INSTANCE.mc().currentScreen instanceof GuiScreen);
    }
    
    public static List getEntityList() {
        return Wrapper.INSTANCE.world().getLoadedEntityList();
    }
    
    public static boolean checkEnemyColor(final EntityPlayer entityPlayer) {
        final int playerArmorColor = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(0));
        final int playerArmorColor2 = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(1));
        final int playerArmorColor3 = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(2));
        final int playerArmorColor4 = getPlayerArmorColor(entityPlayer, entityPlayer.inventory.armorItemInSlot(3));
        final int playerArmorColor5 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.inventory().armorItemInSlot(0));
        final int playerArmorColor6 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.inventory().armorItemInSlot(1));
        final int playerArmorColor7 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.inventory().armorItemInSlot(2));
        final int playerArmorColor8 = getPlayerArmorColor((EntityPlayer)Wrapper.INSTANCE.player(), Wrapper.INSTANCE.inventory().armorItemInSlot(3));
        return (playerArmorColor != playerArmorColor5 || playerArmorColor5 == -1 || playerArmorColor == 1) && (playerArmorColor2 != playerArmorColor6 || playerArmorColor6 == -1 || playerArmorColor2 == 1) && (playerArmorColor3 != playerArmorColor7 || playerArmorColor7 == -1 || playerArmorColor3 == 1) && (playerArmorColor4 != playerArmorColor8 || playerArmorColor8 == -1 || playerArmorColor4 == 1);
    }
    
    public static float[] getRotationsNeeded(final Entity entity) {
        if (entity == null) {
            return null;
        }
        final double x = entity.posX - Wrapper.INSTANCE.mc().player.posX;
        final double y = entity.posZ - Wrapper.INSTANCE.mc().player.posZ;
        double y2;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            y2 = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Wrapper.INSTANCE.mc().player.posY + Wrapper.INSTANCE.mc().player.getEyeHeight());
        }
        else {
            y2 = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (Wrapper.INSTANCE.mc().player.posY + Wrapper.INSTANCE.mc().player.getEyeHeight());
        }
        return new float[] { Wrapper.INSTANCE.mc().player.rotationYaw + MathHelper.wrapDegrees((float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f - Wrapper.INSTANCE.mc().player.rotationYaw), Wrapper.INSTANCE.mc().player.rotationPitch + MathHelper.wrapDegrees((float)(-(Math.atan2(y2, MathHelper.sqrt(x * x + y * y)) * 180.0 / 3.141592653589793)) - Wrapper.INSTANCE.mc().player.rotationPitch) };
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        Utils.rotationsToBlock = getNeededRotations(vec3d);
    }
    
    public static float[] getNeededRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { Wrapper.INSTANCE.player().rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - Wrapper.INSTANCE.player().rotationYaw), Wrapper.INSTANCE.player().rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - Wrapper.INSTANCE.player().rotationPitch) };
    }
    
    public static boolean checkEnemyNameColor(final EntityLivingBase entityLivingBase) {
        entityLivingBase.getDisplayName().getFormattedText();
        return !getEntityNameColor((EntityLivingBase)Wrapper.INSTANCE.player()).equals(getEntityNameColor(entityLivingBase));
    }
    
    public static void copy(final String data) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(data), null);
    }
    
    public static int getDistanceFromMouse(final EntityLivingBase entityLivingBase) {
        final float[] rotationsNeeded = getRotationsNeeded((Entity)entityLivingBase);
        if (rotationsNeeded != null) {
            final float n = Wrapper.INSTANCE.player().rotationYaw - rotationsNeeded[0];
            final float n2 = Wrapper.INSTANCE.player().rotationPitch - rotationsNeeded[1];
            return (int)MathHelper.sqrt(n * n + n2 * n2 * 2.0f);
        }
        return -1;
    }
    
    public static float getYaw(final Entity entity) {
        final double y = entity.posX - Wrapper.INSTANCE.player().posX;
        final double n = entity.posY - Wrapper.INSTANCE.player().posY;
        return (float)(-(Math.atan2(y, entity.posZ - Wrapper.INSTANCE.player().posZ) * 57.29577951308232));
    }
    
    public static Vec3d getRandomCenter(final AxisAlignedBB axisAlignedBB) {
        return new Vec3d(axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * 0.8 * Math.random(), axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * Math.random() + 0.1 * Math.random(), axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * 0.8 * Math.random());
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight(), Wrapper.INSTANCE.player().posZ);
    }
    
    public static float[] getSmoothNeededRotations(final Vec3d vec3d, final float n, final float n2) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { updateRotation(Wrapper.INSTANCE.player().rotationYaw, (float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f, n / 4.0f), updateRotation(Wrapper.INSTANCE.player().rotationPitch, (float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))), n2 / 4.0f) };
    }
    
    public static void attack(final Entity entity) {
        Wrapper.INSTANCE.controller().attackEntity((EntityPlayer)Wrapper.INSTANCE.player(), entity);
    }
    
    public static List getPlayersList() {
        return Wrapper.INSTANCE.world().playerEntities;
    }
    
    public static void removeEffect(final int n) {
        Wrapper.INSTANCE.player().removePotionEffect(Potion.getPotionById(n));
    }
    
    public static boolean isBlockEdge(final EntityLivingBase entityLivingBase) {
        return Wrapper.INSTANCE.world().getCollisionBoxes((Entity)entityLivingBase, entityLivingBase.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(0.001, 0.0, 0.001)).isEmpty() && entityLivingBase.onGround;
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer && getPlayerName((EntityPlayer)entity).equals(getPlayerName((EntityPlayer)Wrapper.INSTANCE.player()));
    }
    
    public static int getPlayerArmorColor(final EntityPlayer entityPlayer, final ItemStack itemStack) {
        if (entityPlayer == null || itemStack == null || itemStack.getItem() == null || !(itemStack.getItem() instanceof ItemArmor)) {
            return -1;
        }
        final ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
        if (itemArmor == null || itemArmor.getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER) {
            return -1;
        }
        return itemArmor.getColor(itemStack);
    }
    
    public static void swingMainHand() {
        Wrapper.INSTANCE.player().swingArm(EnumHand.MAIN_HAND);
    }
    
    public static double[] teleportToPosition(final double[] array, final double[] array2, final double n, final double n2, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (Wrapper.INSTANCE.player().isSneaking()) {
            b3 = true;
        }
        double n3 = array[0];
        double n4 = array[1];
        double n5 = array[2];
        final double n6 = array2[0];
        final double n7 = array2[1];
        final double n8 = array2[2];
        double n9 = Math.abs(n3 - n4) + Math.abs(n4 - n7) + Math.abs(n5 - n8);
        int n10 = 0;
        while (n9 > n2) {
            n9 = Math.abs(n3 - n6) + Math.abs(n4 - n7) + Math.abs(n5 - n8);
            if (n10 > 120) {
                break;
            }
            final double n11 = (b && (n10 & 0x1) == 0x0) ? (n + 0.15) : n;
            final double n12 = n3 - n6;
            final double n13 = n4 - n7;
            final double n14 = n5 - n8;
            if (n12 < 0.0) {
                if (Math.abs(n12) > n11) {
                    n3 += n11;
                }
                else {
                    n3 += Math.abs(n12);
                }
            }
            if (n12 > 0.0) {
                if (Math.abs(n12) > n11) {
                    n3 -= n11;
                }
                else {
                    n3 -= Math.abs(n12);
                }
            }
            if (n13 < 0.0) {
                if (Math.abs(n13) > n11) {
                    n4 += n11;
                }
                else {
                    n4 += Math.abs(n13);
                }
            }
            if (n13 > 0.0) {
                if (Math.abs(n13) > n11) {
                    n4 -= n11;
                }
                else {
                    n4 -= Math.abs(n13);
                }
            }
            if (n14 < 0.0) {
                if (Math.abs(n14) > n11) {
                    n5 += n11;
                }
                else {
                    n5 += Math.abs(n14);
                }
            }
            if (n14 > 0.0) {
                if (Math.abs(n14) > n11) {
                    n5 -= n11;
                }
                else {
                    n5 -= Math.abs(n14);
                }
            }
            if (b3) {
                Wrapper.INSTANCE.sendPacket((Packet)new CPacketEntityAction((Entity)Wrapper.INSTANCE.player(), CPacketEntityAction.Action.STOP_SNEAKING));
            }
            Wrapper.INSTANCE.mc().getConnection().getNetworkManager().sendPacket((Packet)new CPacketPlayer.Position(n3, n4, n5, b2));
            ++n10;
        }
        if (b3) {
            Wrapper.INSTANCE.sendPacket((Packet)new CPacketEntityAction((Entity)Wrapper.INSTANCE.player(), CPacketEntityAction.Action.START_SNEAKING));
        }
        return new double[] { n3, n4, n5 };
    }
    
    public static void selfDamage(final double n) {
        if (!Wrapper.INSTANCE.player().onGround) {
            return;
        }
        for (int n2 = 0; n2 <= 64.0; ++n2) {
            Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayer.Position(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + n, Wrapper.INSTANCE.player().posZ, false));
            Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayer.Position(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY, Wrapper.INSTANCE.player().posZ, n2 == 64.0));
        }
        final EntityPlayerSP player = Wrapper.INSTANCE.player();
        player.motionX *= 0.2;
        final EntityPlayerSP player2 = Wrapper.INSTANCE.player();
        player2.motionZ *= 0.2;
        swingMainHand();
    }
    
    public static String getEntityNameColor(final EntityLivingBase entityLivingBase) {
        final String getFormattedText = entityLivingBase.getDisplayName().getFormattedText();
        if (getFormattedText.contains("§")) {
            if (getFormattedText.contains("§1")) {
                return "§1";
            }
            if (getFormattedText.contains("§2")) {
                return "§2";
            }
            if (getFormattedText.contains("§3")) {
                return "§3";
            }
            if (getFormattedText.contains("§4")) {
                return "§4";
            }
            if (getFormattedText.contains("§5")) {
                return "§5";
            }
            if (getFormattedText.contains("§6")) {
                return "§6";
            }
            if (getFormattedText.contains("§7")) {
                return "§7";
            }
            if (getFormattedText.contains("§8")) {
                return "§8";
            }
            if (getFormattedText.contains("§9")) {
                return "§9";
            }
            if (getFormattedText.contains("§0")) {
                return "§0";
            }
            if (getFormattedText.contains("§e")) {
                return "§e";
            }
            if (getFormattedText.contains("§d")) {
                return "§d";
            }
            if (getFormattedText.contains("§a")) {
                return "§a";
            }
            if (getFormattedText.contains("§b")) {
                return "§b";
            }
            if (getFormattedText.contains("§c")) {
                return "§c";
            }
            if (getFormattedText.contains("§f")) {
                return "§f";
            }
        }
        return "null";
    }
    
    public static void clearEffects() {
        final Iterator<PotionEffect> iterator = Wrapper.INSTANCE.player().getActivePotionEffects().iterator();
        while (iterator.hasNext()) {
            Wrapper.INSTANCE.player().removePotionEffect(iterator.next().getPotion());
        }
    }
    
    public static int random(final int n, final int n2) {
        return Utils.RANDOM.nextInt(n2 - n) + n;
    }
    
    public static float[] getDirectionToBlock(final int n, final int n2, final int n3, final EnumFacing enumFacing) {
        final EntityEgg entityEgg = new EntityEgg((World)Wrapper.INSTANCE.world());
        entityEgg.posX = n + 0.5;
        entityEgg.posY = n2 + 0.5;
        entityEgg.posZ = n3 + 0.5;
        final EntityEgg entityEgg2 = entityEgg;
        entityEgg2.posX += enumFacing.getDirectionVec().getX() * 0.25;
        final EntityEgg entityEgg3 = entityEgg;
        entityEgg3.posY += enumFacing.getDirectionVec().getY() * 0.25;
        final EntityEgg entityEgg4 = entityEgg;
        entityEgg4.posZ += enumFacing.getDirectionVec().getZ() * 0.25;
        return getDirectionToEntity((Entity)entityEgg);
    }
    
    public static void addEffect(final int n, final int n2, final int n3) {
        Wrapper.INSTANCE.player().addPotionEffect(new PotionEffect(Potion.getPotionById(n), n2, n3));
    }
    
    public static boolean nullCheck() {
        return Wrapper.INSTANCE.player() == null || Wrapper.INSTANCE.world() == null;
    }
    
    public static double round(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static String getPlayerName(final EntityPlayer entityPlayer) {
        return (entityPlayer.getGameProfile() != null) ? entityPlayer.getGameProfile().getName() : "null";
    }
    
    public static float getPitch(final Entity entity) {
        final double n = entity.posX - Wrapper.INSTANCE.player().posX;
        final double n2 = entity.posY - Wrapper.INSTANCE.player().posY;
        final double n3 = entity.posZ - Wrapper.INSTANCE.player().posZ;
        return (float)(-(Math.asin(n2 / Wrapper.INSTANCE.player().getDistance(entity)) * 57.29577951308232));
    }
    
    public static boolean isNullOrEmptyStack(final ItemStack itemStack) {
        return itemStack == null || itemStack.isEmpty();
    }
    
    public static float updateRotation(final float n, final float n2, final float n3) {
        float wrapDegrees = MathHelper.wrapDegrees(n2 - n);
        if (wrapDegrees > n3) {
            wrapDegrees = n3;
        }
        if (wrapDegrees < -n3) {
            wrapDegrees = -n3;
        }
        return n + wrapDegrees;
    }
    
    public static float getDirection() {
        float rotationYaw = Wrapper.INSTANCE.player().rotationYaw;
        if (Wrapper.INSTANCE.player().moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Wrapper.INSTANCE.player().moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (Wrapper.INSTANCE.player().moveForward > 0.0f) {
            n = 0.5f;
        }
        if (Wrapper.INSTANCE.player().moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Wrapper.INSTANCE.player().moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public static boolean isMoving(final Entity entity) {
        return entity.motionX != 0.0 && entity.motionZ != 0.0 && (entity.motionY != 0.0 || entity.motionY > 0.0);
    }
    
    public static void windowClick(final int n, final int n2, final int n3, final ClickType clickType) {
        Wrapper.INSTANCE.controller().windowClick(n, n2, n3, clickType, (EntityPlayer)Wrapper.INSTANCE.player());
    }
    
    public static void faceEntity(final EntityLivingBase entityLivingBase) {
        if (entityLivingBase == null) {
            return;
        }
        final double x = entityLivingBase.posX - Wrapper.INSTANCE.player().posX;
        final double n = entityLivingBase.posY - Wrapper.INSTANCE.player().posY;
        final double y = entityLivingBase.posZ - Wrapper.INSTANCE.player().posZ;
        final double y2 = Wrapper.INSTANCE.player().posY + Wrapper.INSTANCE.player().getEyeHeight() - (entityLivingBase.posY + entityLivingBase.getEyeHeight());
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float rotationYaw = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float rotationPitch = (float)(-(Math.atan2(y2, x2) * 180.0 / 3.141592653589793));
        Wrapper.INSTANCE.player().rotationYaw = rotationYaw;
        Wrapper.INSTANCE.player().rotationPitch = rotationPitch;
    }
}
