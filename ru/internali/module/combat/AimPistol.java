//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import ru.internali.*;
import net.minecraft.util.math.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.utils.friend.*;
import net.minecraft.util.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.inventory.*;
import ru.internali.utils.*;
import net.minecraft.item.*;

public class AimPistol extends Module
{
    public static List AimPredict;
    public static List Priority;
    private boolean up;
    public TimerUtils timer;
    public static List Fover;
    public static List Rotare;
    public static List Sort;
    private float[] old_pred;
    public static Entity target;
    public float[] facing;
    public double height;
    public double width;
    private Entity focusTarget;
    public static AimPistol INSTANCE;
    
    public float updateRotation(final float n, final float n2) {
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName(this, "Smooth").getValDouble();
        float wrapDegrees = MathHelper.wrapDegrees(n2 - n);
        if (wrapDegrees > n3) {
            wrapDegrees = n3;
        }
        if (wrapDegrees < -n3) {
            wrapDegrees = -n3;
        }
        return n + wrapDegrees;
    }
    
    public AimPistol() {
        super("AimPistol", "aim to players", Category.COMBAT);
        this.timer = new TimerUtils();
        CatClient.instance.settingsManager.rSetting(new Setting("Range", this, 300.0, 0.0, 300.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Predict", this, 3.5, 0.0, 9.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Walls", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("AutoShoot", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("AutoScope", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Fov", this, 40.0, 1.0, 360.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("players", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Nocked", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("invis", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("AimOnClick", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("ShootDeley", this, 200.0, 0.0, 500.0, true));
        AimPistol.Priority.add("Angle");
        AimPistol.Priority.add("HArmor");
        AimPistol.Priority.add("LArmor");
        AimPistol.Priority.add("Health");
        AimPistol.Priority.add("Distance");
        CatClient.instance.settingsManager.rSetting(new Setting("Priority", this, "Angle", (ArrayList)AimPistol.Priority));
        CatClient.instance.settingsManager.rSetting(new Setting("Smooth", this, 1.0, 1.0, 180.0, true));
        AimPistol.Fover.add("Krug");
        AimPistol.Fover.add("Kvadrat");
        CatClient.instance.settingsManager.rSetting(new Setting("FovType", this, "Krug", (ArrayList)AimPistol.Fover));
        CatClient.instance.settingsManager.rSetting(new Setting("FovRender", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("DYNAMIC", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Targethud", this, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Xpos", this, 1.0, 0.0, 650.0, true));
        CatClient.instance.settingsManager.rSetting(new Setting("Ypos", this, 1.0, 0.0, 500.0, true));
    }
    
    @SubscribeEvent
    public void fovv(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "FovType").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Fov").getValDouble();
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "FovRender").getValBoolean();
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final ScaledResolution scaledResolution = new ScaledResolution(AimPistol.mc);
            if (valBoolean) {
                if (Objects.equals(valString, "Krug")) {
                    RenderUtil.drawCircle222((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), (float)(n * 3.5), Color.white.getRGB(), 360);
                }
                else if (Objects.equals(valString, "Kvadrat")) {
                    final int n2 = 255;
                    final int n3 = 255;
                    final int n4 = 255;
                    final int n5 = 255;
                    final double n6 = n * 3.5;
                    final double n7 = 60.0;
                    final double n8 = 1.75;
                    final ScaledResolution scaledResolution2 = new ScaledResolution(AimPistol.mc);
                    RenderUtil.rectangleBordered(scaledResolution2.getScaledWidth() / 2 - n6 - n8 - (this.isMoving() ? 2 : 0) + 0.15, scaledResolution2.getScaledHeight() / 2 - n7, scaledResolution2.getScaledWidth() / 2 - n6 - (this.isMoving() ? 2 : 0) + 0.15, scaledResolution2.getScaledHeight() / 2 + 1.0f + n7, 0.5, Colors.getColor(n2, n3, n4, n5), new Color(0, 0, 0, n5).getRGB());
                    RenderUtil.rectangleBordered(scaledResolution2.getScaledWidth() / 2 + 1 + n6 + (this.isMoving() ? 2 : 0), scaledResolution2.getScaledHeight() / 2 - n7, scaledResolution2.getScaledWidth() / 2 + n8 + n6 + 1.0 + (this.isMoving() ? 2 : 0), scaledResolution2.getScaledHeight() / 2 + 1.0f + n7, 0.5, Colors.getColor(n2, n3, n4, n5), new Color(0, 0, 0, n5).getRGB());
                }
            }
        }
    }
    
    public static boolean canSeeEntityAtFov(final Entity entity, final float n) {
        return angleDifference((float)(Math.toDegrees(Math.atan2(entity.posZ - AimPistol.mc.player.posZ, entity.posX - AimPistol.mc.player.posX)) - 90.0), AimPistol.mc.player.rotationYaw) <= n;
    }
    
    public static void clickMouse(final int n) {
        try {
            final Robot robot = new Robot();
            if (n == 0) {
                robot.mousePress(16);
                robot.mouseRelease(16);
            }
            else {
                if (n != 1) {
                    return;
                }
                robot.mousePress(4096);
                robot.mouseRelease(4096);
            }
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
    
    private static int lambda$getSortEntities$1(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return (int)(entityLivingBase.getHealth() - entityLivingBase2.getHealth());
    }
    
    public float[] faceHead(final float n, final float n2, final float n3, final float n4, final float n5) {
        final double x = n - Minecraft.getMinecraft().player.posX;
        final double y = n3 - Minecraft.getMinecraft().player.posZ;
        final double n6 = n2 + 1.6200000047683716 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        final double x2 = MathHelper.sqrt(x * x + y * y);
        final float n7 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n8 = (float)(-(Math.atan2(n6 - 0.07, x2) * 180.0 / 3.141592653589793));
        final float n9 = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float n10 = n9 * n9 * n9 * 1.2f;
        final float updateRotation = this.updateRotation(Minecraft.getMinecraft().player.rotationPitch, n8);
        final float updateRotation2 = this.updateRotation(Minecraft.getMinecraft().player.rotationYaw, n7);
        return new float[] { updateRotation2 - updateRotation2 % n10, updateRotation - updateRotation % n10 };
    }
    
    public static double angleDifference(final double n, final double n2) {
        float n3 = (float)(Math.abs(n - n2) % 360.0);
        if (n3 > 180.0f) {
            n3 = 360.0f - n3;
        }
        return n3;
    }
    
    public boolean canAssist(final EntityLivingBase entityLivingBase) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Walls").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "players").getValBoolean();
        final boolean valBoolean3 = CatClient.instance.settingsManager.getSettingByName(this, "invis").getValBoolean();
        final boolean valBoolean4 = CatClient.instance.settingsManager.getSettingByName(this, "Nocked").getValBoolean();
        if (entityLivingBase instanceof EntityPlayer && !valBoolean2) {
            return false;
        }
        if (entityLivingBase.isPlayerSleeping() && !valBoolean4) {
            return false;
        }
        if (entityLivingBase.isInvisible() && !valBoolean3) {
            return false;
        }
        if (!entityLivingBase.canEntityBeSeen((Entity)AimPistol.mc.player)) {
            return valBoolean;
        }
        return entityLivingBase != AimPistol.mc.player;
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "AutoShoot").getValBoolean();
        final boolean valBoolean2 = CatClient.instance.settingsManager.getSettingByName(this, "AutoScope").getValBoolean();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "ShootDeley").getValDouble();
        if (CatClient.instance.settingsManager.getSettingByName("AimOnClick").getValBoolean() && !AimPistol.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        AimPistol.target = null;
        AimPistol.target = this.getSortEntities();
        if (AimPistol.target != null && !FriendManager.friendsList.contains(AimPistol.target.getName()) && canSeeEntityAtFov(AimPistol.target, (float)(int)CatClient.instance.settingsManager.getSettingByName(this, "FOV").getValDouble())) {
            if (AimPistol.target != null) {
                this.facing = this.getPredict(AimPistol.target);
                this.facing = this.faceHead(this.facing[0], (float)AimPistol.target.posY, this.facing[1], 360.0f, 360.0f);
                AimPistol.mc.player.rotationYaw = this.facing[0];
                AimPistol.mc.player.rotationPitch = this.facing[1];
                if (valBoolean && AimPistol.mc.player.canEntityBeSeen(AimPistol.target)) {
                    if (this.timer.isDelay((long)n)) {
                        if (valBoolean2) {
                            clickMouse(1);
                        }
                        AimPistol.mc.player.swingArm(EnumHand.MAIN_HAND);
                        if (valBoolean2) {
                            clickMouse(1);
                        }
                    }
                    this.timer.setLastMS();
                }
            }
            else {
                this.timer.setLastMS();
            }
        }
    }
    
    public boolean isMoving() {
        return false;
    }
    
    public Entity getSortEntities() {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "range").getValDouble();
        final ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        for (final EntityLivingBase entityLivingBase : AimPistol.mc.world.playerEntities) {
            if (entityLivingBase != null && AimPistol.mc.player.getDistance((Entity)entityLivingBase) < n && this.canAssist(entityLivingBase)) {
                if (entityLivingBase.getHealth() > 0.0f) {
                    list.add(entityLivingBase);
                }
                else {
                    list.remove(entityLivingBase);
                }
            }
        }
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Priority").getValString();
        if (valString.equalsIgnoreCase("Angle")) {
            list.sort(AimPistol::lambda$getSortEntities$0);
        }
        else if (valString.equalsIgnoreCase("HArmor")) {
            list.sort(Comparator.comparing((Function<? super Object, ? extends Comparable>)EntityLivingBase::getTotalArmorValue).reversed());
        }
        else if (valString.equalsIgnoreCase("LArmor")) {
            list.sort(Comparator.comparing((Function<? super Object, ? extends Comparable>)EntityLivingBase::getTotalArmorValue));
        }
        else if (valString.equalsIgnoreCase("Health")) {
            list.sort(AimPistol::lambda$getSortEntities$1);
        }
        else if (valString.equalsIgnoreCase("Distance")) {
            final ArrayList<EntityLivingBase> list2 = list;
            final EntityPlayerSP player = AimPistol.mc.player;
            player.getClass();
            list2.sort(Comparator.comparingDouble((ToDoubleFunction<? super Object>)player::getDistance));
        }
        if (list.isEmpty()) {
            return null;
        }
        return (Entity)list.get(0);
    }
    
    private static int lambda$getSortEntities$0(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return (int)(Math.abs(RotationHelper.getAngleEntity((Entity)entityLivingBase) - AimPistol.mc.player.rotationYaw) - Math.abs(RotationHelper.getAngleEntity((Entity)entityLivingBase2) - AimPistol.mc.player.rotationYaw));
    }
    
    @SubscribeEvent
    public void targethud(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Targethud").getValBoolean();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Xpos").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "Ypos").getValDouble();
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (!valBoolean) {
                return;
            }
            if (AimPistol.target == null) {
                return;
            }
            final Color color = new Color(30, 30, 30, 150);
            RenderUtils.drawShadowRect(n, n2, n + 150.0f, n2 + 30.0f, 3);
            try {
                GuiInventory.drawEntityOnScreen((int)(n + 8.0f), (int)(n2 + 30.0f), 15, 0.0f, 0.0f, (EntityLivingBase)AimPistol.target);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            RenderUtil.drawSmoothRect(n + 29.0f, n2 + 1.0f, n + 29.0f + AimPistol.mc.fontRenderer.getStringWidth(AimPistol.target.getName()), n2 + AimPistol.mc.fontRenderer.FONT_HEIGHT + 4.0f, color.getRGB());
            AimPistol.mc.fontRenderer.drawString(AimPistol.target.getName(), (int)(n + 30.0f), (int)(n2 + 3.0f), Color.white.getRGB());
            final int n3 = (int)(((EntityPlayer)AimPistol.target).getHealth() / ((EntityPlayer)AimPistol.target).getMaxHealth() * 100.0f);
            RenderUtil.drawCircle228(n + 135.0f, n2 + 15.0f, 14.0f, ColorUtils.getHealthColor((EntityLivingBase)AimPistol.target).getRGB(), (int)(((EntityPlayer)AimPistol.target).getHealth() / ((EntityPlayer)AimPistol.target).getMaxHealth() * 360.0f));
            AimPistol.mc.fontRenderer.drawString(String.valueOf(new StringBuilder().append(n3).append("")), (int)(n + 135.0f - AimPistol.mc.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(n3).append(""))) / 2), (int)(n2 + 15.0f - AimPistol.mc.fontRenderer.FONT_HEIGHT / 2), ColorUtils.getHealthColor((EntityLivingBase)AimPistol.target).getRGB());
            int n4 = (int)(n + 30.0f);
            RenderUtil.renderItem(((EntityPlayer)AimPistol.target).getHeldItem(EnumHand.MAIN_HAND), n4, (int)n2 + 14);
            n4 += 16;
            final Iterator<ItemStack> iterator = (Iterator<ItemStack>)((EntityPlayer)AimPistol.target).getArmorInventoryList().iterator();
            while (iterator.hasNext()) {
                RenderUtil.renderItem(iterator.next(), n4, (int)n2 + 14);
                n4 += 16;
            }
        }
    }
    
    static {
        AimPistol.Sort = new ArrayList();
        AimPistol.Priority = new ArrayList();
        AimPistol.AimPredict = new ArrayList();
        AimPistol.Fover = new ArrayList();
        AimPistol.Rotare = new ArrayList();
    }
    
    public float[] getPredict(final Entity entity) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Predict").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        final double n3 = entity.posX - entity.lastTickPosX;
        final double n4 = entity.posZ - entity.lastTickPosZ;
        final double n5 = entity.posY - entity.lastTickPosY;
        final float n6 = n + AimPistol.mc.player.getDistance(entity) / n2;
        final double n7 = entity.posX + n3 * n6;
        final double n8 = entity.posZ + n4 * n6;
        double posY = entity.posY + n5 * n6;
        if (n5 * n6 > 1.1) {
            posY = entity.posY;
        }
        return new float[] { (float)n7, (float)n8, (float)posY };
    }
}
