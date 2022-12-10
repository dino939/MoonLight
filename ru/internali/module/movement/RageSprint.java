//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.movement;

import ru.internali.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class RageSprint extends Module
{
    public static Sprint instance;
    
    public void onDisable() {
        super.onDisable();
        RageSprint.mc.player.setSprinting(false);
    }
    
    public RageSprint() {
        super("RageSprint", "i like sprinting", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        RageSprint.mc.player.setSprinting(MovementHelper.isMoving());
    }
}
