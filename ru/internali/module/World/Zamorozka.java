//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.World;

import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class Zamorozka extends Module
{
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        for (int i = 1; i < 8; ++i) {
            Zamorozka.mc.world.spawnParticle(EnumParticleTypes.SPIT, Zamorozka.mc.player.posX + Math.sin(i) * 0.6, Zamorozka.mc.player.posY + 0.2, Zamorozka.mc.player.posZ + Math.cos(i) * 0.3, 0.0, 0.0, 0.0, new int[0]);
            Zamorozka.mc.world.setBlockState(new BlockPos(Zamorozka.mc.player.posX, Zamorozka.mc.player.posY - 1.0, Zamorozka.mc.player.posZ), Blocks.SNOW.getDefaultState(), 2);
            Zamorozka.mc.world.setBlockState(new BlockPos(Zamorozka.mc.player.posX + 1.0, Zamorozka.mc.player.posY - 1.0, Zamorozka.mc.player.posZ), Blocks.ICE.getDefaultState(), 5);
        }
    }
    
    public Zamorozka() {
        super("Zamorozka", "Zamorozka", Category.WORLD);
    }
}
