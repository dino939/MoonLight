//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import ru.internali.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class AntiAim extends Module
{
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        if (!Mouse.isButtonDown(0) && !Mouse.isButtonDown(1) && !Mouse.isButtonDown(2)) {
            final float n = (float)(AntiAim.mc.player.rotationYaw + MathUtils.getRandomInRange(25.0, -25.0));
            AntiAim.mc.player.rotationYawHead = n;
            AntiAim.mc.player.renderYawOffset = n;
        }
    }
    
    public AntiAim() {
        super("AntiAim", "AntiAim", Category.COMBAT);
    }
}
