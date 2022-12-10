//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.util.math.*;
import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraftforge.client.event.*;
import ru.internali.utils.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Xray extends Module
{
    private long max;
    private BlockPos current;
    private List blocks;
    public static List Modes;
    private final List DETECTED_BLOCKS;
    
    static {
        Xray.Modes = new ArrayList();
    }
    
    public Xray() {
        super("Xray", "Xray", Category.RENDER);
        this.blocks = new ArrayList();
        this.DETECTED_BLOCKS = new ArrayList();
        Xray.Modes.add("RustMe");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "RustMe", (ArrayList)Xray.Modes));
        CatClient.instance.settingsManager.rSetting(new Setting("Range", this, 15.0, 5.0, 75.0, false));
    }
    
    private boolean RustmeBlock(final Block block) {
        return block == Blocks.STAINED_HARDENED_CLAY;
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        if (Objects.equals(valString, "RustMe")) {
            for (final BlockPos blockPos : BlockUtils.getBlocksInDistance(n)) {
                if (!this.RustmeBlock(Xray.mc.world.getBlockState(blockPos).getBlock())) {
                    continue;
                }
                NordTessellator.drawBoundingBoxChestBlockPos(blockPos, 1.0f, 255, 150, 60, 255);
            }
        }
    }
}
