//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import ru.internali.*;

@Mod(modid = "optifune", version = "Beta", name = "MoonLight")
public class Main
{
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) throws Exception {
        (CatClient.instance = new CatClient()).init();
    }
    
    private static boolean lIIIIlIIllI(final int n) {
        return n == 0;
    }
}
