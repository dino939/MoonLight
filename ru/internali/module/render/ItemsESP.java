//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraft.util.math.*;
import ru.internali.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import ru.internali.utils.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ItemsESP extends Module
{
    AxisAlignedBB box;
    
    public ItemsESP() {
        super("ItemsESP", "AAAAA, light", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        for (final Entity next : Utils.getEntityList()) {
            if (next instanceof EntityItem || next instanceof EntityArrow) {
                RenderUtils.drawESP(next, 1.0f, 1.0f, 1.0f, 1.0f, renderWorldLastEvent.getPartialTicks());
            }
        }
    }
}
