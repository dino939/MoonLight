//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;

public class MappingManager
{
    public static String fieldModelChanger;
    public static String funcOnUpdateWalkingPlayer;
    public static String fieldItemRenderer;
    public static String fieldRightClickDelayTimer;
    public static String fieldPosY;
    public static String fieldPosZ;
    public static String fieldHitVec;
    public static String fieldJumpTicks;
    public static String fieldCurrentGameType;
    public static String fieldSpeedInAir;
    public static String fieldPlayerController;
    public static String fieldRotationYaw1;
    public static String fieldIsImmuneToFire;
    public static String fieldRotationPitch1;
    public static String fieldSession;
    public static String fieldBlockHitDelay;
    public static String fieldPosX;
    public static String fieldIsRotating;
    public static String fieldTimer;
    public static String fieldTickLength;
    public static String funcGetPlayerInfo;
    public static String fieldRenderItem;
    public static String fieldTicksSinceLastSwing;
    public static String fieldConnection;
    public static String fieldRotationYaw;
    public static String fieldPlayerTextures;
    public static String fieldIsHittingBlock;
    public static String fieldWindowId;
    public static String fieldMoving;
    public static String fieldItemColors;
    public static String fieldIsInWeb;
    public static String fieldRotationPitch;
    public static String fieldCurrentBlockDamageMP;
    public static String fieldOnGround;
    public static String fieldFire;
    
    static {
        MappingManager.fieldSession = (isInstanceNotNull() ? "session" : "session");
        MappingManager.fieldPosX = (isInstanceNotNull() ? "x" : "x");
        MappingManager.fieldPosY = (isInstanceNotNull() ? "y" : "y");
        MappingManager.fieldPosZ = (isInstanceNotNull() ? "z" : "z");
        MappingManager.fieldMoving = (isInstanceNotNull() ? "moving" : "moving");
        MappingManager.fieldRotationYaw = (isInstanceNotNull() ? "yaw" : "yaw");
        MappingManager.fieldRotationPitch = (isInstanceNotNull() ? "pitch" : "pitch");
        MappingManager.fieldOnGround = (isInstanceNotNull() ? "onGround" : "onGround");
        MappingManager.fieldRotationYaw1 = (isInstanceNotNull() ? "yaw" : "yaw");
        MappingManager.fieldRotationPitch1 = (isInstanceNotNull() ? "pitch" : "pitch");
        MappingManager.fieldIsRotating = (isInstanceNotNull() ? "rotating" : "rotating");
        MappingManager.fieldRightClickDelayTimer = (isInstanceNotNull() ? "rightClickDelayTimer" : "rightClickDelayTimer");
        MappingManager.funcGetPlayerInfo = (isInstanceNotNull() ? "getPlayerInfo" : "getPlayerInfo");
        MappingManager.fieldPlayerTextures = (isInstanceNotNull() ? "playerTextures" : "playerTextures");
        MappingManager.fieldCurrentGameType = (isInstanceNotNull() ? "currentGameType" : "currentGameType");
        MappingManager.fieldConnection = (isInstanceNotNull() ? "connection" : "connection");
        MappingManager.fieldBlockHitDelay = (isInstanceNotNull() ? "blockHitDelay" : "blockHitDelay");
        MappingManager.fieldIsInWeb = (isInstanceNotNull() ? "isInWeb" : "isInWeb");
        MappingManager.fieldCurrentBlockDamageMP = (isInstanceNotNull() ? "curBlockDamageMP" : "curBlockDamageMP");
        MappingManager.fieldIsHittingBlock = (isInstanceNotNull() ? "isHittingBlock" : "isHittingBlock");
        MappingManager.funcOnUpdateWalkingPlayer = (isInstanceNotNull() ? "onUpdateWalkingPlayer" : "onUpdateWalkingPlayer");
        MappingManager.fieldFire = (isInstanceNotNull() ? "fire" : "fire");
        MappingManager.fieldIsImmuneToFire = (isInstanceNotNull() ? "isImmuneToFire" : "isImmuneToFire");
        MappingManager.fieldHitVec = (isInstanceNotNull() ? "hitVec" : "hitVec");
        MappingManager.fieldPlayerController = (isInstanceNotNull() ? "playerController" : "playerController");
        MappingManager.fieldTimer = (isInstanceNotNull() ? "timer" : "timer");
        MappingManager.fieldTickLength = (isInstanceNotNull() ? "tickLength" : "tickLength");
        MappingManager.fieldWindowId = (isInstanceNotNull() ? "windowId" : "windowId");
        MappingManager.fieldTicksSinceLastSwing = (isInstanceNotNull() ? "ticksSinceLastSwing" : "ticksSinceLastSwing");
        MappingManager.fieldItemColors = (isInstanceNotNull() ? "itemColors" : "itemColors");
        MappingManager.fieldModelChanger = (isInstanceNotNull() ? "modelManager" : "modelManager");
        MappingManager.fieldRenderItem = (isInstanceNotNull() ? "renderItem" : "renderItem");
        MappingManager.fieldItemRenderer = (isInstanceNotNull() ? "itemRenderer" : "itemRenderer");
        MappingManager.fieldJumpTicks = (isInstanceNotNull() ? "jumpTicks" : "jumpTicks");
        MappingManager.fieldSpeedInAir = (isInstanceNotNull() ? "speedInAir" : "speedInAir");
    }
    
    public static boolean isInstanceNotNull() {
        try {
            return Minecraft.class.getDeclaredField("instance") != null;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
