//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class AntiBot extends Module
{
    public static List BOTS;
    public static boolean toggle;
    
    @Override
    public void onDisable() {
        super.onDisable();
        AntiBot.toggle = false;
        AntiBot.BOTS.clear();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        AntiBot.toggle = true;
    }
    
    public static IBlockState getState(final BlockPos blockPos) {
        return AntiBot.mc.world.getBlockState(blockPos);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "Remove").getValBoolean();
        for (final Entity entity : AntiBot.mc.world.loadedEntityList) {
            if (entity != AntiBot.mc.player && entity.ticksExisted < 5 && entity instanceof EntityOtherPlayerMP) {
                if (AntiBot.mc.player.getDistanceSq(entity) > 15.0) {
                    continue;
                }
                if (isBlockMaterial(new BlockPos(entity).down())) {
                    AntiBot.BOTS.add(entity.getName());
                    if (valBoolean) {
                        AntiBot.mc.world.removeEntity(entity);
                    }
                }
                if (!entity.isInvisible()) {
                    continue;
                }
                AntiBot.BOTS.add(entity.getName());
                if (!valBoolean) {
                    continue;
                }
                AntiBot.mc.world.removeEntity(entity);
            }
        }
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    static {
        AntiBot.BOTS = new ArrayList();
    }
    
    @SubscribeEvent
    public void onUpdate(final RenderWorldLastEvent renderWorldLastEvent) {
        final boolean valBoolean = CatClient.instance.settingsManager.getSettingByName(this, "TickRemove").getValBoolean();
        for (final Entity entity : AntiBot.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity.isInvisible() && entity != AntiBot.mc.player && valBoolean) {
                AntiBot.mc.world.removeEntity(entity);
            }
        }
    }
    
    public static boolean isBot(final String anotherString) {
        if (AntiBot.toggle) {
            final Iterator<String> iterator = AntiBot.BOTS.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equalsIgnoreCase(anotherString)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public static boolean isBlockMaterial(final BlockPos blockPos) {
        return getBlock(blockPos) == Blocks.AIR;
    }
    
    public AntiBot() {
        super("AntiBot", "Prevents you from targetting bots", Category.COMBAT);
        CatClient.instance.settingsManager.rSetting(new Setting("Remove", this, true));
        CatClient.instance.settingsManager.rSetting(new Setting("TickRemove", this, true));
    }
}
