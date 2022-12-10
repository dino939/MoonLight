//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.client.event.*;
import ru.internali.utils.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class PlayerEntity extends Module
{
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post post) {
        switch (post.getType()) {
            case TEXT: {
                RenderUtils.renderEntity((EntityLivingBase)PlayerEntity.mc.player, 30, 40, 100);
                break;
            }
        }
    }
    
    public PlayerEntity() {
        super("PlayerEntity", "PlayerEntity", Category.RENDER);
    }
}
