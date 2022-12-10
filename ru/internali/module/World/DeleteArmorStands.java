//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.World;

import ru.internali.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class DeleteArmorStands extends Module
{
    public DeleteArmorStands() {
        super("RemArmSts", "DeleteArmorStands", Category.WORLD);
    }
    
    @SubscribeEvent
    public void onUpdate(final RenderWorldLastEvent renderWorldLastEvent) {
        if (DeleteArmorStands.mc.player == null || DeleteArmorStands.mc.world == null) {
            return;
        }
        for (final Entity entity : DeleteArmorStands.mc.world.loadedEntityList) {
            if (entity != null) {
                if (!(entity instanceof EntityArmorStand)) {
                    continue;
                }
                DeleteArmorStands.mc.world.removeEntity(entity);
            }
        }
    }
}
