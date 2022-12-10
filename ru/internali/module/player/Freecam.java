//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.player;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import ru.internali.utils.*;
import net.minecraft.network.play.client.*;

public class Freecam extends Module
{
    private int lastThirdPerson;
    private final Minecraft mc;
    public static final Freecam INSTANCE;
    private boolean activeThisSession;
    public EntityOtherPlayerMP camera;
    
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.activeThisSession = true;
        this.mc.renderGlobal.loadRenderers();
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent inputUpdateEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "horizontalSpeed").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "verticalSpeed").getValDouble();
        if (this.camera == null) {
            return;
        }
        final MovementInput movementInput = inputUpdateEvent.getMovementInput();
        final Vec3d rotateYaw = new Vec3d((double)((movementInput.leftKeyDown ? n : 0.0f) - (movementInput.rightKeyDown ? n : 0.0f)), (double)((movementInput.jump ? n2 : 0.0f) - (movementInput.sneak ? n2 : 0.0f)), (double)((movementInput.forwardKeyDown ? n : 0.0f) - (movementInput.backKeyDown ? n : 0.0f))).rotateYaw((float)(-Math.toRadians(this.camera.rotationYaw)));
        this.camera.setPositionAndRotationDirect(this.camera.posX + rotateYaw.x, this.camera.posY + rotateYaw.y, this.camera.posZ + rotateYaw.z, this.camera.rotationYaw, this.camera.rotationPitch, 3, false);
        if (movementInput instanceof MovementInputFromOptions) {
            this.mc.player.moveVertical = 0.0f;
            this.mc.player.moveStrafing = 0.0f;
            this.mc.player.moveForward = 0.0f;
            this.mc.player.setJumping(false);
        }
    }
    
    static {
        INSTANCE = new Freecam();
    }
    
    public Freecam() {
        super("Freecam", "Freecam", Category.PLAYER);
        this.mc = Minecraft.getMinecraft();
        this.activeThisSession = false;
        CatClient.instance.settingsManager.rSetting(new Setting("verticalspeed", this, 3.5, 1.0, 12.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("horizontalspeed", this, 3.5, 1.0, 12.0, false));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (this.mc.world == null || !this.mc.world.isRemote) {
            this.camera = null;
            return;
        }
        if (this.camera == null) {
            this.lastThirdPerson = this.mc.gameSettings.thirdPersonView;
            this.camera = new EntityOtherPlayerMP((World)this.mc.world, this.mc.getSession().getProfile());
            this.mc.world.addEntityToWorld(333393333, (Entity)this.camera);
            this.camera.copyLocationAndAnglesFrom((Entity)this.mc.player);
            this.mc.setRenderViewEntity((Entity)this.camera);
            this.camera.noClip = true;
        }
        this.mc.gameSettings.thirdPersonView = 0;
        this.camera.inventory = this.mc.player.inventory;
        this.camera.setHealth(this.mc.player.getHealth());
    }
    
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        if (this.mc.world != null && this.mc.world.isRemote) {
            this.mc.setRenderViewEntity((Entity)this.mc.player);
            if (this.activeThisSession) {
                this.mc.gameSettings.thirdPersonView = this.lastThirdPerson;
                this.mc.world.removeEntity((Entity)this.camera);
            }
        }
        this.camera = null;
        this.activeThisSession = false;
    }
    
    @SubscribeEvent
    public void onSendPacket(final SendPacketEvent sendPacketEvent) {
        if (sendPacketEvent.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)sendPacketEvent.getPacket()).getEntityFromWorld((World)this.mc.world) == this.mc.player) {
            sendPacketEvent.setCanceled(true);
        }
    }
}
