//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import java.util.*;

public class AntiInvis extends Module
{
    private final List e;
    
    @SubscribeEvent
    public void OnPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        for (final Entity entity : AntiInvis.mc.world.loadedEntityList) {
            if (entity.isInvisible()) {
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                entity.setInvisible(false);
                this.e.add(entity);
            }
        }
    }
    
    @Override
    public void onEnable() {
        final Iterator<Entity> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            iterator.next().setInvisible(true);
        }
        this.e.clear();
        super.onEnable();
    }
    
    public AntiInvis() {
        super("AntiInvis", "AntiInvis", Category.OUTHER);
        this.e = new ArrayList();
    }
}
