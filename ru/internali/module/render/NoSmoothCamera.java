//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class NoSmoothCamera extends Module
{
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent playerTickEvent) {
        NoSmoothCamera.mc.gameSettings.smoothCamera = false;
    }
    
    public NoSmoothCamera() {
        super("NoSmoothCamera", "NoSmoothCamera", Category.RENDER);
    }
}
