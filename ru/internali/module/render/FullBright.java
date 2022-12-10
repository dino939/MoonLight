//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class FullBright extends Module
{
    public static final FullBright INSTANCE;
    public float oldgamma;
    
    public void onDisable() {
        super.onDisable();
        FullBright.mc.gameSettings.gammaSetting = this.oldgamma;
    }
    
    static {
        INSTANCE = new FullBright();
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent livingUpdateEvent) {
    }
    
    public FullBright() {
        super("FullBright", "AAAAA, light", Category.RENDER);
    }
    
    public void onEnable() {
        super.onEnable();
        this.oldgamma = FullBright.mc.gameSettings.gammaSetting;
        FullBright.mc.gameSettings.gammaSetting = 100000.0f;
    }
}
