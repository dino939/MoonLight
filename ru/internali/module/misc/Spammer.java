//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Spammer extends Module
{
    int delay;
    
    public Spammer() {
        super("Spammer", "DungannonSpammer", Category.OUTHER);
        this.delay = 0;
        CatClient.instance.settingsManager.rSetting(new Setting("delaySetting", this, 340.0, 1.0, 1000.0, true));
    }
    
    private void doMessage() {
        final List<String> list = Arrays.asList("> 1000-7 ya umer prosti", "> Far4ik Best Developer", "> MoonLight", "> Kitty lox", "> Kitty OneLove", "> 100rub Soft eblet kupi", "> ty chy ricardo user? znachit syn huyni", "> ne yzay govno, uzay MoonLight");
        Spammer.mc.player.sendChatMessage((String)list.get(new Random().nextInt(list.size())));
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "delaySetting").getValDouble();
        ++this.delay;
        if (this.delay > n) {
            this.doMessage();
            this.delay = 0;
        }
    }
}
