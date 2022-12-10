//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import ru.internali.*;
import java.util.*;
import java.util.function.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.util.math.*;
import ru.internali.utils.*;
import net.minecraft.util.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class AutoFarm extends Module
{
    ArrayList posgoto;
    TimerHelper timerHelper;
    
    private static Double lambdaonRender1(final BlockPos blockPos) {
        return EntityHelper.getDistanceOfEntityToBlock((Entity)AutoFarm.mc.player, blockPos);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("Radius").getValDouble();
        final BlockPos blockPos = (BlockPos)BlockHelper.getSphere(BlockHelper.getPlayerPos(), n, (int)n, true, false).stream().filter(BlockHelper::IsValidBlockPos).min(Comparator.comparing((Function<? super T, ? extends Comparable>)AutoFarm::lambdaonRender1)).orElse(null);
        if (blockPos != null) {
            RenderUtils.blockEspFrame(blockPos, 148.0f, 0.0f, 211.0f);
        }
    }
    
    private boolean IsValidBlockPos(final BlockPos blockPos) {
        return AutoFarm.mc.world.getBlockState(blockPos).getBlock() instanceof BlockStainedHardenedClay && AutoFarm.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
    }
    
    private static Double lambdaonUpdate0(final BlockPos blockPos) {
        return EntityHelper.getDistanceOfEntityToBlock((Entity)AutoFarm.mc.player, blockPos);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName("Radius").getValDouble();
        final BlockPos blockPos = (BlockPos)BlockHelper.getSphere(BlockHelper.getPlayerPos(), n, (int)n, true, false).stream().filter(BlockHelper::IsValidBlockPos).min(Comparator.comparing((Function<? super T, ? extends Comparable>)AutoFarm::lambdaonUpdate0)).orElse(null);
        if (blockPos == null) {
            if (AutoFarm.mc.player.ticksExisted % 100 == 0) {}
            if (AutoFarm.mc.player.ticksExisted % 25 == 0) {}
            if (AutoFarm.mc.player.collidedHorizontally && AutoFarm.mc.player.onGround) {
                AutoFarm.mc.player.jump();
            }
        }
        if (blockPos != null) {
            AutoFarm.mc.player.rotationYaw = RotationHelper.getRotationVector(new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.85f), (double)(blockPos.getZ() + 0.5f)), false, 2.0f, 2.0f, 360.0f)[0];
            AutoFarm.mc.player.setSprinting(false);
            if (AutoFarm.mc.player.collidedHorizontally && AutoFarm.mc.player.onGround) {
                AutoFarm.mc.player.jump();
            }
            if (EntityHelper.getDistanceOfEntityToBlock((Entity)AutoFarm.mc.player, blockPos) < 4.0) {
                final float[] rotationVector = RotationHelper.getRotationVector(new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.85f), (double)(blockPos.getZ() + 0.5f)), false, 2.0f, 2.0f, 360.0f);
                EventUpdate.setYaw(rotationVector[0]);
                EventUpdate.setPitch(rotationVector[1]);
                AutoFarm.mc.player.rotationYaw = rotationVector[0];
                AutoFarm.mc.player.rotationPitch = rotationVector[1];
                if (this.timerHelper.hasReached(50.0f)) {
                    AutoFarm.mc.playerController.clickBlock(blockPos, EnumFacing.UP);
                    this.timerHelper.reset();
                }
            }
        }
    }
    
    public AutoFarm() {
        super("AutoFarm", "AutoFarm", Category.OUTHER);
        this.timerHelper = new TimerHelper();
        this.posgoto = new ArrayList();
        CatClient.instance.settingsManager.rSetting(new Setting("Radius", this, 100.0, 10.0, 300.0, true));
    }
}
