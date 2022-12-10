//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import ru.internali.module.*;
import net.minecraftforge.client.event.*;
import ru.internali.notifications.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Notifications extends Module
{
    private static RenderItem itemRender;
    private Entity target;
    
    public Notifications() {
        super("Notifications", "Notifications", Category.HUD);
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        notifications.show();
    }
}
